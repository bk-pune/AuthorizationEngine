package com.bk.policy;

import com.bk.policy.conditions.Condition;

import java.util.Set;

/**
 * A statement is used to define access to set of operations on a given resource.<br>
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 11/04/22
 */
public class Statement {
    private Long id;
    private String resourceId;
    private Set<String> operations;
    private Set<Condition> conditions;

    public Statement() {
    }

    public Statement(Long id, String resourceId, Set<String> operations, Set<Condition> conditions) {
        this.id = id;
        this.resourceId = resourceId;
        this.operations = operations;
        this.conditions = conditions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(Set<Condition> conditions) {
        this.conditions = conditions;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public void setOperations(Set<String> operations) {
        this.operations = operations;
    }

    public Long getId() {
        return id;
    }

    public String getResourceId() {
        return resourceId;
    }

    public Set<String> getOperations() {
        return operations;
    }
}
