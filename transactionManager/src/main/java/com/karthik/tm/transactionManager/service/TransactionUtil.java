/**
 * 
 */
package com.karthik.tm.transactionManager.service;

/**
 * @author Karthik M
 *
 */
public class TransactionUtil {

	static String getInputPopulated(){
		String args = "{\"args\": {\"uid\": 1110590645, \"token\": \"99285C7D45362B08C8175D78ACF7C480\", \"api-token\": \"AppTokenForInterview\", \"json-strict-mode\": false, \"json-verbose-response\": false}}";
		return args;	
	}

	static String getInputPopulatedForProjectedTrxns(int year,int month){
		String args = "{\"args\": {\"uid\": 1110590645, \"token\": \"99285C7D45362B08C8175D78ACF7C480\", \"api-token\": \"AppTokenForInterview\", \"json-strict-mode\": false, \"json-verbose-response\": false}, \"year\":"+ year +", \"month\": "+month+"}";
		return args;	
	}

}
