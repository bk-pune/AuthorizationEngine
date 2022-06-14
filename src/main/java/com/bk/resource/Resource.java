package com.bk.resource;

import com.bk.policy.AuthorizationPolicy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A resource in your business model.<br>
 * Resource could be a REST api, a Class, or a SERVICE or anything specific to your business.<br>
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 22/03/22
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Resource {
    /**
     * Identifier of this resource, must be unique across the application
     * @return
     */
    String id();

    String description() default "";
}
