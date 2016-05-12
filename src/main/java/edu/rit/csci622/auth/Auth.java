package edu.rit.csci622.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation will be used by the intercepter to determine if the
 * underlying code should be executed.
 * 
 * @author Nathaniel Cotton
 *
 */
@Target(value={ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

   Role[] roles() default {Role.ANONYMOUS};	
	
}
