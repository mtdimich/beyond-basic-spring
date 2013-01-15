package com.dimich.todo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DeprecatedEndpoint
{
    /**
     * The version (iteration) the endpoint will expire.  
     * This should be 3 iterations after the endpoint was deprecated.  
     * Example: 10.10
     */
    String expiryVersion();

    /**
     * The endpoint that will replace the deprecated one
     */
    String replacement();
}
