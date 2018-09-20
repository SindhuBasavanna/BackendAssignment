package com.rabobank.csp.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.csp.constant.CustomerStatementConstants;
import com.rabobank.csp.model.ResponseStatus;
import com.rabobank.csp.model.Record;
import com.rabobank.csp.service.ParserService;
import com.rabobank.csp.service.ValidationService;

/**
 * 
 * @author sindhu
 *
 */
@RestController
public class CustomerStatementController {
	@Autowired
	private ValidationService validatorService;

	@Autowired
	private ParserService extractorService;

	@RequestMapping(value = "/processStatment", method = RequestMethod.POST)
	public @ResponseBody ResponseStatus customerStatement(
			@RequestParam("file") MultipartFile multiPartFile) throws Exception {
		ResponseStatus appResponse = new ResponseStatus();
		if (!multiPartFile.isEmpty()) {
			if (multiPartFile.getContentType().equalsIgnoreCase(
					CustomerStatementConstants.FILE_TYPE_CSV)) {
				List<Record> errorRecords = new ArrayList<Record>();
				List<Record> extractedRecords = extractorService
						.parseFromCSV(getLocalFile(multiPartFile));
				errorRecords.addAll(validatorService
						.getNonUniqueRecords(extractedRecords));
				errorRecords.addAll(validatorService
						.getFailedBalanceRecord(extractedRecords));
				if (!errorRecords.isEmpty()) {
					appResponse
							.setResponseCode(CustomerStatementConstants.HTTP_SUCCESS_CODE);
					appResponse
							.setResponseMessage(CustomerStatementConstants.VALIDATION_ERROR);
					appResponse.setRecords(errorRecords);
				} else {
					appResponse
							.setResponseCode(CustomerStatementConstants.HTTP_SUCCESS_CODE);
					appResponse
							.setResponseMessage(CustomerStatementConstants.VALIDATION_ERROR);
				}
			} else if (multiPartFile.getContentType().equalsIgnoreCase(
					CustomerStatementConstants.FILE_TYPE_XML)) {
				List<Record> errorRecords = new ArrayList<Record>();
				List<Record> extractedRecords = extractorService
						.parseFromXML(getLocalFile(multiPartFile));
				errorRecords.addAll(validatorService
						.getNonUniqueRecords(extractedRecords));
				errorRecords.addAll(validatorService
						.getFailedBalanceRecord(extractedRecords));
				if (!errorRecords.isEmpty()) {
					appResponse
							.setResponseCode(CustomerStatementConstants.HTTP_SUCCESS_CODE);
					appResponse
							.setResponseMessage(CustomerStatementConstants.VALIDATION_ERROR);
					appResponse.setRecords(errorRecords);
				} else {
					appResponse
							.setResponseCode(CustomerStatementConstants.HTTP_SUCCESS_CODE);
					appResponse
							.setResponseMessage(CustomerStatementConstants.VALIDATION_ERROR);
				}
			} else {
				appResponse
						.setResponseCode(CustomerStatementConstants.HTTP_INVALIDTYPE_CODE);
				appResponse
						.setResponseMessage(CustomerStatementConstants.UNSUPORTED_FILE_FORMAT);
			}
		} else {
			appResponse
					.setResponseCode(CustomerStatementConstants.HTTP_INVALIDTYPE_CODE);
			appResponse
					.setResponseMessage(CustomerStatementConstants.INVALID_INPUT);
		}
		return appResponse;
	}

	private File getLocalFile(MultipartFile multipart) throws IOException,
			FileNotFoundException {
		File file = new File(multipart.getOriginalFilename());
		file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(multipart.getBytes());
		fos.close();
		return file;
	}

	@ExceptionHandler(Exception.class)
	public @ResponseBody ResponseStatus handleException(
			HttpServletRequest request, Exception ex) {
		ResponseStatus appResponse = new ResponseStatus();
		appResponse.setResponseCode(CustomerStatementConstants.HTTP_ERROR_CODE);
		appResponse
				.setResponseMessage(CustomerStatementConstants.UNEXPECTED_SERVER_ERROR);
		return appResponse;
	}

}
