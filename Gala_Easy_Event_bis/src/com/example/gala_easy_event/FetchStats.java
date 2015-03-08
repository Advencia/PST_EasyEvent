package com.example.gala_easy_event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class FetchStats extends AsyncTask<String, Void, String>{
	private String msg;
	private InputStream is;
	
	private FetchStatsListener listener;
	
	public FetchStats(FetchStatsListener listener) {
		this.listener = listener;
	}
	
    @Override
    protected String doInBackground(String... params) {
        if(params == null) return null;
         
        String url = "http://10.0.2.2/statistiques.php";
        
            try {              
                HttpClient client = new DefaultHttpClient();
                HttpGet httpget = new HttpGet(url);
                HttpResponse response = client.execute(httpget);
                HttpEntity entity = response.getEntity();
               
                if(entity == null) {
                    msg = "No response from server";
                    return null;       
                }
              
                // get response content and convert it to json string
                InputStream is = entity.getContent();
                Log.d("debug", "2");
                return streamToString(is);
            }
            catch(IOException e){
                msg = "No Network Connection";
            }             
            return null;
        }
         
    @Override
    protected void onPostExecute(String sJson) {
        if(sJson == null) { 
           return;
        }       
         
        try {
            JSONArray aJson = new JSONArray(sJson);
            ArrayList<Stats> apps = new ArrayList<Stats>();
             
            for(int i=0; i<aJson.length(); i++) {
                JSONObject json = aJson.getJSONObject(i);
                Stats app = new Stats();
                app.setSimple(json.getString("simple"));
                app.setBouteille(json.getString("bouteille"));
                app.setVip(json.getString("vip")); 
                
                apps.add(app);
            }
            
            if(listener != null){
            	listener.onStatsComplete(apps);
            }
        } 
        	catch (JSONException e) {
        		msg = "Invalid response"; 
        		listener.onStatsFailure(msg);
        		return;
        }       
    }
    
    public String streamToString(final InputStream is) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
         
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        }
        catch (IOException e) {
            throw e;
        }
        finally {          
            try {
                is.close();
            }
            catch (IOException e) {
                throw e;
            }
        }
         
        return sb.toString();
    }

}
