package com.example.gala_easy_event_ter;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class UpdateStats extends AsyncTask<String, Void, String>{
	
	private InputStream is;
	
    @Override
    protected String doInBackground(String... params) {
	  String url2 = "http://10.0.2.2/stats_update.php";   

  		try	{
  			HttpClient httpclient = new DefaultHttpClient();
  			HttpPost httppost = new HttpPost(url2);
  			HttpResponse response = httpclient.execute(httppost); 
  			HttpEntity entity = response.getEntity();
  			is = entity.getContent(); Log.d("debug","1");
  		}
  
  		catch(Exception e){
  			Log.e("Fail 1", e.toString());
  		}  
  		return null;
    }

}
