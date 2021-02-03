/**
 *
 */
package com.example.demo.commons.exceptions;

import com.example.demo.commons.enums.ErrorCodes;
import lombok.Getter;

/**
 * @author Virender Bhargav
 *
 */
@Getter
public class AppUnauthorizedException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String errorCode;
    private String errorMessage;

    public AppUnauthorizedException(final String message) {
        super(message);
    }

    public AppUnauthorizedException(final ErrorCodes errorCodes) {
        super(errorCodes.getErrorMessage());
        this.errorCode = errorCodes.getErrorCode();
        this.errorMessage = errorCodes.getErrorMessage();
    }

}
