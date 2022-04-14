package com.bk.engine;

import com.bk.exception.ResourceDefinitionException;
import com.bk.policy.AuthorizationPolicy;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 08/04/22
 */
public interface AuthorizationEngine {

    /**
     * Check if the given principal is authorized to access the given url
     * @param url
     *
     * @return True if access is allowed, false otherwise
     */
    boolean isAuthorized(AuthorizationPolicy policy, String url); // TODO correct arguments
}
