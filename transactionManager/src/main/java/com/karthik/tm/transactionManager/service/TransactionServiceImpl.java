package com.karthik.tm.transactionManager.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.karthik.tm.transactionManager.model.Transaction;
import com.karthik.tm.transactionManager.model.TransactionAverageResponseMap;
import com.karthik.tm.transactionManager.model.TransactionMonthlyResponseMap;
import com.karthik.tm.transactionManager.model.TransactionResponse;
import com.karthik.tm.transactionManager.model.TransactionResponseMap;
import com.karthik.tm.transactionManager.resource.TransactionResource;

/**
 * @author Karthik M
 *
 */
public class TransactionServiceImpl  implements TransactionService{

	TransactionHelper transactionHelper = new TransactionHelper();


	final static Logger logger = Logger.getLogger(TransactionServiceImpl.class);

	@Override
	public TransactionResponse getAllTransactions(){
		TransactionResponse transactions = null;
		WebTarget target;
		Client client = ClientBuilder.newClient();
		target = client.target(TransactionConstants.GET_ALL_TRXNS_ENDPOINT);

		if(logger.isDebugEnabled()){
			logger.debug("getAllTransactions URL endpoint"+TransactionConstants.GET_ALL_TRXNS_ENDPOINT);
		}

		String args = TransactionUtil.getInputPopulated();
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String responseString = target.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(args, MediaType.APPLICATION_JSON), String.class);
		try {
			transactions = mapper.readValue(responseString, TransactionResponse.class);
		} catch (Exception e) {
			logger.error("Exception in mapping the get all transactions response::"+e.getMessage());
		} 

		if(logger.isDebugEnabled()){
			logger.debug("Response from get all transactions endpoint::"+transactions.getTransactions());
		}

		return transactions;
	}


	@Override
	public TransactionMonthlyResponseMap getTransactionForMonth(String year, String month){
		TransactionMonthlyResponseMap transactionForTheMonth = new TransactionMonthlyResponseMap();
		TransactionResponse transactionResponse = getAllTransactions();
		List<Transaction> transactionList = transactionResponse.getTransactions();
		transactionForTheMonth= transactionHelper.getAverageForMonth(transactionList, month, year);
		return transactionForTheMonth;
	}
	@Override
	public TransactionResponseMap getTransactionsAverage() {

		TransactionResponseMap allTransactionMap = new TransactionResponseMap();
		TransactionResponse transactionResponse = getAllTransactions();
		List<Transaction> transactionList = transactionResponse.getTransactions();
		try {
			allTransactionMap = transactionHelper.getTransactionsAverage(transactionList);
		} catch (Exception e) {
			logger.error("Exception in mapping the transactions while calculating average::"+e.getMessage());
		} 
		return allTransactionMap;
	}

	@Override
	public TransactionAverageResponseMap getYearlyAverage() {

		TransactionAverageResponseMap yearlyAvgMap = new TransactionAverageResponseMap();
		TransactionResponse transactionResponse = getAllTransactions();
		List<Transaction> transactionList = transactionResponse.getTransactions();
		try {
			yearlyAvgMap = transactionHelper.getYearlyAverage(transactionList);
		} catch (Exception e) {
			logger.error("Exception in mapping the yearly average for transactions response::"+e.getMessage());
		} 
		return yearlyAvgMap;
	}

	@Override
	public TransactionResponseMap getTrxnWithoutCC(){

		TransactionResponseMap allTransactionMap = new TransactionResponseMap();
		TransactionResponse transactionResponse = getAllTransactions();
		List<Transaction> transactionList = transactionResponse.getTransactions();
		try {
			allTransactionMap = transactionHelper.getTransactionIgnoringCC(transactionList);
		} catch (Exception e) {
			logger.error("Exception in mapping the transactions after ignoring credit card transactions response::"+e.getMessage());
		} 
		return allTransactionMap;
	}

	@Override
	public TransactionResponseMap getTransactionsIgnoringDonuts(){

		TransactionResponseMap allTransactionMap = new TransactionResponseMap();
		TransactionResponse transactionResponse = getAllTransactions();
		List<Transaction> transactionList = transactionResponse.getTransactions();
		try {
			allTransactionMap = transactionHelper.getTransactionsIgnoringDonuts(transactionList);
		}  catch (Exception e) {
			logger.error("Exception in mapping the transactions after ignoring donut transactions response::"+e.getMessage());
		} 
		return allTransactionMap;
	}



	public TransactionAverageResponseMap getProjectedTransactions(){

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

		TransactionAverageResponseMap trxnAvgMap = null;
		try {
			trxnAvgMap = transactionHelper.getYearlyAverage(transactionList);
		} catch (Exception e) {
			logger.error("Exception in mapping the yearly average for projected transactions response::"+e.getMessage());
		} 

		return trxnAvgMap;

	}

	public TransactionResponse makeProjectedTransactionsCall(int year,int month){
		TransactionResponse projectedTransactions = null;
		WebTarget target;
		Client client = ClientBuilder.newClient();
		target = client.target(TransactionConstants.GET_PROJECTED_TRXNS_ENDPOINT);
		if(logger.isDebugEnabled()){
			logger.debug("getProjectedTransactions URL endpoint"+TransactionConstants.GET_PROJECTED_TRXNS_ENDPOINT);
		}

		//We are incrementing the month because service takes month from 1-12 , java Calendar gives from 0-11
		String args = TransactionUtil.getInputPopulatedForProjectedTrxns(year,month+1);
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String responseString = target.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(args, MediaType.APPLICATION_JSON), String.class);
		try{
			projectedTransactions = mapper.readValue(responseString, TransactionResponse.class);
		}
		catch (Exception e) {
			logger.error("Exception in mapping the get projected transactions response::"+e.getMessage());
		} 

		if(logger.isDebugEnabled()){
			logger.debug("Response from get projected transactions endpoint::"+projectedTransactions.getTransactions());
		}

		return projectedTransactions;
	}

}
