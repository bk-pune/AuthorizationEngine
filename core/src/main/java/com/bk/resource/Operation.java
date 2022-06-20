package com.bk.resource;

/**
 * An Operation that can be performed on a particular Resource.<br>
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 14/04/22
 */
public @interface Operation {

    /**
     * Name of this Operation, must be unique across the application<br>
     * E.g. fetch, update, etc.
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
     */ // TODO one operation - multiple resources ?
    String resourceId();

    /**
     * Define weather this resource operation is protected, or it is accessible to anyone
     * @return true if the resource operation requires authorization. False if resource access is public
     */
    boolean isProtected() default true;
}
