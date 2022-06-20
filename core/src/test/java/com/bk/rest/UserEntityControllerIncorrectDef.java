package com.bk.rest;

import org.springframework.context.annotation.Profile;

@Profile({"NEGATIVE_TEST"}) // not working!
public class UserEntityControllerIncorrectDef {
    /*@ResourceOperation(name="getUser", resourceId ="user", actions = {"read"})
    public UserEntity getUser() {
        UserEntity user = new UserEntity();
        user.setUsername("bhushan");
        return user;
    }

    // Duplicate resourceOperation name
    @ResourceOperation(name="getUser", resourceId ="user", actions = {"read"})
    public UserEntity getUser2() {
        UserEntity user = new UserEntity();
        user.setUsername("bhushan");
        return user;
    }

    // Non-existing resource name
    @ResourceOperation(name="getUser2", resourceId ="non_existing", actions = {"read"})
    @GetMapping(value="/getUser")
    @ResponseBody
    public UserEntity getUser3() {
        UserEntity user = new UserEntity();
        user.setUsername("bhushan");
        return user;
    }*/
}