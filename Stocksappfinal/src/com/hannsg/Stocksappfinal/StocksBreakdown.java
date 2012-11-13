package com.hannsg.Stocksappfinal;

import com.hannsg.Stocksappfinal.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class StocksBreakdown extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                
        com.hannsg.Stocksappfinal.StocksBreakdown myActivity = new com.hannsg.Stocksappfinal.StocksBreakdown();
        //myActivity.getShares();
        
        //String[] shares = myActivity.showShares();
        //displayAll(shares);
    }
    
    public void displayAll(String[] shares) {
    	TextView t = (TextView)findViewById(R.id.val_bp);
    	t.setText(shares[0]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
