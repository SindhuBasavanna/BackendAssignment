package com.rabobank.csp.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rabobank.csp.model.Record;
import com.rabobank.csp.service.ValidationService;

/**
 * 
 * @author sindhu
 *
 */
@Service
public class ValidatorServiceImpl implements ValidationService {

	@Override
	public List<Record> getNonUniqueRecords(List<Record> records) {
		Map<Integer, Record> uniqeRecords = new HashMap<Integer, Record>();
		List<Record> duplicateRecords = new ArrayList<Record>();
		for (Record record : records) {
			if (uniqeRecords.containsKey(record.getTransactionRef())) {
				duplicateRecords.add(record);
			} else {
				uniqeRecords.put(record.getTransactionRef(), record);
			}
		}
		List<Record> finalDuplicateRecords = new ArrayList<Record>();
		finalDuplicateRecords.addAll(duplicateRecords);
		for (Record record : duplicateRecords) {
			if (null != uniqeRecords.get(record.getTransactionRef())) {
				finalDuplicateRecords.add(uniqeRecords.get(record
						.getTransactionRef()));
				uniqeRecords.remove(record.getTransactionRef());
			}
		}
		return finalDuplicateRecords;
	}

	@Override
	public List<Record> getFailedBalanceRecord(List<Record> records) {
		List<Record> endBalanceErrorRecords = new ArrayList<Record>();
		for (Record record : records) {
			if((record.getStartBalance()*100 + record.getMutation()*100)/100 != record.getEndBalance()){
			/*if (Math.round((record.getStartBalance() + record.getMutation())) != Math
					.round(record.getEndBalance())) {*/
				endBalanceErrorRecords.add(record);
			}
		}
		return endBalanceErrorRecords;
	}

}
