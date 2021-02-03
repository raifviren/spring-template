package com.example.demo.commons.constants;

public class AppConstants {

    public final static String CONTENT_TYPE_KEY = "Content-Type";
    public final static String REQUEST_HEADER_OAUTH_TOKEN_KEY = "x-user-token";
    public final static String REQUEST_HEADER_MID_KEY = "x-user-mid";
    public final static String REQUEST_CONTEXT_TOKEN_KEY = "sessionToken";
    public final static String MODEL_REQUEST_CONTEXT_KEY = "requestContext";
    public static final String REQUEST_HEADER_REQUEST_ID_KEY = "requestId";
    public static final String OAUTH_USER_INFO_SERIAL = "sessionUserSerial";
    public static final String OAUTH_USER_INFO = "sessionUser";
    public static final String REQUEST_ID = "requestId";

	public static final String X_AUTH_UMP = "x-auth-ump";
	public static final String X_USER_TOKEN = "x-user-token";
	public static final String X_USER_MID = "x-user-mid";
	public static final String X_SITE_ID = "x-site-id";
	public static final String X_SITE_NAME = "x-site-name";
	public static final String X_FILE_NAME = "x-file-name";
	
	public static final String X_CLIENT_ID = "x-client-id";
	public static final String X_CLIENT_TOKEN = "x-client-token";
	

	/*ASYNC POOL CONSTANTS*/
	public static final String AYNC_SERVICE_POOL_SIZE_CORE = "async.common.pool1.size.core";
	public static final String AYNC_SERVICE_POOL_SIZE_MAX = "async.common.pool1.size.max";
	public static final String AYNC_SERVICE_QUEUE_SIZE = "async.common.pool1.queue.size";
	public static final String AYNC_THREAD_NAME_PREFIX = "arap-async-service-";
	
    public enum ResultStatus{
		SUCCESS,
		ACCEPTED,
		FAILED;
	}
    
	public static final String INVOICE_NUM_SEPARATOR = "-";
	public static final String INVOICE_FILE_NAME_FORMAT = "Invoice #%s.pdf";
	public static final String RECEIPT_FILE_NAME_FORMAT = "Receipt #%s.pdf";
	public static final String CREDIT_NOTE_FILE_NAME_FORMAT = "Credit Note #%s.pdf";
	public static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	public static final String MOBILE_REGEX = "^[6-9][0-9]{9}$";

	//properties
	public static final String KAFKA_CONSUMER_BOOTSTRAP_ADDRESS ="kafka.consumer.bootstrap.address";
	public static final String KAFKA_PRODUCER_BOOTSTRAP_ADDRESS ="kafka.producer.bootstrap.address";
	public static final String KAFKA_CONSUMER_CONCURRENCY="kafka.consumer.concurrency";
	public static final String KAFKA_AUTO_OFFSET_RESET="kafka.auto.offset.reset";
	public static final String KAFKA_DEFAULT_GROUP_ID="kafka." + "default.consumer.group.id";
	

	//miscellaneous
	public static final String HYPHEN = "-";
	public static final String RUPEES_SYMBOL = "Rs. ";
	public static final String BLANK = "";
	public static final String COMMA = ",";
	public static final String COMMA_WITH_WHITESPACE = "\\s*,+\\s*";
	public static final String DOUBLE_QUOTES = "\"";
	public static final String PIPE_SEPARATOR = " | ";
	public static final String INTEGER_REGEX = "\\d+";
	public static final String AR_SITE_ID = "-1";
	public static final long UNIQUE_REF_KEY_FOR_ARINVOICE = 0l;
	public static final String DUE_TEXT_LOWERCASE = "due";
	public static final String ADVANCE_TEXT_LOWERCASE = "advance";
	public static final long UNIQUE_REF_KEY_FOR_CREDIT_NOTE = 0l;

	public static final String STATUS_HEADER_NAME = "Status";
	public static final String DUE_DATE_HEADER = "Due Date";
	public static final String FAILURE_REASON_HEADER_NAME = "Failure Reason";

	public static final String ADVANCE_TEXT_CAMELCASE = "Advance";
	public static final String DUE_TEXT_CAMELCASE = "Due";
	public static final String DOT = ".";
	public static String CONTENT_DISPOSITION = "attachment;filename=";
	public static final String RESULT = "_Result";
	public static final String ON = "On";
	public static final String OFF ="Off";

	public static final Short FONT_HEIGHT=12;
}
