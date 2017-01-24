/**
 * 
 */
package com.karthik.tm.transactionManager.model;

import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Karthik M
 *
 */
@XmlRootElement
public class TransactionResponseMap {
 
	private Map<String,Map<String,Double>> allTransaction = new TreeMap<String,Map<String,Double>>();

	/**
	 * @return the allTransaction
	 */
	public Map<String, Map<String, Double>> getAllTransaction() {
		return allTransaction;
	}

	/**
	 * @param allTransaction the allTransaction to set
	 */
	public void setAllTransaction(Map<String, Map<String, Double>> allTransaction) {
		this.allTransaction = allTransaction;
	}

	/**
	 * 
	 */
	public TransactionResponseMap() {
	}

	/**
	 * @param allTransaction
	 */
	public TransactionResponseMap(Map<String, Map<String, Double>> allTransaction) {
		super();
		this.allTransaction = allTransaction;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TransactionResponseMap [allTransaction=" + allTransaction + "]";
	}
	
	
	

}
