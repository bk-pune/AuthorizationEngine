package com.bk.resource;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 22/03/22
 */
public enum ResourceType {
    ENTITY("entity"),
    REST_API("rest_api");

    private final String type;

    ResourceType(String type) {
        this.type = type;
    }
}
