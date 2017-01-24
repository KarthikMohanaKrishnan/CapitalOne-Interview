/**
 * 
 */
package com.karthik.tm.transactionManager.service;

import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.karthik.tm.transactionManager.model.Transaction;
import com.karthik.tm.transactionManager.model.TransactionAverageResponseMap;
import com.karthik.tm.transactionManager.model.TransactionMonthlyResponseMap;
import com.karthik.tm.transactionManager.model.TransactionResponseMap;

/**
 * @author Karthik M
 *
 */
public class TransactionHelper {

	public TransactionMonthlyResponseMap getAverageForMonth(List<Transaction> transactionList,String month, String year)
	{
		Map<String,Double> transactionMap = new HashMap<>();
		Double spending = 0.0;
		Double income = 0.0;
		for(Transaction transaction : transactionList)
		{
			try{
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
				String transactionTime = transaction.getTransactionTime();

				Date txnDate = formatter.parse(transactionTime);

				SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy");
				String txnYear = dayFormat.format(txnDate);

				SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
				String txnMonth = monthFormat.format(txnDate);
				if(txnYear.equalsIgnoreCase(year) && txnMonth.equalsIgnoreCase(month))
				{
					if(transaction.getAmount()==0)
					{
						continue;
					}
					else if(transaction.getAmount()>0)
					{
						income += transaction.getAmount();
					}
					else if(transaction.getAmount()<0)
					{
						spending += transaction.getAmount();
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		transactionMap.put("Spending",Math.abs(spending)/10000);
		transactionMap.put("Income", income/10000);

		TransactionMonthlyResponseMap transactionMonthlyResponseMap = new TransactionMonthlyResponseMap();
		transactionMonthlyResponseMap.setYearlyAverageMap(transactionMap);

		return transactionMonthlyResponseMap;
	}

	public TransactionResponseMap getTransactionsAverage(List<Transaction> transactionList) throws ParseException
	{
		TransactionResponseMap responseMap = new TransactionResponseMap();
		Map<String,Map<String,Double>> allTransaction = new TreeMap<String,Map<String,Double>>();


		for(int i = 2013; i<2018;i++)
		{
			String year = String.format("%04d",i);
			Double spending = 0.0;
			Double income = 0.0;

			for(int j=1;j<13;j++)
			{

				Map<String,Double> transactionMap = new TreeMap<String,Double>();
				String month = String.format("%02d", j);

				for(Transaction transaction : transactionList)
				{

					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
					String transactionTime = transaction.getTransactionTime();

					Date txnDate = formatter.parse(transactionTime);

					SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy");
					String txnYear = dayFormat.format(txnDate);

					SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
					String txnMonth = monthFormat.format(txnDate);
					if(txnYear.equalsIgnoreCase(year) && txnMonth.equalsIgnoreCase(month))
					{
						if(transaction.getAmount()==0)
						{
							continue;
						}
						else if(transaction.getAmount()>0)
						{
							income += transaction.getAmount();
						}
						else if(transaction.getAmount()<0)
						{
							spending += transaction.getAmount();
						}
					}


				}
				if(spending!=0.0)
					transactionMap.put("Spending",spending/10000);
				if(income!=0.0)
					transactionMap.put("Income", income/10000);
				spending = 0.0;
				income = 0.0;
				allTransaction.put(""+year+"-"+month,transactionMap);
			}

		}
		responseMap.setAllTransaction(allTransaction);
		return responseMap;
	}

	public TransactionAverageResponseMap getYearlyAverage(List<Transaction> transactionList) throws ParseException
	{
		Map<String,Map<String,Double>> avgTrxnMap = new TreeMap<String,Map<String,Double>>();

		for(int i = 2013; i<2018;i++)
		{
			String year = String.format("%04d",i);
			Double spendingTotal = 0.0;
			Double incomeTotal = 0.0;
			Map<String,Double> avgTotalsMap = new TreeMap<String,Double>();

			for(Transaction transaction : transactionList)
			{

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
				String transactionTime = transaction.getTransactionTime();

				Date txnDate = formatter.parse(transactionTime);

				SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy");
				String txnYear = dayFormat.format(txnDate);

				if(txnYear.equalsIgnoreCase(year))
				{
					if(transaction.getAmount()==0)
					{
						continue;
					}
					else if(transaction.getAmount()>0)
					{
						incomeTotal += transaction.getAmount();
					}
					else if(transaction.getAmount()<0)
					{
						spendingTotal += transaction.getAmount();
					}
				}
			}

			if(spendingTotal!=0.0)
				spendingTotal=Math.abs(spendingTotal/10000);
			if(incomeTotal!=0.0)
				incomeTotal/=10000;
			Double avgSpending = (incomeTotal - spendingTotal)/12;
			avgTotalsMap.put("Spending_Total",spendingTotal);
			avgTotalsMap.put("Income_Total", incomeTotal);
			avgTotalsMap.put("Average",avgSpending);
			avgTrxnMap.put(year,avgTotalsMap);

		}

		TransactionAverageResponseMap yearlyAvgMap = new TransactionAverageResponseMap();
		yearlyAvgMap.setAllTransactionAverage(avgTrxnMap);
		return yearlyAvgMap;

	}

	public TransactionResponseMap getTransactionsIgnoringDonuts(List<Transaction> transactionList) throws ParseException
	{
		TransactionResponseMap responseMap = new TransactionResponseMap();
		Map<String,Map<String,Double>> allTransaction = new TreeMap<String,Map<String,Double>>();


		for(int i = 2013; i<2018;i++)
		{

			String year = String.format("%04d",i);
			Double spending = 0.0;
			Double income = 0.0;

			for(int j=1;j<13;j++)
			{

				Map<String,Double> transactionMap = new TreeMap<String,Double>();
				String month = String.format("%02d", j);


				for(Transaction transaction : transactionList)
				{

					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
					String transactionTime = transaction.getTransactionTime();

					Date txnDate = formatter.parse(transactionTime);

					SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy");
					String txnYear = dayFormat.format(txnDate);

					SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
					String txnMonth = monthFormat.format(txnDate);
					if(txnYear.equalsIgnoreCase(year) && txnMonth.equalsIgnoreCase(month) && !checkDonutTrxns(transaction))
					{
						if(transaction.getAmount()==0)
						{
							continue;
						}
						else if(transaction.getAmount()>0)
						{
							income += transaction.getAmount();
						}
						else if(transaction.getAmount()<0)
						{
							spending += transaction.getAmount();
						}
					}


				}
				if(spending!=0.0)
					transactionMap.put("Spending",spending/10000);
				if(income!=0.0)
					transactionMap.put("Income", income/10000);
				spending = 0.0;
				income = 0.0;
				allTransaction.put(""+year+"-"+month,transactionMap);
			}

		}
		responseMap.setAllTransaction(allTransaction);
		return responseMap;
	}

	public boolean checkDonutTrxns(Transaction transaction){
		boolean isDonutTrxn = false;
		if(transaction.getMerchant().equalsIgnoreCase("Krispy Kreme Donuts") || transaction.getMerchant().equalsIgnoreCase("DUNKIN #336784")){
			isDonutTrxn = true;
		}

		return isDonutTrxn;

	}

	public TransactionResponseMap getTransactionIgnoringCC(List<Transaction> transactionList) throws ParseException
	{
		List<Transaction> psudoTrxnList = new ArrayList<>();
		List<Transaction> transactionRemovedList = new ArrayList<>();
		transactionRemovedList.addAll(transactionList);
		for(Transaction transaction : transactionList)
		{
			Double trxnAmount = transaction.getAmount();
			transactionRemovedList.remove(transaction);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			String transactionTime = transaction.getTransactionTime();
			Date txnDate = formatter.parse(transactionTime);

			for(Transaction trxn :transactionRemovedList ){
				Double absVal = Math.abs(trxn.getAmount());
				Double currentAbsVal = Math.abs(trxnAmount);

				if(absVal.equals(currentAbsVal) && !trxn.getAmount().equals(trxnAmount)){

					long MAX_DURATION = MILLISECONDS.convert(24, HOURS);
					String currentTrxnTime = trxn.getTransactionTime();

					Date currentTxnDate = formatter.parse(currentTrxnTime);
					long duration = currentTxnDate.getTime() - txnDate.getTime();

					if (duration <= MAX_DURATION) {
						psudoTrxnList.add(trxn);
					}
				}
			}

		}

		System.out.println("Credit Card Transactions List :: "+psudoTrxnList.size());
		System.out.println("Master Transactions List :: "+transactionList.size());
		transactionList.removeAll(psudoTrxnList);
		System.out.println("After ignoring CC Transactions List :: "+transactionList.size());
		TransactionResponseMap transactionResponseMap = new TransactionResponseMap();
		transactionResponseMap=	getTransactionsAverage(transactionList);
		return transactionResponseMap;
	}
	
}
