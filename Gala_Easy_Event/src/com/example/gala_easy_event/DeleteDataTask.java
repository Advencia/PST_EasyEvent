package com.example.gala_easy_event;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class DeleteDataTask extends AsyncTask<String, Void, String>{
    private String msg;
    private final Etudiant etudiant;
	
    
    public DeleteDataTask(Etudiant etudiant, String email) {
    	this.etudiant = etudiant;
    }
    
	 @Override
	    protected String doInBackground(String... params) {
	        if(params == null) return null;
	         
	        String url = "http://10.0.2.2/delete_etudiant.php"; 
	             
	            try {	            	
	                // create http connection
	                HttpClient client = new DefaultHttpClient();
	                HttpGet httpget = new HttpGet(url);
	                 
	                // connect
	                HttpResponse response = client.execute(httpget);
	               
	            }
	            catch(IOException e){
	                msg = "No Network Connection";
	            }
	             
	            return null;
	        }
}
