package com.bk.resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Complete structure of a resource and corresponding operations that can be performed on it.
 *
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 23/03/22
 */
public class ResourceMetadata {
    private String id;
    private List<ResourceOperationMetadata> resourceOperationMetadata = new ArrayList<>(5);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ResourceOperationMetadata> getResourceOperationMetadata() {
        return resourceOperationMetadata;
    }

    public void setResourceOperationMetadata(List<ResourceOperationMetadata> resourceOperationMetadata) {
        this.resourceOperationMetadata = resourceOperationMetadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceMetadata that = (ResourceMetadata) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
