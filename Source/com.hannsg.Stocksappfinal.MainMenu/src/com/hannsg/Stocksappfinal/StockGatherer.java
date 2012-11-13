package com.hannsg.Stocksappfinal;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;

import com.hannsg.Stocksappfinal.R;
import com.hannsg.Stocksappfinal.R.id;
import com.hannsg.Stocksappfinal.R.layout;
import com.hannsg.Stocksappfinal.R.menu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StockGatherer extends Activity {
	
	public StockGatherer() {
		
	}

	// declare the dialog as a member field of your activity
	//ProgressDialog mProgressDialog;
	
	static int BP = 192;
	static int HSBA = 343;
	static int EXPN = 258;
	static int MKS = 485;
	static int SN = 1219;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);
        
     // instantiate it within the onCreate method
    	mProgressDialog = new ProgressDialog(StockGatherer.this);
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
		});*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    
    public void getShares()
    {
    	// execute this when the downloader must be fired
    	DownloadFile downloadFile = new DownloadFile(this);
    	downloadFile.execute("http://download.finance.yahoo.com/d/quotes.csv?s=BP.L,HSBA.L,EXPN.L,MKS.L,SN.L&f=sl1&e=.csv");
    	
    	
    	
    }
    
    
    //CODES
    //BP.L, HSBA.L, EXPN.L, MKS.L, SN.L
    public void getPreviousFriday(String code)
    {
    	Calendar c = Calendar.getInstance();
    	
    	while (c.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY)
    	{
    		c.set(c.DAY_OF_MONTH, c.DAY_OF_MONTH - 1);
    	}
    	
    	int d = c.get(Calendar.DAY_OF_MONTH);
    	int m = c.get(Calendar.MONTH);
    	int y = c.get(Calendar.YEAR);
    	
    	DownloadFile downloadFile = new DownloadFile(this);
    	String url = "http://ichart.yahoo.com/table.csv?s=" + code;
    	url += "&a=" + d-- + "&b=" + m + "&c=" + y;
    	url += "&d=" + d + "&e=" + m + "&f=" + y;
    	url += "&g=w&ignore=.csv";
    	
    	downloadFile.execute(url);
    }
   
    public void updateScreen(String success)
    {
    	showShares();
    }
    
    
    public String[] showShares()
    //public String[] showShares()
    {
    	StringBuilder text = new StringBuilder();
    	//TextView output = new TextView(this);
    	
    	//output = (TextView)findViewById(R.id.textView1);
    	
    	BufferedReader br;
    	
    	String path = Environment.getExternalStorageDirectory().getPath();
    	
    	try 
    	{
    		br = new BufferedReader(new FileReader(path + "/quotes.csv"));
    		
    		String line;
    		String[] stocks = new String[5];
    		
    		int line_counter = 0;
    		
    		while ((line = br.readLine()) != null)
    		{
    			
    			//Split up line
    			//[0] = Stock Code
    			//[1] = Current Stock Value
    			String delims = "[,]+";
    			String[] csv_read = line.split(delims);
    			
    			//text.append(csv_read[0]);
    			
    			
    			if (csv_read[1] == "N/A")
    			{
    				stocks[line_counter] = "ND";
    				
    			}
    			else
    			{
	    			float stockWorth = 0.0f;
	    			try
	    			{
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
	    			}
	    			catch (Exception ex)
	    			{
	    				stocks[line_counter] = "ER";
	    			}
	    			
	    			// convert to £
	    			stockWorth /= 100;
	    			
	    			//text.append(" - Total value: £" + addCommas(stockWorth));
	    			
	    			// add value of current stock to array of totals
	    			stocks[line_counter] = addCommas(stockWorth);
	    			
	    			    			
	    			//text.append('\n');
	    			line_counter++;
    			}
    		}
    		
    		if (stocks[0] == null)
    		{
    			stocks[0] = "CE";
    			br.close();
    			return stocks;
    		}
    		
    		//Get total worth in pence
    		float totalWorth = calculateTotalWorth(stocks);
    		
    		//Convert to £
    		totalWorth /= 100;
    		
    		//for (int i = 0; i < stocks.length; i++)
    		//	stocks[i] = addCommas(totalWorth);
    		
    		//text.append('\n');
    		//text.append("Your total value is: £" + addCommas(totalWorth));
    		
    		//text.append("BP: £"+stocks[0]+'\n'+"HSBA: £"+stocks[1]+'\n'+"EXPN: £"+stocks[2]+'\n'+"MKS: £"+stocks[3]+'\n'+"SN: £"+stocks[4]+'\n'+"TOTAL: £"+stocks[5]);
    		br.close();
    		return stocks;
    	}
    	catch (Exception ex)
    	{
    		System.out.println("Error in showShares method. MSG: " + ex.getMessage());
    		return null;
    	}
    	
    	//output.setText(text);
    }
    
    
    private String addCommas(float val)
    {
    	/*
    	 * Adds commas between sets of 3 digits in
    	 * values greater than £1000
    	 * returns formatted value as a string.
    	 * (horrible hack, but whatever)
    	 */
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


