package com.bk.resource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Resource access definition defined for a particular resource in your business model.<br>
 * Put this annotation over the methods which allow principals to perform an action on a Resource.<br>
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 22/03/22
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResourceAccess {

    /**
     * Resource type on which this access policy is defined
     * @return
     */
    Class resource();

    /**
     * Identifier of this resource access definition, must be unique across the application
     * @return
     */
    String id();

    /**
     * Define weather this resource access is protected, or it is accessible to anyone
     * @return true if the resource access requires authorization. False if resource access is public
     */
    boolean isProtected() default true;

    /**
     * One or  of actions that can be performed on this resource.<br>
     * For example, a FILE resource can have set of actions like <i>read, write, execute</i>.<br>
     * While a REST resource can have one or more actions like <i>read</i> or <i>update</i> or <i>create</i>.
     * @return
     */
    String[] actions() default {};
}