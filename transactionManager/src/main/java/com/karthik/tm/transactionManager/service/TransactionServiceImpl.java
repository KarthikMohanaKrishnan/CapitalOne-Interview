package com.karthik.tm.transactionManager.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.karthik.tm.transactionManager.model.Transaction;
import com.karthik.tm.transactionManager.model.TransactionAverageResponseMap;
import com.karthik.tm.transactionManager.model.TransactionMonthlyResponseMap;
import com.karthik.tm.transactionManager.model.TransactionResponse;
import com.karthik.tm.transactionManager.model.TransactionResponseMap;

/**
 * @author Karthik M
 *
 */
public class TransactionServiceImpl  implements TransactionService{

	TransactionHelper transactionHelper = new TransactionHelper();

	@Override
	public TransactionResponse getAllTransactions()
			throws JsonParseException, JsonMappingException, IOException {
		TransactionResponse transactions = null;
		WebTarget target;
		Client client = ClientBuilder.newClient();
		target = client.target(TransactionConstants.GET_ALL_TRXNS_ENDPOINT);
		String args = TransactionUtil.getInputPopulated();
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String responseString = target.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(args, MediaType.APPLICATION_JSON), String.class);
		transactions = mapper.readValue(responseString, TransactionResponse.class);
		return transactions;
	}


	@Override
	public TransactionMonthlyResponseMap getTransactionForMonth(String year, String month) throws JsonParseException, JsonMappingException, IOException {

		TransactionMonthlyResponseMap transactionForTheMonth = new TransactionMonthlyResponseMap();
		TransactionResponse transactionResponse = getAllTransactions();
		List<Transaction> transactionList = transactionResponse.getTransactions();
		transactionForTheMonth= transactionHelper.getAverageForMonth(transactionList, month, year);
		return transactionForTheMonth;
	}
	@Override
	public TransactionResponseMap getTransactionsAverage() throws JsonParseException, JsonMappingException, IOException, ParseException {

		TransactionResponseMap allTransactionMap = new TransactionResponseMap();
		TransactionResponse transactionResponse = getAllTransactions();
		List<Transaction> transactionList = transactionResponse.getTransactions();
		allTransactionMap = transactionHelper.getTransactionsAverage(transactionList);
		return allTransactionMap;
	}

	@Override
	public TransactionAverageResponseMap getYearlyAverage() throws JsonParseException, JsonMappingException, IOException, ParseException {

		TransactionAverageResponseMap yearlyAvgMap = new TransactionAverageResponseMap();
		TransactionResponse transactionResponse = getAllTransactions();
		List<Transaction> transactionList = transactionResponse.getTransactions();
		yearlyAvgMap = transactionHelper.getYearlyAverage(transactionList);
		return yearlyAvgMap;
	}

	@Override
	public TransactionResponseMap getTrxnWithoutCC() throws JsonParseException, JsonMappingException, IOException, ParseException {

		TransactionResponseMap allTransactionMap = new TransactionResponseMap();
		TransactionResponse transactionResponse = getAllTransactions();
		List<Transaction> transactionList = transactionResponse.getTransactions();
		allTransactionMap = transactionHelper.getTransactionIgnoringCC(transactionList);
		return allTransactionMap;
	}

	@Override
	public TransactionResponseMap getTransactionsIgnoringDonuts() throws JsonParseException, JsonMappingException, IOException, ParseException {

		TransactionResponseMap allTransactionMap = new TransactionResponseMap();
		TransactionResponse transactionResponse = getAllTransactions();
		List<Transaction> transactionList = transactionResponse.getTransactions();
		allTransactionMap = transactionHelper.getTransactionsIgnoringDonuts(transactionList);
		return allTransactionMap;
	}



	public TransactionAverageResponseMap getProjectedTransactions() throws JsonParseException, JsonMappingException, IOException, ParseException {

		Calendar now = Calendar.getInstance();  
		int currentMonth = now.get(Calendar.MONTH); 
		List<Transaction> projectedTrxnsMasterList = new ArrayList<Transaction>();
		for(int i = 2017; i<2019;i++)
		{
			for(int j=0;j<12;j++)
			{
				if(j>=currentMonth){
					TransactionResponse response = new TransactionResponse();
					response = makeProjectedTransactionsCall(i,j);
					projectedTrxnsMasterList.addAll(response.getTransactions());
				}
			}
		}

		TransactionResponse transactionResponse = getAllTransactions();
		List<Transaction> transactionList = transactionResponse.getTransactions();
		transactionList.addAll(projectedTrxnsMasterList);
		
		TransactionAverageResponseMap trxnAvgMap = transactionHelper.getYearlyAverage(transactionList);
		return trxnAvgMap;

	}

	public TransactionResponse makeProjectedTransactionsCall(int year,int month)
			throws JsonParseException, JsonMappingException, IOException {
		TransactionResponse projectedTransactions = null;
		WebTarget target;
		Client client = ClientBuilder.newClient();
		target = client.target(TransactionConstants.GET_PROJECTED_TRXNS_ENDPOINT);
		String args = TransactionUtil.getInputPopulatedForProjectedTrxns(year,month+1);
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String responseString = target.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(args, MediaType.APPLICATION_JSON), String.class);
		projectedTransactions = mapper.readValue(responseString, TransactionResponse.class);
		return projectedTransactions;
	}

}
