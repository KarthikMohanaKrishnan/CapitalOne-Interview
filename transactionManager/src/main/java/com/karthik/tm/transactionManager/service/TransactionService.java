package com.karthik.tm.transactionManager.service;

import java.io.IOException;
import java.text.ParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.karthik.tm.transactionManager.model.TransactionAverageResponseMap;
import com.karthik.tm.transactionManager.model.TransactionMonthlyResponseMap;
import com.karthik.tm.transactionManager.model.TransactionResponse;
import com.karthik.tm.transactionManager.model.TransactionResponseMap;

/**
 * @author Karthik M
 *
 */
public interface TransactionService {

	TransactionResponse getAllTransactions() throws JsonParseException, JsonMappingException, IOException;
	TransactionMonthlyResponseMap getTransactionForMonth(String year, String month) throws JsonParseException, JsonMappingException, IOException;
	TransactionResponseMap getTransactionsAverage() throws JsonParseException, JsonMappingException, IOException, ParseException;
	TransactionResponseMap getTrxnWithoutCC()
			throws JsonParseException, JsonMappingException, IOException, ParseException;
	TransactionResponseMap getTransactionsIgnoringDonuts()
			throws JsonParseException, JsonMappingException, IOException, ParseException;
	TransactionAverageResponseMap getProjectedTransactions() throws JsonParseException, JsonMappingException, IOException, ParseException;
	TransactionAverageResponseMap getYearlyAverage()
			throws JsonParseException, JsonMappingException, IOException, ParseException;
}
