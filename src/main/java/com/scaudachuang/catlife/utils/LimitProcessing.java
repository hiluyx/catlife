package com.scaudachuang.catlife.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author hiluyx
 * @since 2021/8/30 10:00
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitProcessing {
    String name();
    int ratePerSec() default 20; // n/s
    String msg() default "rate limit";
}
