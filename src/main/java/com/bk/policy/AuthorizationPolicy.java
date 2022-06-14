package com.bk.policy;

import java.util.Set;

/**
 * Represents an Authorization Policy which can be assigned to a Principal.<br>
 * AuthorizationPolicy is basically a set of Statements, which define access to a Resources in the system.<br>
 * It is also possible to add multiple statements for defining access to the same resource.
 *
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 11/04/22
 */
public class AuthorizationPolicy {
    private Long id;
    private String name;
    private Boolean enabled;
    private String modifiedBy;
    private Long modifiedOn;
    private Set<Statement> statements;

    public AuthorizationPolicy() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
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
