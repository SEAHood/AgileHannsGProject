package com.hannsg.Stocksappfinal;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Environment;

//usually, subclasses of AsyncTask are declared inside the activity class.
//that way, you can easily modify the UI thread from here
public class DownloadFile extends AsyncTask<String, Integer, String> {
	
	public StockGatherer activity;
	
	public DownloadFile(StockGatherer myMain) {
		// TODO Auto-generated constructor stub
		activity = myMain;
	}

@Override
protected String doInBackground(String... sUrl) {
	   String success;
   try {
       URL url = new URL(sUrl[0]);
       URLConnection connection = url.openConnection();
       connection.connect();
       // this will be useful so that you can show a typical 0-100% progress bar
       int fileLength = connection.getContentLength();

       String path = Environment.getExternalStorageDirectory().getPath();
       File file = new File(path, "quotes.csv");
       // download the file
       InputStream input = new BufferedInputStream(url.openStream());
       OutputStream output = new FileOutputStream(file);

       byte data[] = new byte[1024];
       long total = 0;
       int count;
       while ((count = input.read(data)) != -1) {
           total += count;
           // publishing the progress....
           publishProgress((int) (total * 100 / fileLength));
           output.write(data, 0, count);
       }

       output.flush();
       output.close();
       input.close();
       success = "YAY";
   }
   catch (Exception e) {
	   success = "NAY";
   }
   return success;
}


	 @Override
	 protected void onPostExecute(String success) {
	    
			 activity.updateScreen(success);
		 
	 }

}
