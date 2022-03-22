package com.bk.model;

import com.bk.resource.Resource;
import com.bk.resource.ResourceType;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 22/03/22
 */
@Resource(id="User", type = ResourceType.ENTITY)
public class UserEntity {
    private String username;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
