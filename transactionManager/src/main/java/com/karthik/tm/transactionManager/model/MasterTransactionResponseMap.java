/**
 * 
 */
package com.karthik.tm.transactionManager.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Karthik M
 *
 */
@XmlRootElement
public class MasterTransactionResponseMap {
 
	TransactionAverageResponseMap transactionAverageResponseMap = new TransactionAverageResponseMap();
	
	TransactionResponseMap transactionResponseMap = new TransactionResponseMap();

	/**
	 * @return the transactionAverageResponseMap
	 */
	public TransactionAverageResponseMap getTransactionAverageResponseMap() {
		return transactionAverageResponseMap;
	}

	/**
	 * @param transactionAverageResponseMap the transactionAverageResponseMap to set
	 */
	public void setTransactionAverageResponseMap(TransactionAverageResponseMap transactionAverageResponseMap) {
		this.transactionAverageResponseMap = transactionAverageResponseMap;
	}

	/**
	 * @return the transactionResponseMap
	 */
	public TransactionResponseMap getTransactionResponseMap() {
		return transactionResponseMap;
	}

	/**
	 * @param transactionResponseMap the transactionResponseMap to set
	 */
	public void setTransactionResponseMap(TransactionResponseMap transactionResponseMap) {
		this.transactionResponseMap = transactionResponseMap;
	}

	/**
	 * 
	 */
	public MasterTransactionResponseMap() {
	}

	/**
	 * @param transactionAverageResponseMap
	 * @param transactionResponseMap
	 */
	public MasterTransactionResponseMap(TransactionAverageResponseMap transactionAverageResponseMap,
			TransactionResponseMap transactionResponseMap) {
		super();
		this.transactionAverageResponseMap = transactionAverageResponseMap;
		this.transactionResponseMap = transactionResponseMap;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MasterTransactionResponseMap [transactionAverageResponseMap=" + transactionAverageResponseMap
				+ ", transactionResponseMap=" + transactionResponseMap + "]";
	}
	
	

}
