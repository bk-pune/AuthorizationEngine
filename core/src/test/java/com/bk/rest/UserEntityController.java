package com.bk.rest;

import com.bk.model.UserEntity;
import com.bk.resource.Operation;
import com.bk.resource.ResourceAccess;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 22/03/22
 */
@RestController
//@Profile({"POSITIVE_TEST"})
public class UserEntityController {
    @ResourceAccess(operations = {
            @Operation(name = "hello", urlPattern = "/hello", resourceId = "none")
    })
    @GetMapping(value="/hello")
    @ResponseBody
    public String helloWorld() {
        return "Hello Guest!";
    }

    @ResourceAccess(operations = {
            @Operation(name = "getUser", urlPattern = "/getUser", resourceId = "user")
    })
    @GetMapping(value="/getUser")
    @ResponseBody
    public UserEntity getUser() {
        UserEntity user = new UserEntity();
        user.setUsername("bhushan");
        return user;
    }

    @ResourceAccess(operations = {
            @Operation(name = "updateUser", urlPattern = "/updateUser", resourceId = "user")
    })
    @PostMapping(value = "/updateUser")
    @ResponseBody
    public UserEntity updateUser(UserEntity newValue) {
        return newValue; // mock body
    }

    @ResourceAccess(operations = {
            @Operation(name = "fetchUser", urlPattern = "/fetch/user", resourceId = "user"),
            @Operation(name = "fetchRole", urlPattern = "/fetch/role", resourceId = "role")
    })
    @GetMapping(value="/fetch/*")
    @ResponseBody
    public String fetch(HttpServletRequest request) {
        return "You invoked:<b>" + request.getRequestURI() + "<b><br>" +
                "This is a rest API with pattern in the request mapping-> " +
                "<i>/get/*</i><br>Try invoking like /get/user or /get/student";
    }

    @ResourceAccess(operations = {
            @Operation(name = "fetchAnyUser", urlPattern = "/fetch/user/entity/1", resourceId = "user"), // full match
            @Operation(name = "fetchAnyRole", urlPattern = "/fetch/role/entity/*", resourceId = "role") // valid wild card path
    })
    @GetMapping(value="/fetch/*/entity/*")
    @ResponseBody
    public String fetchAny(HttpServletRequest request) {
        return "You invoked:<b>" + request.getRequestURI() + "<b><br>" +
                "This is a rest API with pattern in the request mapping-> " +
                "<i>/fetch/*/entity/*</i><br>Try invoking like /fetch/student/entity/1 or /fetch/role/entity/bhushan";
    }


}
