package com.example.demo.commons.exceptions;


/**
 * @author Virender Bhargav
 */
public class AppServiceException extends RuntimeException {

    private static final long serialVersionUID = -4047236656832227299L;

    public AppServiceException(String message) {
        super(message);
    }

    public AppServiceException(Throwable cause) {
        super(cause);
    }

    public AppServiceException(String message, Throwable cause) {
        super(message, cause);
    }


}
