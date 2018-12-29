package com.tctest;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Inherited
@Retention(value = RUNTIME)
@Target(value = METHOD)
public @interface TemplateParams {
    TemplateParam[] value();
}
