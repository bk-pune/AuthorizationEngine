package com.bk.model;

import com.bk.policy.AuthorizationPolicy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 13/04/22
 */
public class TestUtils {

    private static final String SAMPLE_POLICY = "{\"id\":1,\"enabled\":true,\"modifiedOn\":1211315545,\"modifiedBy\":\"admin\",\"statements\":[{\"id\":\"1\",\"resourceId\":\"user\",\"operations\":[\"getUser\"]},{\"id\":\"1\",\"resourceId\":\"role\",\"operations\":[\"fetchRole\",\"op2\"]}]}";
    public static final AuthorizationPolicy getSamplePolicy() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(SAMPLE_POLICY, AuthorizationPolicy.class);
    }
}
