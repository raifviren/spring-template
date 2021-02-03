package com.example.demo.commons.config.threadpools;

import com.example.demo.commons.constants.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 
 * @author Virender Bhargav
 *
 */
@Configuration
public class CommonThreadPools {
	
	@Autowired
	Environment env;
	
	@Bean(name = "CommonPool1")
	public Executor getCommonThreadPool1() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(env.getProperty(AppConstants.AYNC_SERVICE_POOL_SIZE_CORE, Integer.class, 10));
		executor.setMaxPoolSize(env.getProperty(AppConstants.AYNC_SERVICE_POOL_SIZE_MAX, Integer.class, 20));
		executor.setQueueCapacity(env.getProperty(AppConstants.AYNC_SERVICE_QUEUE_SIZE, Integer.class, 20));
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.setThreadNamePrefix(AppConstants.AYNC_THREAD_NAME_PREFIX);
		executor.initialize();
		return executor;
	}
}
