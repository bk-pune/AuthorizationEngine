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
public @interface ResourceAccess {
    /**
     * Array of specific operations that are being performed on resources.<br>
     * If your REST endpoints have request pattern mapping, then you can define Operation for every/some/none of the concrete possible paths.<p>
     * Also, each operation can have different resource id. This allows same REST api to make changes in multiple resources.<br>
     * Please make sure you define the Operations properly to have better authorization control.
     *
     * @return Array of operations that are being performed on resources
     */
    Operation[] operations();

    /**
     * Reserved for future use<br>
     * One or more actions this ResourceOperation performs on a resource.<br>
     * For example, a FILE resource can have set of actions like <i>read, write, execute</i>.<br>
     * While a REST resource can have one or more actions like <i>read</i> or <i>update</i> or <i>create</i>.
     * @return
     */
    String[] actions() default {};
}