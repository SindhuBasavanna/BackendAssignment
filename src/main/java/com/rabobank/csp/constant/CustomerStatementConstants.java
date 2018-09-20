package com.rabobank.csp.constant;

/**
 * @author sindhu
 */
public class CustomerStatementConstants {
	public static final String FILE_TYPE_XML = "application/xml";
	public static final String FILE_TYPE_CSV = "text/csv";

	public static final String UNEXPECTED_SERVER_ERROR = "Unexpected server error.";
	public static final String VALIDATION_ERROR = "validation error.";
	public static final String VALIDATION_SUCCESS = "Validation success.";
	public static final String INVALID_INPUT = "Invalid Input.";
	public static final String UNSUPORTED_FILE_FORMAT = "Only csv or xml file allowed.";

	public static final Integer HTTP_SUCCESS_CODE = 200;
	public static final Integer HTTP_ERROR_CODE = 500;
	public static final Integer HTTP_INVALIDTYPE_CODE = 406;
}
