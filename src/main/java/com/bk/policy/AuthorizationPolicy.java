package com.bk.policy;

import java.util.Set;
import java.util.UUID;

/**
 * Represents an Authorization Policy which can be assigned to a Principal.<br>
 * AuthorizationPolicy is basically a set of Statements, which define access to a Resources in the system.<br>
 * It is also possible to add multiple statements for defining access to the same resource.
 *
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 11/04/22
 */
public class AuthorizationPolicy {
    private final String id;
    private Boolean enabled;
    private String modifiedBy;
    private Long modifiedOn;
    private Set<Statement> statements;

    public AuthorizationPolicy() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    public Set<Statement> getStatements() {
        return statements;
    }

    public void setStatements(Set<Statement> statements) {
        this.statements = statements;
    }
}
