package com.online.springioc.annotation.lable;

import java.lang.annotation.*;

/**
 * Created by lihongjing on 2018/8/6.
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
    String value() default "";
}
