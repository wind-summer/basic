package com.basic.core.annotation;

import java.lang.annotation.*;

/**
 * <p>
 *      系统日志记录注解
 * </p>
 *
 * @author wenlongfei
 * @since 2019/3/27
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
