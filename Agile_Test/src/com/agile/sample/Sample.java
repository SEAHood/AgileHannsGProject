package com.agile.sample;

import java.util.ArrayList;

public class Sample {
// Function 1 :  How much set of shares that worth in total 
	public static ArrayList<Double> sharesList = new ArrayList<Double>();
	public static String index ;
	public static float buyValue;
	public static float sellValue;
	public static float trending_percent ; 
	public static String noInternetMessage;
	public static String noFeedMessage;
	public static String noDataMessage;
	public static String invalidDepositMessage;
	public static String noProfitInfoMessage;
	
	public static ArrayList<StockList> stockList;
	public static double usdRatio;
	private static double eurosRatio;
	public static Object previousPortfolioCapture;
	
	public static double profitPerShare(int amount, double profitPerEach) {
		double result = amount * profitPerEach;
		sharesList.add(result);
		return result;
	}

	public static double totalProfit() {
		// TODO Auto-generated method stub
		double total = 0 ;
		for(int i = 0 ; i < sharesList.size() ; i++){
			total += sharesList.get(i);
		}
		return total ; 
		
	}

	public static double dolarsToPounds(double price) {
		// TODO Auto-generated method stub
		 
		return price * usdRatio;
	}

	public static double eurosToPounds(double price) {
		// TODO Auto-generated method stub
		return price * eurosRatio;
	}
	
	 
	
}
