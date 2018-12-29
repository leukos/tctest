package com.tctest;

import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParameterExtension implements BeforeTestExecutionCallback {

    /**
     * Signals that the test execution is ran in distributed mode.
     */
    private static boolean isDistributedMode() {
        String distributedMode = System.getProperty("are.mode.distributed");
        if (distributedMode != null && System.getProperty("are.mode.distributed").equals("true")) {
            return true;
        }
        return false;
    }

    private static Map<String, List<String>> getTemplateParameters(Method method) {
        return Arrays.stream(method
                .getAnnotation(TemplateParams.class)
                .value())
                .collect(Collectors.toMap(TemplateParam::name, (param) -> Arrays.asList(param.values())));
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) {
        Method testMethod = extensionContext.getTestMethod().orElseThrow(() -> new IllegalArgumentException("Test method must be defined."));
        Map<String, List<String>> templateParams = getTemplateParameters(testMethod);

        if (isDistributedMode()) {
            System.out.println("Running in distributed mode ...");
            System.out.println("Retrieving parameters from system properties.");
        }

        PropertyCombinationIterator combinationIterator = new GCDPropertyCombinationIterator(templateParams);

        combinationIterator.forEachRemaining(properties -> {
            String configuration = properties
                    .keySet()
                    .stream()
                    .map(key -> (String) "[" + key + ": " + (String) properties.get(key) + "]")
                    .collect(Collectors.joining(","));
            System.out.println("Execution Configruation: { " + configuration + " }");
        });
    }
}
