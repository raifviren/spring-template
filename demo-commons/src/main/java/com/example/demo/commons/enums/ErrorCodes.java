package com.example.demo.commons.enums;

import lombok.Getter;

/**
 *
 * @author Virender Bhargav
 */
@Getter
public enum ErrorCodes {
	INVALID_CLIENT_ID_OR_TOKEN("ERR-101", "Invalid client id/token"),
	JWT_CLAIMS_MISSING("ERR-102", "JWT Claims Missing"),
	JWT_EXPIRED("ERR-103", "JWT Token Expired"),
	;
    private String errorCode;
    private String errorMessage;



	private ErrorCodes(final String errorCode, final String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

}