package com.bk.resource;

/**
 * Complete structure of a ResourceOperation. Maintains all the details of an operation that can be performed on a resource.
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 23/03/22
 */
public class ResourceOperationMetadata {
    private String resourceId;
    private String [] actions;
    private Boolean isProtected;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String[] getActions() {
        return actions;
    }

    public void setActions(String[] actions) {
        this.actions = actions;
    }

    public Boolean getProtected() {
        return isProtected;
    }

    public void setProtected(Boolean aProtected) {
        isProtected = aProtected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceOperationMetadata that = (ResourceOperationMetadata) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
