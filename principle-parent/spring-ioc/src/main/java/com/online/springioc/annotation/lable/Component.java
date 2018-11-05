package com.online.springioc.annotation.lable;

import java.lang.annotation.*;

/**
 * Created by lihongjing on 2018/8/6.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Component {
        String value() default "";
}
