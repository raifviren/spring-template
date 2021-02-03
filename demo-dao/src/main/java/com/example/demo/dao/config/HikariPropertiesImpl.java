package com.example.demo.dao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author Pranav Miglani
 * @version 1.0.0
 */
@Component

@PropertySources({
        @PropertySource(value = {"classpath:datasource-${spring.profiles.active}.properties"}),
//        @PropertySource(value = {"classpath:accounting-schedular-resources/schedular-datasource.${spring.profiles.active}.properties"}, ignoreResourceNotFound = true),
//        @PropertySource(value = {"classpath:accounting-schedular-resources/schedular-datasource-${spring.profiles.active}.properties"}, ignoreResourceNotFound = true),
        /*
         * the below file is added so as to provide a consolidated application properties file to the QA
         */
        @PropertySource(value = "classpath:application-configuration.properties", ignoreResourceNotFound = true)
})
public class HikariPropertiesImpl implements HikariProperties {
    @Autowired
    private Environment env;

    @Override
    public String getProperty(String name) {
        return env.getProperty(name);
    }

    @Override
    public boolean contains(String name) {
        return StringUtils.hasLength(env.getProperty(name));
    }
}
