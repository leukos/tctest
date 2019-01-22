package com.tctest;

import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.tctest.util.Util.getTemplateParameters;

public class ParameterExtension implements BeforeTestExecutionCallback {

    /**
     * Signals that the test execution is run in distributed mode.
     */
    private static boolean isDistributedMode() {
        final String distributedMode = System.getProperty("are.mode.distributed");
        if (distributedMode != null && System.getProperty("are.mode.distributed").equals("true")) {
            return true;
        }
        return false;
    }

    /**
     * Signals that the test execution is run in distributed mode.
     */
    private static Long getConfigurationNumber() {
        final String configurationNumberString = System.getProperty("are.mode.distributed.number");
        if (configurationNumberString != null) {
            try {
                return Long.valueOf(configurationNumberString);
            } catch (NumberFormatException e) {
                // do nothing
            }
        }
        return null;
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) {
        Method testMethod = extensionContext.getTestMethod().orElseThrow(() -> new IllegalArgumentException("Test method must be defined."));
        Map<String, List<String>> templateParams = getTemplateParameters(testMethod);

        if (isDistributedMode()) {
            System.out.println("Running in distributed mode ...");
            System.out.println("Retrieving parameters from system properties.");
            PropertyCombinationIterator combinationIterator = new GCDPropertyCombinationIterator(templateParams);

            Map<String, String> props = combinationIterator.getSpecificConfiguration(getConfigurationNumber());
            // TODO: execute with the properties.
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
