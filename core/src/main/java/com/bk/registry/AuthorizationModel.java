package com.bk.registry;

import com.bk.exception.ResourceDefinitionException;
import com.bk.resource.*;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.springframework.http.server.PathContainer;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Complete view of the Authorization schema, such as Resources and ResourceOperations.<br>
 *
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 22/03/22
 */
public class AuthorizationModel implements Registry {
    public static final String PACKAGE_COM_BK = "com.bk";
    private static final String NONE = "none";

    private RequestMappingHandlerMapping mapping;

    // Collection of Resources and corresponding ResourceOperation
    private final Map<String, ResourceMetadata> resources;

    // Collection of ResourceOperation
    private final Map<String, ResourceOperationMetadata> resourceOperations;

    private final Map<PathPattern, ResourceOperationMetadata> restApiResourceOperationMapping;

    public AuthorizationModel(RequestMappingHandlerMapping mapping) {
        resources = new HashMap<>(10);
        resourceOperations = new HashMap<>(10);
        restApiResourceOperationMapping = new HashMap<>();
        this.mapping = mapping;

        Reflections reflections = new Reflections(PACKAGE_COM_BK, Scanners.MethodsAnnotated, Scanners.TypesAnnotated);
        scanProjectForResources(reflections);
        scanProjectForResourceOperations(reflections);
        if(mapping != null) {
            buildRestAPItoResourceOperationMapping();
        }
    }

    /**
     * Builds Operation metadata based on the API model.
     */
    private void buildRestAPItoResourceOperationMapping() {
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        // REST URLs vs Method handler
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : map.entrySet()) {
            RequestMappingInfo info = entry.getKey();
            HandlerMethod method = entry.getValue();
            ResourceAccess resourceOperation = method.getMethodAnnotation(ResourceAccess.class);
            if(resourceOperation != null) {
                Operation[] operations = resourceOperation.operations();
                for (Operation operation: operations) {

                    PathPatternsRequestCondition pathPatternsCondition = info.getPathPatternsCondition();
                    if (pathPatternsCondition != null && !pathPatternsCondition.isEmptyPathMapping()) {
                        Set<PathPattern> patterns = pathPatternsCondition.getPatterns();
                        for(PathPattern pattern: patterns) {
                            // patterns defined for operations must match with the RequestMapping defined on the REST API
                            if(pattern.matches(PathContainer.parsePath(operation.urlPattern()))) {
                                PathPattern operationPattern = PathPatternParser.defaultInstance.parse(operation.urlPattern());
                                ResourceOperationMetadata resourceOperationMetadata = createResourceOperationMetadata(operation);
                                restApiResourceOperationMapping.put(operationPattern, resourceOperationMetadata);
                            } else {
                                throw new ResourceDefinitionException("Pattern for the Operation is not defined correctly. " +
                                        "Operation: " + operation.name() + ". Pattern: "+operation.urlPattern());
                            }
                        }
                    }
                }
            }
        }
        System.out.println("REST API mapping initialized:" + restApiResourceOperationMapping);
    }

    private void scanProjectForResources(Reflections reflections) {
        // TODO - figure out good strategy for APIs which do not operate on a resource
        ResourceMetadata noneResource = new ResourceMetadata();
        noneResource.setId(NONE);
        resources.put(NONE, noneResource);

        Set<Class<?>> annotatedResources = reflections.getTypesAnnotatedWith(Resource.class);
        for(Class clazz: annotatedResources) {
            Resource resource = (Resource) clazz.getAnnotation(Resource.class);
            if(resources.get(resource.id()) != null) {
                throw new ResourceDefinitionException("Duplicate resource identified: "+ resource.id());
            }
            ResourceMetadata resourceMetadata = createResourceMetadata(resource);
            resources.put(resourceMetadata.getId(), resourceMetadata);
        }
        if(resources.isEmpty()) {
            System.err.println("Not a single resource defined in the entire application!");
        }
    }

    private ResourceMetadata createResourceMetadata(Resource resource) {
        ResourceMetadata resourceMetadata = new ResourceMetadata();
        resourceMetadata.setId(resource.id());
        return resourceMetadata;
    }

    private void scanProjectForResourceOperations(Reflections reflections) {
        Set<Method> resourceOperationMethods = reflections.getMethodsAnnotatedWith(ResourceAccess.class);
        for(Method method: resourceOperationMethods) {
            ResourceAccess resourceOperation = method.getAnnotation(ResourceAccess.class);
            Operation[] operations = resourceOperation.operations();
            for(Operation operation: operations) {
                ResourceOperationMetadata resourceOperationMetadata = createResourceOperationMetadata(operation);
                validateResourceOperation(method, resourceOperationMetadata);
                resourceOperations.put(operation.name(), resourceOperationMetadata);
                resources.get(resourceOperationMetadata.getResourceId()).getResourceOperationMetadata().add(resourceOperationMetadata);
            }
        }
        if(resourceOperations.isEmpty()) {
            System.err.println("Not a single ResourceOperation defined in the entire application!");
        }
    }

    private boolean validateResourceOperation(Method method, ResourceOperationMetadata resourceOperationMetadata) {
        boolean resourceConfigured = resources.containsKey(resourceOperationMetadata.getResourceId());
        if(!resourceConfigured) {
            throw new ResourceDefinitionException("Resource mentioned in ResourceOperation not found: " + resourceOperationMetadata.getName()
            + " on method: " + method.toGenericString());
        }

        if(resourceOperations.containsKey(resourceOperationMetadata.getName())) {
            throw new ResourceDefinitionException("Duplicate ResourceAccess identified: " + resourceOperationMetadata.getName()
                    + " on method: " + method.toGenericString());
        }
        return true;
    }

    private ResourceOperationMetadata createResourceOperationMetadata(Operation operation) {
        ResourceOperationMetadata resourceOperationMetadata = new ResourceOperationMetadata();
        resourceOperationMetadata.setName(operation.name());
        resourceOperationMetadata.setResourceId(operation.resourceId());
        resourceOperationMetadata.setProtected(operation.isProtected());
        return resourceOperationMetadata;
    }

    /**
     * Utility method to get the ResourceOperationMetadata present for current API being accessed.
     * @param uriPath Path of the REST API
     * @return ResourceOperationMetadata present for current API, or null if not present
     */
    public ResourceOperationMetadata getResourceOperationMetadataForUri(String uriPath) {
        ResourceOperationMetadata resourceOperationMetadata = null;
        for(Map.Entry<PathPattern, ResourceOperationMetadata> entry: restApiResourceOperationMapping.entrySet()) {
            PathPattern pattern = entry.getKey();
            if(pattern.matches(PathContainer.parsePath(uriPath))) {
                resourceOperationMetadata = entry.getValue();
                break;
            }
        }
        return resourceOperationMetadata;
    }

    public ResourceOperationMetadata getResourceMetadataFromOperationName(String operation) {
        return resourceOperations.get(operation);
    }

    public ResourceMetadata getResourceMetadata(String resourceName) {
        return resources.get(resourceName);
    }
}
