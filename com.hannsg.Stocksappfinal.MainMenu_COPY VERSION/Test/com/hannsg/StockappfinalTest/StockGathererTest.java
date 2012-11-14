package com.hannsg.StockappfinalTest;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hannsg.Stocksappfinal.StockGatherer;

public class StockGathererTest {
	StockGatherer stockGathererTest = new StockGatherer();

	@Test
	public void getShareTest() {
		String downloadLink = stockGathererTest.getDownloadLink();
		assertEquals(
				"http://download.finance.yahoo.com/d/quotes.csv?s=BP.L,HSBA.L,EXPN.L,MKS.L,SN.L&f=sl1&e=.csv",
				downloadLink);

		boolean executeTest = stockGathererTest.getDownloadFile().execute(downloadLink);

		boolean getShareTest = stockGathererTest.getShares();
		assertTrue(getShareTest);

	}
	
	@Test 
	public void getPreviousFridayTest(){
		Calendar cTest = Calendar.getInstance();
		// Test if it's Friday .. 
		int dTest = Calendar.FRIDAY;
		assertEquals(dTest, stockGathererTest.getD());
		
		String urlTest = "http://ichart.yahoo.com/table.csv?s=";
		urlTest += stockGathererTest.getCode();
		urlTest += "&a=" + stockGathererTest.d -- + "&b=" + stockGathererTest.m + "&c=" + stockGathererTest.y;
    	urlTest += "&d=" + stockGathererTest.d + "&e=" + stockGathererTest.m + "&f=" + stockGathererTest.y;
    	urlTest += "&g=w&ignore=.csv";
    
    	assertEquals(urlTest, stockGathererTest.getURL());
     
	}
	
	@Test
	public updateScreenTest(){
		boolean updateScreen = stockGathererTest.updateScreen();
		assertTrue(updateScreen);
	}
	
}
