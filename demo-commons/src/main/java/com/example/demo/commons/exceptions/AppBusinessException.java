package com.example.demo.commons.exceptions;

import com.example.demo.commons.enums.ErrorCodes;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Virender Bhargav
 */
@Getter
@ToString
public class AppBusinessException extends RuntimeException {

    private static final long serialVersionUID = -4047236656832227299L;

    private String errorMessage;
    private String errorCode;

    public AppBusinessException(final String message) {
        super(message);
    }

    public AppBusinessException(ErrorCodes errorCodes) {
        super(errorCodes.getErrorMessage());
        this.errorCode = errorCodes.getErrorCode();
        this.errorMessage = errorCodes.getErrorMessage();
    }
    
    public AppBusinessException(ErrorCodes errorCodes, String message) {
        super(errorCodes.getErrorMessage() + " ~~ " + message);
        this.errorCode = errorCodes.getErrorCode();
        this.errorMessage = errorCodes.getErrorMessage() + " ~~ " + message;
    }
}
