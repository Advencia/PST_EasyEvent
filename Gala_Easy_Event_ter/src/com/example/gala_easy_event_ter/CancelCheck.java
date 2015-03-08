package com.example.gala_easy_event_ter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;

public class CancelCheck extends AsyncTask<String, Void, String> {

	private InputStream is;
	private String result;
	private String line;
	private String email;
	private String validation = "non";
	
	public CancelCheck(String email){
		this.email = email;
	}
	
	@Override
    protected String doInBackground(String... params) {
        if(params == null) return null;
        
		Thread thread = new Thread(new Runnable(){
		@Override
    	public void run() {  

	        Log.d("verif",  validation);
	    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("validation", validation));
			nameValuePairs.add(new BasicNameValuePair("email", email));
			try {
            	HttpClient httpclient = new DefaultHttpClient();
		        HttpPost httppost = new HttpPost("http://10.0.2.2/validation.php");
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        HttpResponse response = httpclient.execute(httppost); 
		        HttpEntity entity = response.getEntity();
		        is = entity.getContent();
		        Log.e("pass 1", "connection success ");
            }
            
            catch(IOException e){
                String msg = "No Network Connection";
            }
            
            try{
	            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	            StringBuilder sb = new StringBuilder();
	            while ((line = reader.readLine()) != null){
	                sb.append(line + "\n");
	            }
	            is.close();
	            result = sb.toString();
	            Log.e("pass 2", "connection success ");
	        }
	        catch(Exception e){
	            Log.e("Fail 2", e.toString());
	        }     
        }
		});
		thread.start(); 
		 return null;
		}
}
