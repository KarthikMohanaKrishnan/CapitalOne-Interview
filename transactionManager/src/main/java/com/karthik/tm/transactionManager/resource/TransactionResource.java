package com.karthik.tm.transactionManager.resource;

import java.io.IOException;
import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.karthik.tm.transactionManager.model.TransactionAverageResponseMap;
import com.karthik.tm.transactionManager.model.TransactionMonthlyResponseMap;
import com.karthik.tm.transactionManager.model.TransactionResponse;
import com.karthik.tm.transactionManager.model.TransactionResponseMap;
import com.karthik.tm.transactionManager.service.TransactionServiceImpl;

/**
 * @author Karthik M
 * Root resource (exposed at "/transactions" path)
 */
@Path("/transactions")
public class TransactionResource {
	
	TransactionServiceImpl trxnService = new TransactionServiceImpl();
	
	
    @GET  
    @Path("/GetAllTransaction")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response getAllTransactions() throws JsonParseException, JsonMappingException, IOException { 
		TransactionResponse trxns = trxnService.getAllTransactions();
		return  Response.ok(trxns, MediaType.APPLICATION_JSON).build();
	} 
    
    @GET  
    @Path("/GetProjectedTransactions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response getProjectedTransactions() throws JsonParseException, JsonMappingException, IOException, ParseException { 
    	TransactionAverageResponseMap trxns = trxnService.getProjectedTransactions();
		return  Response.ok(trxns, MediaType.APPLICATION_JSON).build();
	} 
    
    @GET  
    @Path("/GetTransactions/{month}/{year}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response getSpecificTransactionsForMonth(@PathParam("month") String month,
		    @PathParam("year") String year) throws JsonParseException, JsonMappingException, IOException { 
    	TransactionMonthlyResponseMap transactionForTheMonth = new TransactionMonthlyResponseMap();
    	transactionForTheMonth = trxnService.getTransactionForMonth(year,month);
		return  Response.ok(transactionForTheMonth, MediaType.APPLICATION_JSON).build();
	} 
    
    @GET  
    @Path("/GetTransactionsAggregate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response getTransactionForAggregate() throws JsonParseException, JsonMappingException, IOException, ParseException { 
    	TransactionResponseMap allTransactionMap = trxnService.getTransactionsAverage();
		return  Response.ok(allTransactionMap, MediaType.APPLICATION_JSON).build();
	} 
    
    @GET  
    @Path("/GetYearlyAvg")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response getYearlyAvg() throws JsonParseException, JsonMappingException, IOException, ParseException { 
    	TransactionAverageResponseMap avgMap = trxnService.getYearlyAverage();
		return  Response.ok(avgMap, MediaType.APPLICATION_JSON).build();
	} 
    
    @GET  
    @Path("/GetTrxnsAggregateIgnoringCC")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response getTrxnsAggregateIgnoringCC() throws JsonParseException, JsonMappingException, IOException, ParseException { 
    	TransactionResponseMap allTransactionMap = trxnService.getTrxnWithoutCC();
		return  Response.ok(allTransactionMap, MediaType.APPLICATION_JSON).build();
	} 
    
    @GET  
    @Path("/GetTrxnsAggregateIgnoringDonuts")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response getTrxnsAggregateIgnoringDonuts() throws JsonParseException, JsonMappingException, IOException, ParseException { 
    	TransactionResponseMap allTransactionMap = trxnService.getTransactionsIgnoringDonuts();
		return  Response.ok(allTransactionMap, MediaType.APPLICATION_JSON).build();
	} 
}
