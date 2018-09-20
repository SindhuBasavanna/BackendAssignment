package com.rabobank.csp;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.rabobank.csp.constant.TestCaseUtil;
import com.rabobank.csp.model.Record;
import com.rabobank.csp.service.ParserService;
import com.rabobank.csp.service.ValidationService;
import com.rabobank.csp.serviceImpl.ParserServiceImpl;
import com.rabobank.csp.serviceImpl.ValidatorServiceImpl;

public class CustomerStatementProcessorTest {
	@Test
	public void parseFromCSVTest() {
		ParserService executorServiceImpl = new ParserServiceImpl();
		File inputFile = new File("records.csv");
		try {
			int totalNoOfLinesInputCSV = TestCaseUtil
					.getNumberOfLinesForCSV(inputFile);
			List<Record> getRecords = executorServiceImpl
					.parseFromCSV(inputFile);
			assertEquals(totalNoOfLinesInputCSV - 1, getRecords.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void parseFromXMLTest() {
		ParserService executorServiceImpl = new ParserServiceImpl();
		File inputFile = new File("records.xml");
		try {
			int totalNoOfLinesInputXML = 10;
			List<Record> getRecords = executorServiceImpl
					.parseFromXML(inputFile);
			assertEquals(totalNoOfLinesInputXML - 1, getRecords.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getNonUniqueRecordsTest() {
		List<Record> inputList = Arrays.asList(new Record(172833,
				"NL91RABO0315273637", 66.72, -41.74, "Clothes from Jan Bakker",
				24.98), new Record(172833, "NL43AEGO0773393871", 16.52, +43.09,
				"Clothes for Willem Dekker", 59.61));
		ValidationService validatorServiceImpl = new ValidatorServiceImpl();
		List<Record> duplicateRecords = validatorServiceImpl
				.getNonUniqueRecords(inputList);
		assertEquals(inputList.size(), duplicateRecords.size());

	}

	@Test
	public void getUniqueRecordsTest() {
		List<Record> inputList = Arrays.asList(new Record(172823,
				"NL91RABO0315273637", 66.72, -41.74, "Clothes from Jan Bakker",
				24.98), new Record(172833, "NL43AEGO0773393871", 16.52, +43.09,
				"Clothes for Willem Dekker", 59.61));
		ValidationService validatorServiceImpl = new ValidatorServiceImpl();
		List<Record> duplicateRecords = validatorServiceImpl
				.getNonUniqueRecords(inputList);
		assertEquals(0, duplicateRecords.size());

	}

	@Test
	public void getSuccessBalanceRecord() {
		List<Record> inputList = Arrays.asList(new Record(172833,
				"NL91RABO0315273637", 66.72, -41.74, "Clothes from Jan Bakker",
				108.46), new Record(172833, "NL43AEGO0773393871", 16.52,
				+43.09, "Clothes for Willem Dekker", -26.57));
		ValidationService validatorServiceImpl = new ValidatorServiceImpl();
		List<Record> endBalanceErrorRecords = validatorServiceImpl
				.getFailedBalanceRecord(inputList);
		assertEquals(inputList.size(), endBalanceErrorRecords.size());

	}

	@Test
	public void getFailedBalanceRecord() {
		List<Record> inputList = Arrays.asList(new Record(172833,
				"NL91RABO0315273637", 66.72, -41.74, "Clothes from Jan Bakker",
				24.98), new Record(172833, "NL43AEGO0773393871", 16.52, +43.09,
				"Clothes for Willem Dekker", 59.61));
		ValidationService validatorServiceImpl = new ValidatorServiceImpl();
		List<Record> endBalanceErrorRecords = validatorServiceImpl
				.getFailedBalanceRecord(inputList);
		assertEquals(0, endBalanceErrorRecords.size());
	}

}
