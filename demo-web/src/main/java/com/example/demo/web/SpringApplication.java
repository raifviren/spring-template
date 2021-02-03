package com.example.demo.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.InetAddress;
import java.net.UnknownHostException;

//@EnableCaching
@ComponentScan(basePackages = {"com.example.demo.*"})
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableAsync
@EnableScheduling
@Configuration
@EnableConfigurationProperties
@EntityScan("com.example.demo.*")
@EnableJpaRepositories(basePackages = {"com.example.demo.*"},
		entityManagerFactoryRef = "entityManagerFactory",
		enableDefaultTransactions = false)
@EnableTransactionManagement(order = 100)
@SpringBootApplication
public class SpringApplication {

	public static void main(String[] args) throws UnknownHostException {

		System.setProperty("hostName", InetAddress.getLocalHost().getHostName());
		System.setProperty("applicationName", "DemoSpringService");
		org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
	}

}
