package com.example.demo.dao.config;

import javax.validation.constraints.NotNull;

/**
 * @author Pranav Miglani
 * @version 1.0.0
 */
public interface DbContextHolder {

    /**
     * <p>
     *
     * @return {@code null}.
     * @see DataSourceType
     */
    @NotNull
    DataSourceType getDbType();

    /**
     * <p>
     *
     * @param type
     * @throws RuntimeException
     * @see DataSourceType
     */
    void setDbType(@NotNull DataSourceType type);

    /**
     * <p>
     *
     * @see ThreadLocal
     */
    void clearDbType();
}

