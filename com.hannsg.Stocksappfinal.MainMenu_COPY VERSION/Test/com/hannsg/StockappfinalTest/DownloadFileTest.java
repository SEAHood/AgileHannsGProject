package com.hannsg.StockappfinalTest;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Assert;
import org.junit.Test;

import android.os.Environment;

import com.hannsg.Stocksappfinal.DownloadFile;

public class DownloadFileTest {
	DownloadFile downloadTest = new DownloadFile(); 
	
	@Test 
	public void connectionTest(){
		boolean connection ; 
		// All the fields need to be tested ... 
		connection = downloadTest.connect();
		assertTrue(connection);
	
	}
	
	
	@Test
	public void doInBackgroundTest() {
		String successTest = donwnloadTest.doInBackground();
		String pathTest = downloadTest.getPath(); // check the path 
		
		// is able to get the file 
		boolean getFileTest = downloadTest.getFile(pathTest);
		assertTrue(getFileTest);
		
		// Test in download the file .. 
		// Is able to get the input  
		boolean getInputTest = downloadTest.getInputStream(downloadTest.getURL().openStream());
		assertTrue(getInputTest);
		
		// Test the OutputStream is created   
		boolean getOutputStreamTest = downloadTest.getOutputStream(downloadTest.getFile());
		assertTrue(getOutputStreamTest);
		assertEquals("YAY", successTest);
		
		assertNotNull(pathTest);
		
	}

	

	
}
