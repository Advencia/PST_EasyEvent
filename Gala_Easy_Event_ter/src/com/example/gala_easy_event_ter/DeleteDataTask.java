package com.example.gala_easy_event_ter;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class DeleteDataTask extends AsyncTask<String, Void, String>{
    private String msg;
    private Etudiant etudiant;
	private String email;
	private String nom;
	private String prenom;
	private String prevente;
	
    public DeleteDataTask(Etudiant etudiant, String email) {
    	this.etudiant = etudiant;
    	this.email = etudiant.getEmail();
    }
    
    
    public void delete (Etudiant etudiant, String email) {
    	String url = "http://10.0.2.2/delete_etudiant.php";
    	try {	
    		// create http connection
            HttpClient client = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);	                 
            // connect
            HttpResponse response = client.execute(httppost);	               
        }
        catch(IOException e){
            msg = "No Network Connection";
        }
    }
    
	 @Override
	    protected String doInBackground(String... params) {
	        if(params == null) return null;
	         	             
	            	etudiant = new Etudiant();
	            	email = etudiant.getEmail();
	            	//Log.d("sup",email);
	            	nom = etudiant.getNom();
	            	prenom = etudiant.getPrenom();
	            	prevente = etudiant.getPrevente();
	            	delete(etudiant, email);
	            	return null;
	        }
	 
}
