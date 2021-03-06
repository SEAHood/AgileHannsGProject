package com.stocks.hannsg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class StocksInformationActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(StocksInformationActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            	
            	if (position == 0) {
            		
            		/*********************************************************************************
            		 *CHANGE THIS CODE FROM "NEXTACTIVITY" TO CALL THE PORTFOLIO TOTAL VALUE ACTIVITY*
            		 *********************************************************************************/

            		/*Intent myIntent = new Intent(StocksInformationActivity.this, com.example.my.first.app.MainActivity.class);
            		StocksInformationActivity.this.startActivity(myIntent);*/
            		
            	} else if (position == 1) {
            		
            		/****************************************************************************
            		 *CHANGE THIS CODE FROM "NEXTACTIVITY" TO CALL THE STOCKS BREAKDOWN ACTIVITY*
            		 ****************************************************************************/

            		/*Intent myIntent = new Intent(StocksInformationActivity.this, NextActivity.class);
            		StocksInformationActivity.this.startActivity(myIntent);*/
            		
            	}
            }
        });
    }
    
    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {  // if it's not recycled, initialise some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(230, 230));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 20, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

        // references to our images
        private Integer[] mThumbIds = {
        		R.drawable.dollar, R.drawable.chart
        };
    }
}