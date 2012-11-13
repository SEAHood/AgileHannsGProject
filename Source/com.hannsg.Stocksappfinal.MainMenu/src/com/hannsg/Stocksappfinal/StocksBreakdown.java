package com.hannsg.Stocksappfinal;

import com.hannsg.Stocksappfinal.R;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.widget.TextView;

public class StocksBreakdown extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breakdown);
                
        com.hannsg.Stocksappfinal.StockGatherer myActivity = new com.hannsg.Stocksappfinal.StockGatherer();
        myActivity.getShares();
        
        String[] shares = myActivity.showShares();
        displayAll(shares);
        
    }
    
    public void displayAll(String[] shares) {
    	/*
    	 * BP		stocks[0]
    	 * HSBA		stocks[1]
    	 * EXPN		stocks[2]
    	 * MKS		stocks[3]
    	 * SN		stocks[4]
    	 */
    	TextView t = null;
    	for (int i = 0; i < shares.length; i++)
    	{
    		switch (i)
    		{
    		case 0:
        		t = (TextView)findViewById(R.id.val_bp);
        		break;
    		case 1:
        		t = (TextView)findViewById(R.id.val_hsba);
        		break;
    		case 2:
        		t = (TextView)findViewById(R.id.val_expn);
        		break;
    		case 3:
        		t = (TextView)findViewById(R.id.val_mks);
        		break;
    		case 4:
        		t = (TextView)findViewById(R.id.val_sn);
        		break;
    		default:
    				break;
    		}
	    	try
	    	{
	    		if (shares[i] == "ND")
	    		{
	    			t.setText("No data");
	    			t.setTextColor(Color.RED);
	    		}
	    		else if (shares[i] == "ER")
	    		{
	    			t.setText("ERROR");
	    			t.setTextColor(Color.RED);
	    		}
	    		else
	    			t.setText("£" + shares[i]);
	    	}
	    	catch (Exception ex)
	    	{
	    		t.setText("Loading...");
	    	}
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
