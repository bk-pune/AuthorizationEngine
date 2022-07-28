package com.bk.engine;

import com.bk.exception.AuthorizationException;
import com.bk.identity.Principal;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 08/04/22
 */
public interface AuthorizationEngine {

    /**
     * Check if the given principal is authorized to access the given url
     *
     * @param principal
     * @param url
     *
     * @return True if access is allowed, false otherwise
     */
    boolean isAuthorized(Principal principal, String url) throws AuthorizationException; // TODO correct arguments
}
