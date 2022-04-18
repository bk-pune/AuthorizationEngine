package com.bk.engine;

import com.bk.policy.AuthorizationPolicy;
import com.bk.policy.Statement;
import com.bk.registry.AuthorizationModel;
import com.bk.resource.ResourceOperationMetadata;

import java.util.Set;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 08/04/22
 */
public class AuthorizationEngineImpl implements AuthorizationEngine {
    private AuthorizationModel authorizationModel;

    public AuthorizationEngineImpl(AuthorizationModel authorizationModel) {
        this.authorizationModel = authorizationModel;
    }

    @Override
    public boolean isAuthorized(AuthorizationPolicy policy, String uri) {//TODO define correct arguments
        if(policy == null || !policy.getEnabled()) {
            return false; // should throw exception?
        }

        boolean authorized = false;

        // find out ResourceOperationMetadata for this uri
        ResourceOperationMetadata fromUri = authorizationModel.getResourceOperationMetadataForUri(uri);
        if(fromUri == null) {
            return true; // TODO for uri not having any ResourceOperation defined, access is allowed
        }

        if(!fromUri.getProtected()) {
            return true; // if the ResourceOperation is defined, but it is not protected API (such as SAML/OAUTH metadata endpoint)
        }

        // find out if the above ResourceOperationMetadata is present in policy definition
        Set<Statement> statements = policy.getStatements();
        for(Statement statement : statements) {
            Set<String> operations = statement.getOperations();
            for(String operation : operations) {
                ResourceOperationMetadata fromPolicy = authorizationModel.getResourceMetadataFromOperationName(operation);
                if(fromPolicy != null && fromUri.equals(fromPolicy)) {
                    authorized = true;
                    break;
                }
            } // operations

            if(authorized) {
                break;
            }
        } // statements

        return authorized;
    }
}
