package com.example.demo.dao.annotations;

import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Pranav Miglani
 * @version 1.0.0
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
@Transactional(readOnly = true)
public @interface ReadOnly {

}

