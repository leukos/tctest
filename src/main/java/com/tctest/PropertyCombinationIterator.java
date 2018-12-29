package com.tctest;

import java.util.Iterator;
import java.util.Properties;

public interface PropertyCombinationIterator extends Iterator<Properties> {
    public Properties getSpecificConfiguration(long configuration);
}
