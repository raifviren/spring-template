package com.example.demo.commons.utils;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class BeanUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }

    public static boolean isEligibleBean(Class clazz) {
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
