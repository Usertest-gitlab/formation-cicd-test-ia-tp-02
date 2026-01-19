package com.devops.cicd;

import java.io.InputStream;
import java.util.Properties;

public class PricingConfigLoader {

    public PricingConfig load() {
        Properties props = new Properties();

        try (InputStream in = getClass().getClassLoader().getResourceAsStream("app.properties")) {
            if (in == null) {
                throw new IllegalStateException("app.properties not found in classpath");
            }
            props.load(in);

            double vatRate = Double.parseDouble(required(props, "vatRate"));
            double threshold = Double.parseDouble(required(props, "freeShippingThreshold"));

            return new PricingConfig(vatRate, threshold);

        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Cannot load pricing configuration", e);
        }
    }

    private String required(Properties props, String key) {
        String value = props.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing property: " + key);
        }
        return value.trim();
    }
}
