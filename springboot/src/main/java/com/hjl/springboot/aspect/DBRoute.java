package com.hjl.springboot.aspect;

import java.lang.annotation.*;

/**
 * @athor: HJL
 * @create: 2018-12-26 17:32
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface DBRoute {

    String name() default "one";
}
