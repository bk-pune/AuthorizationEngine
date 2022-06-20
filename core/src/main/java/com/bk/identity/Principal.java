package com.bk.identity;

import com.bk.policy.AuthorizationPolicy;

import java.util.Set;

/**
 * Represents an Identity at the runtime after the authentication is complete.<br>
 * Principal can represent a user, a service or an organization; based on the credentials used while authentication.
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 02/03/22
 */
public interface Principal extends java.security.Principal {
    // TODO Add refresh/access token support (could be jwt)

    /**
     * Return the set of Roles associated with this principal.
     * @return set of Roles associated with this principal.
     */
    Set<Role> getRoles();

    /**
     * Get the Authorization Policy associated with this Principal.
     * @return AuthorizationPolicy in json format
     */
    AuthorizationPolicy getAuthorizationPolicy();

    /**
     * Unique id for this principal.<br>
     * Not to be confused with "name". Same identity can log in multiple times at a time, will have the same name, but different id
     * @return Unique id which identifies this Principal
     */
    Long getPrincipalId();
}
