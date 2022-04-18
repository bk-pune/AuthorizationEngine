package com.bk.resource;

/**
 * An Operation that can be performed on a particular Resource.<br>
 * In authorization engine, operation is the finest element, over which, an access can be defined.
 * <br>
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 14/04/22
 */
public @interface Operation {

    /**
     * Name of this ResourceOperation, must be unique across the application<br>
     * E.g. fetch
     * @return
     */
    String name();


    /**
     * REST API url pattern for this operation. This pattern must match the pattern defined RequestMapping value defined.
     * @return
     */
    String urlPattern();
    /**
     * The resource id on which this Operation works
     * @return
     */
    String resourceId();

    /**
     * Define weather this resource operation is protected, or it is accessible to anyone
     * @return true if the resource operation requires authorization. False if resource access is public
     */
    boolean isProtected() default true;
}
