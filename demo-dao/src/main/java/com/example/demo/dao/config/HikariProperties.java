package com.example.demo.dao.config;

import org.springframework.core.env.Environment;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author Pranav Miglani
 * @version 1.0.0
 */
public interface HikariProperties {

    /**
     * <p>
     *
     * @param name
     * @see Environment
     */
    @Null
    String getProperty(@NotNull String name);

    /**
     * <p>
     *
     * @param name
     * @see Environment
     */
    boolean contains(@NotNull String name);
}
