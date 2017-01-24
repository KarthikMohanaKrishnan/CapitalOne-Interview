/**
 * 
 */
package com.karthik.tm.transactionManager.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Karthik M
 *
 */
@XmlRootElement
public class TransactionResponse {
 
	private List<Transaction> transactions = new ArrayList<Transaction>();
	
	private String error;



	/**
	 * @return the transactions
	 */
	public List<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * 
	 */
	public TransactionResponse() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TransactionResponse [transactionList=" + transactions + ", error=" + error + "]";
	}
	

}
