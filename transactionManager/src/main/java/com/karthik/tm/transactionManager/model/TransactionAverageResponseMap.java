/**
 * 
 */
package com.karthik.tm.transactionManager.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Karthik M
 *
 */
@XmlRootElement
public class TransactionAverageResponseMap {
 
	private Map<String,Map<String,Double>> allTransactionAverage = new HashMap<>();

	/**
	 * @return the allTransactionAverage
	 */
	public Map<String, Map<String, Double>> getAllTransactionAverage() {
		return allTransactionAverage;
	}

	/**
	 * @param allTransactionAverage the allTransactionAverage to set
	 */
	public void setAllTransactionAverage(Map<String, Map<String, Double>> allTransactionAverage) {
		this.allTransactionAverage = allTransactionAverage;
	}

	/**
	 * 
	 */
	public TransactionAverageResponseMap() {
	}
	
	/**
	 * @param allTransactionAverage
	 */
	public TransactionAverageResponseMap(Map<String, Map<String, Double>> allTransactionAverage) {
		super();
		this.allTransactionAverage = allTransactionAverage;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TransactionAverageResponseMap [allTransactionAverage=" + allTransactionAverage + "]";
	}

	
	
	

}
