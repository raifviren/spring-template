package com.example.demo.commons.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Virender Bhargav
 */
@Getter
@Setter
public class AppInvalidInputException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AppInvalidInputException(String message) {
        super(message);
    }

    public AppInvalidInputException(Throwable cause) {
        super(cause);
    }

    public AppInvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }


}
