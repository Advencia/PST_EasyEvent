package com.example.gala_easy_event;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;






import java.util.Map;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;











import android.content.Intent;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;	
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Form extends Activity {
	Button bouton ;
    HttpPost httppost;
    StringBuffer buffer;
    HttpClient httpclient ;
    private int code;
	private String result;
	private InputStream is;
	private String line;
	String nom; 
	String prenom;
	String email;
	String prevente;
	EditText edit_nom, edit_prenom, edit_email, edit_prevente;
    String validation;
    
 @Override
 public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.form);
 
    	edit_nom = (EditText)  findViewById(R.id.editTextFormulaire1) ;
    	edit_prenom = (EditText) findViewById(R.id.editTextFormulaire2);
    	edit_email = (EditText) findViewById(R.id.editTextFormulaire3);
    	edit_prevente = (EditText) findViewById(R.id.editTextFormulaire4);
    	bouton = (Button) findViewById(R.id.buttonFormulaire1) ;
    	
    	nom = edit_nom.getText().toString();
    	prenom = edit_prenom.getText().toString();
        email = edit_email.getText().toString();
        prevente = edit_prevente.getText().toString();
        
        /**********Click bouton lance le processus d'insertion**********/
        bouton.setOnClickListener(new View.OnClickListener(){
 		public void onClick(View v) {
	    	Thread thread = new Thread(new Runnable(){
			
				@Override
    			public void run() {
					nom = edit_nom.getText().toString();
			    	prenom = edit_prenom.getText().toString();
			        email = edit_email.getText().toString();
			        prevente = edit_prevente.getText().toString();
			        validation = "non";
	    			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        			nameValuePairs.add(new BasicNameValuePair("prenom", prenom));
        			nameValuePairs.add(new BasicNameValuePair("nom", nom));
        			nameValuePairs.add(new BasicNameValuePair("email",email));	  
        			nameValuePairs.add(new BasicNameValuePair("prevente", prevente));
        			//nameValuePairs.add(new BasicNameValuePair("validation", validation));
        			
        			try{
        			HttpClient httpclient = new DefaultHttpClient();
        		        HttpPost httppost = new HttpPost("http://10.0.2.2/insertion.php");
        		        System.out.println("///////////////////////" + nameValuePairs);
        		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        		        HttpResponse response = httpclient.execute(httppost); 
        		        HttpEntity entity = response.getEntity();
        		        is = entity.getContent();        		             		        
        			}
        			
        	        catch(Exception e){
        	        	Log.e("Fail 1", e.toString());
        		    	Toast.makeText(getApplicationContext(), "Invalid IP Address",
        				Toast.LENGTH_LONG).show();
        	        }     
        	        
        	        try{
        	            BufferedReader reader = new BufferedReader
        				(new InputStreamReader(is,"iso-8859-1"),8);
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
        	       
        	       /* try{
        	            JSONObject json_data = new JSONObject(result);
        	            code=(json_data.getInt("code"));
        	            Log.e("pass 3", "connection success ");
        	        }
        	        catch(Exception e){
        	            Log.e("Fail 3", e.toString());
        	        }    */      
	    		}
			});
	    	thread.start(); 
	    	final Intent intent = new Intent(Form.this, TabView.class);
	    	startActivity(intent);
 		}
    
 	});
    }
}
