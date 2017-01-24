/**
 * 
 */
package com.karthik.tm.transactionManager.model;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Karthik M
 *
 */
@XmlRootElement
public class Transaction {
	
	@JsonProperty("amount")
	Double amount;
	
	@JsonProperty("is-pending")
	boolean isPending;
	
	@JsonProperty("aggregation-time")
	String aggregationTime;
	
	@JsonProperty("account-id")
	String account_id;
	
	@JsonProperty("clear-date")
	BigInteger clearDate;

	@JsonProperty("transaction-id")
	String transactionId = null;


	@JsonProperty("raw-merchant")
	String rawMerchant;
	
	@JsonProperty("categorization")
	String categorization;


	@JsonProperty("merchant")
	String merchant;

	@JsonProperty("transaction-time")
	String transactionTime;

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the isPending
	 */
	public boolean isPending() {
		return isPending;
	}

	/**
	 * @param isPending the isPending to set
	 */
	public void setPending(boolean isPending) {
		this.isPending = isPending;
	}

	/**
	 * @return the aggregationTime
	 */
	public String getAggregationTime() {
		return aggregationTime;
	}

	/**
	 * @param aggregationTime the aggregationTime to set
	 */
	public void setAggregationTime(String aggregationTime) {
		this.aggregationTime = aggregationTime;
	}

	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return account_id;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.account_id = accountId;
	}

	/**
	 * @return the clearDate
	 */
	public BigInteger getClearDate() {
		return clearDate;
	}

	/**
	 * @param clearDate the clearDate to set
	 */
	public void setClearDate(BigInteger clearDate) {
		this.clearDate = clearDate;
	}

	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the rawMerchant
	 */
	public String getRawMerchant() {
		return rawMerchant;
	}

	/**
	 * @param rawMerchant the rawMerchant to set
	 */
	public void setRawMerchant(String rawMerchant) {
		this.rawMerchant = rawMerchant;
	}

	/**
	 * @return the categorization
	 */
	public String getCategorization() {
		return categorization;
	}

	/**
	 * @param categorization the categorization to set
	 */
	public void setCategorization(String categorization) {
		this.categorization = categorization;
	}

	/**
	 * @return the merchant
	 */
	public String getMerchant() {
		return merchant;
	}

	/**
	 * @param merchant the merchant to set
	 */
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	/**
	 * @return the transactionTime
	 */
	public String getTransactionTime() {
		return transactionTime;
	}

	/**
	 * @param transactionTime the transactionTime to set
	 */
	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	/**
	 * 
	 */
	public Transaction() {
	}

	/**
	 * @param amount
	 * @param isPending
	 * @param aggregationTime
	 * @param accountId
	 * @param clearDate
	 * @param transactionId
	 * @param rawMerchant
	 * @param categorization
	 * @param merchant
	 * @param transactionTime
	 */
	public Transaction(Double amount, boolean isPending, String aggregationTime, String accountId,
			BigInteger clearDate, String transactionId, String rawMerchant, String categorization, String merchant,
			String transactionTime) {
		super();
		this.amount = amount;
		this.isPending = isPending;
		this.aggregationTime = aggregationTime;
		this.account_id = accountId;
		this.clearDate = clearDate;
		this.transactionId = transactionId;
		this.rawMerchant = rawMerchant;
		this.categorization = categorization;
		this.merchant = merchant;
		this.transactionTime = transactionTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Transaction [amount=" + amount + ", isPending=" + isPending + ", aggregationTime=" + aggregationTime
				+ ", accountId=" + account_id + ", clearDate=" + clearDate + ", transactionId=" + transactionId
				+ ", rawMerchant=" + rawMerchant + ", categorization=" + categorization + ", merchant=" + merchant
				+ ", transactionTime=" + transactionTime + "]";
	}

}
