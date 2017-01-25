/**
 * 
 */
package com.karthik.tm.transactionManager.service;

import org.apache.log4j.Logger;

/**
 * @author Karthik M
 *
 */
public class TransactionUtil {
	
	final static Logger logger = Logger.getLogger(TransactionUtil.class);

	static String getInputPopulated(){
		String args = "{\"args\": {\"uid\": 1110590645, \"token\": \"99285C7D45362B08C8175D78ACF7C480\", \"api-token\": \"AppTokenForInterview\", \"json-strict-mode\": false, \"json-verbose-response\": false}}";
		if(logger.isDebugEnabled()){
			logger.debug("Method getInputPopulated input::"+args);
		}
		return args;	
	}

	static String getInputPopulatedForProjectedTrxns(int year,int month){
		String args = "{\"args\": {\"uid\": 1110590645, \"token\": \"99285C7D45362B08C8175D78ACF7C480\", \"api-token\": \"AppTokenForInterview\", \"json-strict-mode\": false, \"json-verbose-response\": false}, \"year\":"+ year +", \"month\": "+month+"}";
		return args;	
	}

}
