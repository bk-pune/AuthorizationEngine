package com.bk.registry;

import com.bk.exception.ResourceDefinitionException;
import com.bk.resource.ResourceAccess;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.Scanners;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Registry maintains all the operations allowed on a particular resource.<br>
 *
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 22/03/22
 */
public class ResourceAccessRegistry implements Registry {
    public static final String PACKAGE_COM_BK = "com.bk";
    private static ResourceAccessRegistry resourceAccessRegistry;
    private final Map<Method, ResourceAccess> resourceAccessMap;

    public static ResourceAccessRegistry getInstance() {
        if (resourceAccessRegistry == null) {
            if (resourceAccessRegistry == null) {
                resourceAccessRegistry = new ResourceAccessRegistry();
            }
        }
        return resourceAccessRegistry;
    }

    private ResourceAccessRegistry() {
        resourceAccessMap = new HashMap<>(10);
        Reflections reflections = new Reflections(PACKAGE_COM_BK, Scanners.MethodsAnnotated);
        Set<Method> resourceAccessMethods = reflections.getMethodsAnnotatedWith(ResourceAccess.class);
        scanProjectForResources(resourceAccessMethods);
    }

    /**
     * Scan the given package for Resources in it.
     * @param packageName e.g. com.bk
     */
    public void scanPackageForResourceAccess(String packageName) {
        Reflections reflections = new Reflections(PACKAGE_COM_BK);
        Set<Method> resourceAccessMethods = reflections.getMethodsAnnotatedWith(ResourceAccess.class);
        scanProjectForResources(resourceAccessMethods);
    }

    /**
     * Returns unmodifiable map of Resources in this application.
     * @return Unmodifiable copy of ResourceMap
     */
    public Map<Method, ResourceAccess> getResourceAccessMap() {
        return Collections.unmodifiableMap(resourceAccessMap);
    }

    private void scanProjectForResources(Set<Method> resourceAccessMethods) {
        for(Method method: resourceAccessMethods) {
            ResourceAccess resourceAccess = method.getAnnotation(ResourceAccess.class);
            if(resourceAccessMap.containsKey(method)) {
                throw new ResourceDefinitionException("Duplicate ResourceAccess identified: "+ method.toGenericString());
            }
            resourceAccessMap.put(method, resourceAccess);
        }
        if(resourceAccessMap.isEmpty()) {
            System.err.println("Not a single ResourceAccess defined in the entire application!");
        }
    }
}
