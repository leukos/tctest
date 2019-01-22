package com.tctest.util;

import com.tctest.TemplateParam;
import com.tctest.TemplateParams;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Util {
    public static Map<String, List<String>> getTemplateParameters(Method method) {
        return Arrays.stream(method
                .getAnnotation(TemplateParams.class)
                .value())
                .collect(Collectors.toMap(TemplateParam::name, (param) -> Arrays.asList(param.values())));
    }

    public static String configurationToString(Map<String, String> configuration) {
        return "[" + configuration.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining(", ")) + "]";
    }
}
