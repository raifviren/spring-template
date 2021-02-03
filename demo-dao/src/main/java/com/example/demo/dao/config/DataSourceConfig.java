package com.example.demo.dao.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@PropertySources({
        @PropertySource(value = {"classpath:datasource-${spring.profiles.active}.properties"}),
//        @PropertySource(value = {"classpath:accounting-listener-resources/listener-datasource-${spring.profiles.active}.properties"}, ignoreResourceNotFound = true),
//        @PropertySource(value = {"classpath:accounting-schedular-resources/schedular-datasource-${spring.profiles.active}.properties"}, ignoreResourceNotFound = true),
        /*
         * the below file is added so as to provide a consolidated application properties file to the QA
         */
        @PropertySource(value = "classpath:application-configuration.properties", ignoreResourceNotFound = true)
})
@Slf4j
@DependsOn("DatabasePropertiesLoggingConfig")
public class DataSourceConfig {

    @Bean("hcMaster")
    @Primary
    @ConfigurationProperties(prefix = "master.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean("hcSlave")
    @ConfigurationProperties(prefix = "slave.datasource.hikari")
    public HikariConfig slaveHikariConfig() {
        return new HikariConfig();
    }

    @Primary
    @Bean(name = DBConstants.DATA_SOURCE_MASTER)
    public DataSource masterDataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean(name = DBConstants.DATA_SOURCE_SLAVE)
    public DataSource slaveDataSource() {
        return new HikariDataSource(slaveHikariConfig());
    }


    @Bean("RoutingDataSource")
    public RoutingDataSource routingDataSource() {
        RoutingDataSource dataSource = new RoutingDataSource();
        dataSource.setDefaultTargetDataSource(masterDataSource());
        Map<Object, Object> targets = new HashMap<>();
        targets.put(DataSourceType.MASTER, masterDataSource());
        targets.put(DataSourceType.SLAVE, slaveDataSource());
        dataSource.setTargetDataSources(targets);
        dataSource.afterPropertiesSet();
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {


        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(routingDataSource());
        em.setPersistenceUnitName("persistence.routing");
        em.setPackagesToScan(new String[]{"com.example"});
        em.setJpaPropertyMap(getHibernatePropertiesMap());

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getHibernatePropertiesMaster());
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        EntityManagerFactory emf = entityManagerFactory().getObject();
        JpaTransactionManager transactionManager = new JpaTransactionManager(emf);
        return transactionManager;
    }


    private Properties getHibernatePropertiesMaster() {
        Properties properties = new Properties();
        properties.put("org.hibernate.envers.auditTableSuffix", "_aud");
        properties.put("org.hibernate.envers.auditTablePrefix", "");
//        properties.put("org.hibernate.envers.storeDataAtDelete", "true");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.use_sql_comments", "true");
        properties.put("hibernate.generate_statistics", "false");
        properties.put("hibernate.hbm2ddl.auto", "none");
//        properties.put("bytecode.use_reflection_optimizer", "false");
//        properties.put("javax.persistence.validation.mode", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
//        properties.put("hibernate.jdbc.batch_size", "5");
//        properties.put("hibernate.order_inserts", "true");
//        properties.put("hibernate.order_updates", "true");
        return properties;
    }

    //ankit
//    private Map<String, String> getHibernatePropertiesMap(){
//        Map<String, String> properties = new HashMap<>();
//        properties.put("org.hibernate.envers.auditTableSuffix", "_aud");
//        properties.put("org.hibernate.envers.auditTablePrefix", "");
////        properties.put("org.hibernate.envers.storeDataAtDelete", "true");
//        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.format_sql", "true");
//        properties.put("hibernate.generate_statistics", "false");
//        properties.put("hibernate.hbm2ddl.auto", "none");
////        properties.put("bytecode.use_reflection_optimizer", "false");
////        properties.put("javax.persistence.validation.mode", "none");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
////        properties.put("hibernate.jdbc.batch_size", "5");
////        properties.put("hibernate.order_inserts", "true");
////        properties.put("hibernate.order_updates", "true");
//        return properties;
//        
//    }

    //pranav
    private Map<String, String> getHibernatePropertiesMap() {
        Map<String, String> properties = new HashMap<>();
        properties.put("org.hibernate.envers.auditTableSuffix", "_AUD");
        properties.put("org.hibernate.envers.auditTablePrefix", "");
        properties.put("org.hibernate.envers.storeDataAtDelete", "true");
        properties.put("org.hibernate.envers.do_not_audit_optimistic_locking_field", "true");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.use_sql_comments", "true");
        properties.put("hibernate.generate_statistics", "false");
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("bytecode.use_reflection_optimizer", "false");
        properties.put("javax.persistence.validation.mode", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        return properties;
    }


}
