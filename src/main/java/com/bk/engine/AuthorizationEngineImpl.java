package com.bk.engine;

import com.bk.identity.Principal;
import com.bk.identity.Role;
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

    // TODO
    // private ResourcePolicyProvider resourcePolicyProvider;

    public AuthorizationEngineImpl(AuthorizationModel authorizationModel) {
        this.authorizationModel = authorizationModel;
        // this.resourcePolicyProvider = resourcePolicyProvider;
    }

    @Override
    public boolean isAuthorized(Principal principal, String uri) {//TODO define correct arguments
        boolean isPrincipalAuthorized = false;
        boolean isResourceAccessAllowed = false;

        // find out ResourceOperationMetadata for this uri
        ResourceOperationMetadata resourceMetadataFromUri = authorizationModel.getResourceOperationMetadataForUri(uri);
        if (resourceMetadataFromUri == null) {
            return true; // TODO for uri not having any ResourceOperation defined, access is allowed
        }

        if (!resourceMetadataFromUri.getProtected()) {
            return true; // if the ResourceOperation is defined, but it is not protected API (such as SAML/OAUTH metadata endpoint)
        }

        // Verify if the resource can be accessed
        isResourceAccessAllowed = verifyResourceAccessAllowed(principal, resourceMetadataFromUri.getResourceId());

        // Verify if the principal is authorized to access this resource
        isPrincipalAuthorized = verifyPrincipalIsAuthorized(principal, resourceMetadataFromUri);


        return (isResourceAccessAllowed && isPrincipalAuthorized);
    }

    private boolean verifyResourceAccessAllowed(Principal principal, String resourceId) {
        // AuthorizationPolicy resourceAuthorizationPolicy = resourcePolicyProvider.getAuthorizationPolicy(resourceId);
        return true; // TODO
    }

    private boolean verifyPrincipalIsAuthorized(Principal principal, ResourceOperationMetadata resourceMetadataFromUri) {
        boolean isPrincipalAuthorized = false;
        // if principal has role which provides the resource access, then no need to verify the principal policy

        Set<Role> roles = principal.getRoles();
        // Verify principal roles
        if (roles != null && !roles.isEmpty()) {
            for (Role role : roles) {
                isPrincipalAuthorized = verifyPrincipalPolicy(role.getPolicy(), resourceMetadataFromUri);

                // any single role is sufficient to grant access
                if (isPrincipalAuthorized) {
                    break;
                }
            }
        }

        if(isPrincipalAuthorized) {
            return isPrincipalAuthorized;
        } else {
            AuthorizationPolicy principalAuthorizationPolicy = principal.getAuthorizationPolicy();
            return verifyPrincipalPolicy(principalAuthorizationPolicy, resourceMetadataFromUri);
        }
    }

    private boolean verifyPrincipalPolicy(AuthorizationPolicy principalAuthorizationPolicy, ResourceOperationMetadata resourceMetadataFromUri) {
        boolean authorized = false;
        if (principalAuthorizationPolicy == null || !principalAuthorizationPolicy.getEnabled()) {
            return false; // should throw exception?
        }

        // find out if the above ResourceOperationMetadata is present in policy definition
        Set<Statement> statements = principalAuthorizationPolicy.getStatements();
        for (Statement statement : statements) {
            Set<String> operations = statement.getOperations();
            for (String operation : operations) {
                ResourceOperationMetadata fromPolicy = authorizationModel.getResourceMetadataFromOperationName(operation);
                if (fromPolicy != null && resourceMetadataFromUri.equals(fromPolicy)) {
                    authorized = true;
                    break;
                }
            } // operations

            if (authorized) {
                break;
            }
        } // statements
        return authorized;
    }
}