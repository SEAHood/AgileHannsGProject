package com.hannsg.Stocksappfinal;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Portfolio extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_portfolio, menu);
        return true;
    }
}
