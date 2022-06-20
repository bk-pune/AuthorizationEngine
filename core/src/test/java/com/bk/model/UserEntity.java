package com.bk.model;

import com.bk.resource.Resource;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 22/03/22
 */
@Resource(id="user")
public class UserEntity {
    private String username;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
