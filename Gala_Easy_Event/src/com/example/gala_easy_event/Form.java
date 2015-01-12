package com.example.gala_easy_event;

import java.util.ArrayList;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;	
import org.apache.http.message.BasicNameValuePair;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Form extends Activity {
	EditText Nom,Prenom,Email,Prevente ;
    Button bouton ;
    HttpPost httppost;
    StringBuffer buffer;
    HttpClient httpclient ;
 
    @Override
 public void onCreate(Bundle savedInstanceState) 
 {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.form);
 
 Nom = (EditText)  findViewById(R.id.editTextFormulaire1) ;
 Prenom = (EditText) findViewById(R.id.editTextFormulaire2);
 Email = (EditText) findViewById(R.id.editTextFormulaire3);
 Prevente = (EditText) findViewById(R.id.editTextFormulaire4);
 bouton = (Button) findViewById(R.id.buttonFormulaire1) ;


 
 bouton.setOnClickListener(new View.OnClickListener() 
 {
	 public void onClick(View nouveau) 
	 {
		final String N = Nom.getText().toString();
    	final String P = Prenom.getText().toString();
    	final String E = Email.getText().toString();
    	final String B = Prevente.getText().toString();
	    new AsyncTask<Void, Void, Void>() 
	    {
	        @Override
	        protected Void doInBackground(Void... params) 
	        {
	            httpclient = new DefaultHttpClient();
	            httppost = new HttpPost("http://10.0.2.2/insertion.php");

	              try {
	                  ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	                  nameValuePairs.add(new BasicNameValuePair("Nom", N));
	                  nameValuePairs.add(new BasicNameValuePair("Prenom", P));
	                  nameValuePairs.add(new BasicNameValuePair("Email", E));
	                  nameValuePairs.add(new BasicNameValuePair("Prevente", B));

	                  httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));                  
	                  HttpResponse response = httpclient.execute(httppost);
	                  Log.i("postData", response.getStatusLine().toString());
	              } 
	              catch(Exception e) {
	                       Log.e("log_tag", "Error:  "+e.toString());
	              } 
	            return null;
	         }
	     }.execute();
	   }
 });
}
}
