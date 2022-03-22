package com.bk.registry;

import com.bk.exception.ResourceDefinitionException;
import com.bk.resource.Resource;
import org.reflections.Reflections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 22/03/22
 */
public class ResourceRegistry implements Registry {

    public static final String PACKAGE_COM_BK = "com.bk";
    private static ResourceRegistry resourceRegistry;
    private final Map<Class, Resource> resourceMap;

    public static ResourceRegistry getInstance() {
        if (resourceRegistry == null) {
            if (resourceRegistry == null) {
                resourceRegistry = new ResourceRegistry();
            }
        }
        return resourceRegistry;
    }

    private ResourceRegistry() {
        resourceMap = new HashMap<>(10);
        Reflections reflections = new Reflections(PACKAGE_COM_BK);
        Set<Class<?>> resources = reflections.getTypesAnnotatedWith(Resource.class);
        scanProjectForResources(resources);
    }

    /**
     * Scan the given package for Resources in it.
     * @param packageName e.g. com.bk
     */
    public void scanPackageForResource(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> resources = reflections.getTypesAnnotatedWith(Resource.class);
        scanProjectForResources(resources);
    }

    /**
     * Returns unmodifiable map of Resources in this application.
     * @return Unmodifiable copy of ResourceMap
     */
    public Map<Class, Resource> getResourceMap() {
        return Collections.unmodifiableMap(resourceMap);
    }

    private void scanProjectForResources(Set<Class<?>> resources) {
        for(Class clazz: resources) {
            Resource resource = (Resource) clazz.getAnnotation(Resource.class);
            if(resourceMap.containsKey(clazz)) {
                throw new ResourceDefinitionException("Duplicate resource identified: "+ clazz);
            }
            resourceMap.put(clazz, resource);
        }
        if(resourceMap.isEmpty()) {
            System.err.println("Not a single resource defined in the entire application!");
        }
    }
}