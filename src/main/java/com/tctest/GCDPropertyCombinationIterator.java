package com.tctest;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class GCDPropertyCombinationIterator implements PropertyCombinationIterator {
    private final Map<String, List<String>> possibleValues;
    private long currentConfiguration = 0;
    final private long maxConfigurations;

    public GCDPropertyCombinationIterator(Map<String, List<String>> possibleValues) {
        this.possibleValues = possibleValues;
        maxConfigurations = possibleValues.values().stream().mapToLong(values -> values.size()).reduce((a, b) -> a * b).orElse(0);
    }

    @Override
    public boolean hasNext() {
        if (currentConfiguration < maxConfigurations) {
            return true;
        }
        return false;
    }

    @Override
    public Properties next() {
        return getSpecificConfiguration(currentConfiguration++);
    }

    @Override
    public Properties getSpecificConfiguration(final long configuration) {
        final Properties properties = new Properties();
        long remainder = configuration;
        for (String name : possibleValues.keySet()) {
            final int size = possibleValues.get(name).size();
            properties.put(name, possibleValues.get(name).get((int) remainder % size));
            remainder /= size;
        }

        return properties;
    }
}
