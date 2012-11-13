package com.example.my.first.app;

import com.example.my.first.app.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                
        com.example.test.MainActivity myActivity = new com.example.test.MainActivity();
        myActivity.getShares();
        
        String[] shares = myActivity.showShares();
        displayAll(shares);
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
