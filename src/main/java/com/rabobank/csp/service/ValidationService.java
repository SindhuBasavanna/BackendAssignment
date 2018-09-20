package com.rabobank.csp.service;

import java.util.List;

import com.rabobank.csp.model.Record;

/**
 * 
 * @author sindhu
 *
 */
public interface ValidationService {
	public List<Record> getNonUniqueRecords(List<Record> records);

	public List<Record> getFailedBalanceRecord(List<Record> records);
}
