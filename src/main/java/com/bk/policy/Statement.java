package com.bk.policy;

import java.util.Set;
import java.util.UUID;

/**
 * A statement is used to define access to set of operations on a given resource.<br>
 * Statement is unmutable, i.e. one has to remove existing statement, create a new one and assign to the policy.
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 11/04/22
 */
public class Statement {
    private String id;
    private String resourceId;
    private Set<String> operations;

    // TODO
    /*
    private Condition condition;
     */

    public Statement() {
    }

    public Statement(String resourceId, Set<String> operations) {
        this.id = UUID.randomUUID().toString();
        this.resourceId = resourceId;
        this.operations = operations;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public void setOperations(Set<String> operations) {
        this.operations = operations;
    }

    public String getId() {
        return id;
    }

    public String getResourceId() {
        return resourceId;
    }

    public Set<String> getOperations() {
        return operations;
    }
}
