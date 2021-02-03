package com.example.demo.web.security;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * All the services related to the security context
 *
 * @author Virender Bhargav
 */
public class SecurityContextUtil {

    /**
     * Get the already authenticated ArApUser from the spring security security context
     *
     * @return
     */
    public static AppUser getLoggedInUser() {
        return (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
