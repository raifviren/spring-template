package com.example.demo.dao.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * @author ankitsingodia
 */
public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolderImpl.getDbType();
    }
}
