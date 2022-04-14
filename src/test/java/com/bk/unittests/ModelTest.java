package com.bk.unittests;

import com.bk.model.TestUtils;
import com.bk.policy.AuthorizationPolicy;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 13/04/22
 */
public class ModelTest {
    @Test
    public void testSamplePolicy() throws JsonProcessingException {
        AuthorizationPolicy samplePolicy = TestUtils.getSamplePolicy();
        Assert.assertNotNull(samplePolicy);
    }
}
