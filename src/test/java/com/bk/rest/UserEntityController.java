package com.bk.rest;

import com.bk.model.UserEntity;
import com.bk.resource.ResourceOperation;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 22/03/22
 */
@RestController
//@Profile({"POSITIVE_TEST"})
public class UserEntityController {
    @ResourceOperation(name="getUser", resourceId ="user", actions = {"read"})
    @GetMapping(value="/getUser")
    @ResponseBody
    public UserEntity getUser() {
        UserEntity user = new UserEntity();
        user.setUsername("bhushan");
        return user;
    }

    @ResourceOperation(name = "updateUser", resourceId = "user", actions = {"update", "write", "read"})
    @PostMapping(value = "/updateUser")
    @ResponseBody
    public UserEntity updateUser(UserEntity newValue) {
        return newValue; // mock body
    }

}
