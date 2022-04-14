package com.bk.resource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ResourceOperation defines a specific operation that can be performed on a resource.<br>
 * Put this annotation over the methods which allow principals to perform an action on a Resource.<br>
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 22/03/22
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResourceOperation {

    /**
     * Name of this ResourceOperation, must be unique across the application<br>
     * E.g. fetchUser
     * @return
     */
    String name();

    /**
     * The resource id of this ResourceOperation
     * @return
     */
    String resourceId();

    /**
     * Define weather this resource operation is protected, or it is accessible to anyone
     * @return true if the resource operation requires authorization. False if resource access is public
     */
    boolean isProtected() default true;

    /**
     * One or more actions this ResourceOperation performs on a resource.<br>
     * For example, a FILE resource can have set of actions like <i>read, write, execute</i>.<br>
     * While a REST resource can have one or more actions like <i>read</i> or <i>update</i> or <i>create</i>.
     * @return
     */
    String[] actions() default {};
}