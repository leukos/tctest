package com.tctest;

import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Inherited
@Repeatable(TemplateParams.class)
@Retention(value = RUNTIME)
@Target(value = METHOD)
public @interface TemplateParam {
    String name();
    String[] values();
}
