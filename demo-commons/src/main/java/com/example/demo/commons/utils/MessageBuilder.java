/**
 * 
 */
package com.example.demo.commons.utils;

import com.example.demo.commons.constants.AppConstants;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Virender Bhargav
 *
 */
public class MessageBuilder {

	private StringBuilder message;
	private String messageSeparator;

	public MessageBuilder() {
		super();
		this.message = new StringBuilder(AppConstants.BLANK);
		this.messageSeparator = AppConstants.PIPE_SEPARATOR;
	}

	public MessageBuilder(String message) {
		super();
		this.message = new StringBuilder(message);
		this.messageSeparator = AppConstants.PIPE_SEPARATOR;
	}

	public MessageBuilder(String message, String messageSeparator) {
		super();
		this.message = new StringBuilder(message);
		this.messageSeparator = messageSeparator;
	}

	public static MessageBuilder build() {
		return new MessageBuilder();
	}

	public static MessageBuilder build(String message) {
		return new MessageBuilder(message);
	}

	public static MessageBuilder build(String message, String messageSeparator) {
		return new MessageBuilder(message, messageSeparator);
	}

	public void appendMessage(String message, Object... messageArgs) {
		if (StringUtils.isNotBlank(this.message.toString()))
			this.message.append(this.messageSeparator);
		this.message.append(String.format(message, messageArgs));
	}

	public void appendMessageIfConditionFalse(boolean condition, String message, Object... messageArgs) {
		if (!condition)
			appendMessage(message, messageArgs);
	}

	public String getMessage() {
		return message.toString();
	}

}
