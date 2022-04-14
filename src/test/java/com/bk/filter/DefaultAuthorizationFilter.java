package com.bk.filter;

import com.bk.model.TestUtils;
import com.bk.policy.AuthorizationPolicy;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 14/04/22
 */
public class DefaultAuthorizationFilter extends AuthorizationFilter {
    @Override
    public boolean isBypassUrl(String url) {
        return false;
    }

    @Override
    public AuthorizationPolicy extractPolicy(HttpServletRequest request, HttpServletResponse response) {
        AuthorizationPolicy authorizationPolicy = null;
        try {
            authorizationPolicy = TestUtils.getSamplePolicy();
        } catch (JsonProcessingException e) {
            System.err.println("Error while parsing sample policy. " +e);
        }
        return authorizationPolicy;
    }
}
