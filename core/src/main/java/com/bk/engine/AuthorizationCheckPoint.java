package com.bk.engine;

import com.bk.identity.Principal;
import com.bk.resource.ResourceOperationMetadata;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 07/07/22
 */
public interface AuthorizationCheckPoint {
    boolean isAuthorized(Principal principal, ResourceOperationMetadata resourceOperationMetadata);
}
