package com.agile.test;

import static org.junit.Assert.*;


import org.junit.BeforeClass;
import org.junit.Test;
import com.agile.sample.Sample;

public  class StockTest {
	@BeforeClass
	public void connect(){
		
	}
	// Function 1 : How much set of shares that worth in total 
	
	// Unit 1 : Calculate how much set of shares that worth in total 
	@Test
	public void profitPerShareTest() {
		double profitPerSetOfShareX = Sample.profitPerShare(2, 2.25);
		double profitPerSetOfShareY = Sample.profitPerShare(2, 2.5);
		assertTrue(profitPerSetOfShareX == 4.5);
		assertTrue(profitPerSetOfShareY == 5);
	
			
	}
	
	@Test
	public void totalProfitTest() {
		double totalProfit = Sample.totalProfit();
		assertTrue(totalProfit == 9.5);
	}
	
	// Unit 2 : Update prices  
	@Test
	public void indexTest(){
		assertNotNull(Sample.index);
	}
	@Test
	public void buyValueTest(){
		assertNotNull(Sample.buyValue);
		assertTrue(Sample.buyValue >=0);
	
		assertNotNull(Sample.sellValue);
		assertTrue(Sample.sellValue >=0);
		
		assertNotNull(Sample.trending_percent);
		
	}
	// Unit 3 - Error checking 
	@Test
	public void connectionTest(){
		
	}
	
	@Test 
	public void errorCheckingTest(){
		assertNotNull(Sample.noInternetMessage);
		assertNotNull(Sample.noDataMessage);
		assertNotNull(Sample.noFeedMessage);
		assertNotNull(Sample.invalidDepositMessage);
		assertNotNull(Sample.noProfitInfoMessage);
		
	}
	
// FUNCTION : Estimate how much portfolio is worth in total

	// Unit 1 : Displaying total of all stock 
	@Test
	public void portfolioWorthTest(){
		assertNotNull(Sample.stockList);
		
	}
	
	// Unit 2 : Total must be in pounds 
	@Test
	public void transferToPoundsTest(){
		double resultInPounds = 0 ; 
		resultInPounds = Sample.dolarsToPounds((Sample.stockList.get(0)).getPrice());
		resultInPounds = Sample.eurosToPounds(Sample.stockList.get(1).getPrice());
		assertTrue(resultInPounds >= 0);
		
	}
	// Unit 3 : error checking, that is same error checking test above 
	
	// Unit 4 : Compare from previous Fridaythat include portfolioWorthTest() above and capturePortfolioTest()
	@Test 
	public void capturePortfolioTest(){
		assertNotNull(Sample.previousPortfolioCapture);
		
	}
	
}
