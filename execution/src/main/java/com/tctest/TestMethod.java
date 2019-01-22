package com.tctest;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestMethod {
    public static void main(String[] args) throws NoSuchMethodException {
        Method method = MainTest.class.getMethod("test");
        Arrays.stream(method.getAnnotation(TemplateParams.class).value()).forEach(t -> System.out.println(t.name()));
    }
}
