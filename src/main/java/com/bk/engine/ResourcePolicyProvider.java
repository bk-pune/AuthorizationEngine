package com.bk.engine;

import com.bk.policy.AuthorizationPolicy;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 07/06/22
 */
public interface ResourcePolicyProvider {
    /**
     * Return the authorization policy associated with this resource.
     * @param resourceId Resource Identifier
     * @return authorization policy associated with this resource or NULL if resource has no policy associated
     */
    AuthorizationPolicy getAuthorizationPolicy(String resourceId);
}