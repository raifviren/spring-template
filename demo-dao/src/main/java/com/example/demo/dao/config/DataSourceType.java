package com.example.demo.dao.config;

/**
 * @author Pranav Miglani
 * @version 1.0.0
 */
public enum DataSourceType {

    /**
     * master
     */
    MASTER,

    /**
     * slave
     */
    SLAVE,

    /**
     * cron - master
     */
    CRON_MASTER,

    /**
     * cron -slave
     */
    CRON_SLAVE,


    ;
}
