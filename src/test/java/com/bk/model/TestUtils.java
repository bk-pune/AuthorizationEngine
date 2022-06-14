package com.bk.model;

import com.bk.policy.AuthorizationPolicy;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 13/04/22
 */
public class TestUtils {

    public static final String ADMIN_POLICY = "ADMIN_POLICY";
    public static final String GUEST_POLICY = "GUEST_POLICY";

    private static Map<String, AuthorizationPolicy> policyMap;

    static {
        policyMap = new HashMap<>();
        try {
            initPolicies();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initPolicies() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        AuthorizationPolicy authorizationPolicy = objectMapper.readValue(new ClassPathResource("./policies/admin_policy.json").getFile(), AuthorizationPolicy.class);
        policyMap.put(authorizationPolicy.getName(), authorizationPolicy);
        authorizationPolicy = objectMapper.readValue(new ClassPathResource("./policies/guest_policy.json").getFile(), AuthorizationPolicy.class);
        policyMap.put(authorizationPolicy.getName(), authorizationPolicy);
    }

    public static final AuthorizationPolicy getPolicy(String policyName) {
        return policyMap.get(policyName);
    }
}
