package com.example.gala_easy_event;

import java.io.BufferedReader;
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
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Form_update extends Activity{
	String prenom;
	String nom;
	String email;
	String prevente;
	String firstname;
	String name;
	String adress_mail;
	String ticket;
	private InputStream is;
	private String result;
	private int code;
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);       
	        setContentView(R.layout.form_update);  
	        //recuperation des données de l'etudiant sur lequel on a clické
	        Intent i = getIntent();
	        name = i.getStringExtra("name");
	        firstname = i.getStringExtra("firstname");
	        adress_mail = i.getStringExtra("adress_mail");
	        ticket = i.getStringExtra("ticket");

	        //instanciation des boutons
	        Button bouton_modif = (Button) findViewById(R.id.bouton_modif);
	        bouton_modif.setOnClickListener(myhandler1);
			Button bouton_retour = (Button) findViewById(R.id.bouton_retour);
			bouton_retour.setOnClickListener(myhandler2);
			
	        //affichage des données recupérées dans l'activity + recuperation du texte ecrit dans les edittext
	        EditText edit_prenom = (EditText)findViewById(R.id.edit_prenom);
	        edit_prenom.setText(firstname);
			prenom = edit_prenom.getText().toString();					 
			EditText edit_nom = (EditText)findViewById(R.id.edit_nom);
			edit_nom.setText(name);
			nom = edit_nom.getText().toString();
			EditText edit_email = (EditText)findViewById(R.id.edit_email);
			edit_email.setText(adress_mail);
			email = edit_email.getText().toString();		
			EditText edit_prevente = (EditText)findViewById(R.id.edit_prevente);
			edit_prevente.setText(ticket);
			prevente = edit_prevente.getText().toString();							
	 	}
	 
		//click sur le bouton Modifier lance le processus de modification sur serveur
	 	View.OnClickListener myhandler1 = new View.OnClickListener() {
		    public void onClick(View v) {
		    	Thread thread = new Thread(new Runnable(){
				@Override
        		public void run() {
        			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        			nameValuePairs.add(new BasicNameValuePair("prenom", prenom));
        			nameValuePairs.add(new BasicNameValuePair("nom", nom));
        			nameValuePairs.add(new BasicNameValuePair("email",email));	  
        			nameValuePairs.add(new BasicNameValuePair("prevente", prevente));
        			try	{
        				HttpClient httpclient = new DefaultHttpClient();
        				HttpPost httppost = new HttpPost("http://10.0.2.2/update.php");
        				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        				HttpResponse response = httpclient.execute(httppost); 
        				HttpEntity entity = response.getEntity();
        				is = entity.getContent();
        				Log.e("pass 1", "connection success ");
        			}
        			catch(Exception e){
        				Log.e("Fail 1", e.toString());
        				Toast.makeText(getApplicationContext(), "Invalid IP Address",
        						Toast.LENGTH_LONG).show();
        			}     
        			
        			try{
		            	BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		            	StringBuilder sb = new StringBuilder();
		            	String line;
						
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
		       
        			try {
        				JSONObject json_data = new JSONObject(result);
        				code=(json_data.getInt("code"));
        				Log.e("pass 3", "connection success ");
        				if(code==1){
        				Toast.makeText(getBaseContext(), "Mise à jour OK",
        					Toast.LENGTH_SHORT).show();
        	        	}
        	        	else{
        				Toast.makeText(getBaseContext(), "Réessayé",
        					Toast.LENGTH_LONG).show();
        	        	}
        			}
        			catch(Exception e){
        				Log.e("Fail 3", e.toString());
        			}	        		    
        		}
        	});
	thread.start(); 
	final Intent intent = new Intent(Form_update.this, TabView.class);
	startActivity(intent);
		    }
	  };
	  
	  //click sur bouton retour relance l'activity avec liste
	  View.OnClickListener myhandler2 = new View.OnClickListener() {
		    public void onClick(View v) {
		    	final Intent intent = new Intent(Form_update.this, TabView.class);
				startActivity(intent);
		    }
	  }; 
}
