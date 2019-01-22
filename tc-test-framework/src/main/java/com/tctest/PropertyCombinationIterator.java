package com.tctest;

import java.util.Iterator;
import java.util.Map;

public interface PropertyCombinationIterator extends Iterator<Map<String, String>> {
    long getNumberOfPossibleConfigurations();

    long getCurrentConfiguration();

    Map<String, String> getSpecificConfiguration(final long configuration);
}
