package com.tctest;

import java.util.HashMap;
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
    public Map<String, String> next() {
        return getSpecificConfiguration(currentConfiguration++);
    }

    @Override
    public long getNumberOfPossibleConfigurations() {
        return maxConfigurations;
    }

    @Override
    public long getCurrentConfiguration() {
        return currentConfiguration;
    }

    @Override
    public Map<String, String> getSpecificConfiguration(final long configuration) {
        final Map<String, String> properties = new HashMap<>();
        long remainder = configuration;
        for (String name : possibleValues.keySet()) {
            final int size = possibleValues.get(name).size();
            properties.put(name, possibleValues.get(name).get((int) remainder % size));
            remainder /= size;
        }

        return properties;
    }
}
