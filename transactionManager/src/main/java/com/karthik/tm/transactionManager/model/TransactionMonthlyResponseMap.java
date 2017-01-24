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
public class TransactionMonthlyResponseMap {
	
	private Map<String,Double> yearlyAverageMap = new TreeMap<String,Double>();

	
	/**
	 * @return the yearlyAverageMap
	 */
	public Map<String, Double> getYearlyAverageMap() {
		return yearlyAverageMap;
	}

	/**
	 * @param yearlyAverageMap the yearlyAverageMap to set
	 */
	public void setYearlyAverageMap(Map<String, Double> yearlyAverageMap) {
		this.yearlyAverageMap = yearlyAverageMap;
	}
 
	/**
	 * 
	 */
	public TransactionMonthlyResponseMap() {
	}

	/**
	 * @param yearlyAverageMap
	 */
	public TransactionMonthlyResponseMap(Map<String, Double> yearlyAverageMap) {
		super();
		this.yearlyAverageMap = yearlyAverageMap;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TransactionMonthlyResponseMap [yearlyAverageMap=" + yearlyAverageMap + "]";
	}

	

}
