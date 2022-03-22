package com.bk.rest;

import com.bk.model.UserEntity;
import com.bk.resource.ResourceAccess;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 22/03/22
 */
@RestController
public class UserEntityController {
    @ResourceAccess(id="getUser", actions = {"read"}, resource = UserEntity.class, isProtected = true)
    @GetMapping(value="/getUser")
    @ResponseBody
    public UserEntity getUser() {
        UserEntity user = new UserEntity();
        user.setUsername("bhushan");
        return user;
    }
}
