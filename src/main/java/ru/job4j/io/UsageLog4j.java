package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean boolValue = true;
        byte byteValue = 100;
        short shortValue = 30_000;
        int intValue = 30_000_000;
        long longValue = 30_000_000;
        float floatValue = 30_000_000;
        double doubleValue = 30_000_000;
        char charValue = 'a';
        LOG.debug("value: {}", boolValue);
        LOG.debug("value: {}", byteValue);
        LOG.debug("value: {}", shortValue);
        LOG.debug("value: {}", intValue);
        LOG.debug("value: {}", longValue);
        LOG.debug("value: {}", floatValue);
        LOG.debug("value: {}", doubleValue);
        LOG.debug("value: {}", charValue);
    }
}
