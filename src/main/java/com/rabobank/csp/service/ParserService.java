package com.rabobank.csp.service;

import java.io.File;
import java.util.List;

import com.rabobank.csp.model.Record;

/**
 * 
 * @author sindhu
 *
 */
public interface ParserService {
	public List<Record> parseFromCSV(File file) throws Exception;

	public List<Record> parseFromXML(File file) throws Exception;
}
