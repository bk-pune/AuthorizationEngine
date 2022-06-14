package com.bk.identity;

import com.bk.policy.AuthorizationPolicy;

/**
 * Role is predefined Authorization Policy which can be mapped to a principal.<br>
 * AuthorizationEngine is a blend of both traditional Role Based Access and Attribute Based Access. <p/>
 * Having predefined roles ease the job of Admins where access to common operations can be pre-defined as a Role,
 * while any other customizations can be added in the AuthorizationPolicy separately.<p/>
 * E.g. A guest user can have a 'Guest' role assigned, however, at a times, he/she might need to perform an operation which beyond the Guest scope.
 * In such scenarios, Admin can temporarily change the AuthorizationPolicy for that user to 'Guest' role + 'operation'
 *
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 07/06/22
 */
public class Role {
    private Long id;
    private String name;
    private String modifiedBy;
    private Long modifiedOn;
    private AuthorizationPolicy policy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuthorizationPolicy getPolicy() {
        return policy;
    }

    public void setPolicy(AuthorizationPolicy policy) {
        this.policy = policy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Long getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Long modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
