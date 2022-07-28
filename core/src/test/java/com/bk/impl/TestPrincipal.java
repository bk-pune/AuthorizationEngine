package com.bk.impl;

import com.bk.identity.Principal;
import com.bk.identity.Role;
import com.bk.policy.AuthorizationPolicy;

import java.util.Map;
import java.util.Set;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 14/06/22
 */
public class TestPrincipal implements Principal {
    private Long id;
    private String name;
    private Set<Role> roles;
    private AuthorizationPolicy authorizationPolicy;

    public TestPrincipal(Long id, String name, Set<Role> roles, AuthorizationPolicy authorizationPolicy) {
        this.name = name;// should be unique
        this.roles = roles;
        this.authorizationPolicy = authorizationPolicy;
        this.id = id;// should be unique
    }

    @Override
    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public AuthorizationPolicy getAuthorizationPolicy() {
        return authorizationPolicy;
    }

    @Override
    public Long getPrincipalId() {
        return id;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
}
