package com.agile.test;

import static org.junit.Assert.*;


import org.junit.Test;
import com.agile.sample.Sample;

public class SetOfShareTest {
// Unit 1 : 
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
	
	// Unit 2 : 
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
	// Unit 3 - 
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
}
