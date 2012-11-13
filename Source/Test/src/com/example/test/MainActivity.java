package com.example.test;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public MainActivity() {
		
	}

	// declare the dialog as a member field of your activity
	ProgressDialog mProgressDialog;
	
	static int BP = 192;
	static int HSBA = 343;
	static int EXPN = 258;
	static int MKS = 485;
	static int SN = 1219;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
     // instantiate it within the onCreate method
    	mProgressDialog = new ProgressDialog(MainActivity.this);
    	mProgressDialog.setMessage("A message");
    	mProgressDialog.setIndeterminate(false);
    	mProgressDialog.setMax(100);
    	mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    	
        Button button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getShares();
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    
    public void getShares()
    {
    	// execute this when the downloader must be fired
    	DownloadFile downloadFile = new DownloadFile(this);
    	downloadFile.execute("http://download.finance.yahoo.com/d/quotes.csv?s=BP.L,HSBA.L,EXPN.L,MKS.L,SN.L&f=sl1&e=.csv");
    	
    }
   
    public void updateScreen(String success)
    {
    	showShares();
    }
    
    
    //private void showShares()
    public String[] showShares()
    {
    	StringBuilder text = new StringBuilder();
    	TextView output = new TextView(this);
    	
    	output = (TextView)findViewById(R.id.textView1);
    	
    	BufferedReader br;
    	
    	String path = Environment.getExternalStorageDirectory().getPath();
    	System.out.println("PATH: " + path);
    	//float total = 0.0f;
    	try 
    	{
    		br = new BufferedReader(new FileReader(path + "/quotes.csv"));
    		
    		String line;
    		String[] stocks = new String[5];
    		String[] euanStocks = new String[6];
    		
    		int line_counter = 0;
    		
    		while ((line = br.readLine()) != null)
    		{
    			
    			//Split up line
    			//[0] = Stock Code
    			//[1] = Current Stock Value
    			String delims = "[,]+";
    			String[] csv_read = line.split(delims);
    			
    			//text.append(csv_read[0]);
    			float stockWorth = 0.0f;
    			switch(line_counter)
    			{
	    			case 0:
	    				stockWorth = Float.parseFloat(csv_read[1]) * BP;
	    				break;
	    			case 1:
	    				stockWorth = Float.parseFloat(csv_read[1]) * HSBA;
	    				break;
	    			case 2:
	    				stockWorth = Float.parseFloat(csv_read[1]) * EXPN;
	    				break;
	    			case 3:
	    				stockWorth = Float.parseFloat(csv_read[1]) * MKS;
	    				break;
	    			case 4:
	    				stockWorth = Float.parseFloat(csv_read[1]) * SN;
	    				break;
    				default:
    					break;
    			}
    			
    			// convert to £
    			stockWorth /= 100;
    			
    			//text.append(" - Total value: £" + addCommas(stockWorth));
    			
    			// add value of current stock to array of totals
    			euanStocks[line_counter] = addCommas(stockWorth);
    			stocks[line_counter] = csv_read[1];
    			    			
    			//text.append('\n');
    			line_counter++;
    		}
    		
    		//Get total worth in pence
    		float totalWorth = calculateTotalWorth(stocks);
    		
    		//Convert to £
    		totalWorth /= 100;
    		
    		euanStocks[5] = addCommas(totalWorth);
    		
    		// sam's expression which i don't understand the need for =]
    		// Math.round(totalWorth*100.0)/100.0)
    		
    		//text.append('\n');
    		//text.append("Your total value is: £" + addCommas(totalWorth));
    		
    		return euanStocks;
    	}
    	catch (IOException ex)
    	{
    		System.out.println("Error in showShares method.");
    		return null;
    	}
    	
    	//output.setText(text);
    }
    
    
    private String addCommas(float val)
    {
    	String result = "";
    	
    	result = String.format("%,.2f", val);
        	
    	return result;
    }
    
    
    private float calculateTotalWorth(String[] stocks)
    {
    	/*
    	 * BP		stocks[0]
    	 * HSBA		stocks[1]
    	 * EXPN		stocks[2]
    	 * MKS		stocks[3]
    	 * SN		stocks[4]
    	 */
    	try
    	{
	    	float totalWorth = 0.0f;
	    	
	    	totalWorth += (Float.parseFloat(stocks[0]) * BP);
	    	totalWorth += (Float.parseFloat(stocks[1]) * HSBA);
	    	totalWorth += (Float.parseFloat(stocks[2]) * EXPN);
	    	totalWorth += (Float.parseFloat(stocks[3]) * MKS);
	    	totalWorth += (Float.parseFloat(stocks[4]) * SN);
	    	
	    	return totalWorth;
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    		return 0.0f;
    	}
    }
    
    
}


