package com.bk.test;

import com.bk.registry.ResourceAccessRegistry;
import com.bk.registry.ResourceRegistry;
import com.bk.model.UserEntity;
import com.bk.rest.UserEntityController;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 22/03/22
 */
public class ResourceTest {
    @Test
    public void testResource() {
        ResourceRegistry resourceRegistry = ResourceRegistry.getInstance();
        Assert.assertTrue(resourceRegistry.getResourceMap().containsKey(UserEntity.class));
    }

    @Test
    public void testResourceAccess() throws NoSuchMethodException {
        ResourceAccessRegistry resourceAccessRegistry = ResourceAccessRegistry.getInstance();
        Method getUser = UserEntityController.class.getMethod("getUser");
        Assert.assertTrue(resourceAccessRegistry.getResourceAccessMap().containsKey(getUser));
    }
}
