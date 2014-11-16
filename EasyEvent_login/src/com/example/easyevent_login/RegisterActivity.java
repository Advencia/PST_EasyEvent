package com.example.easyevent_login;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends Activity {

	  Button btnRegister;
	  Button btnLinkToLogin;
	  EditText inputFullName;
	  EditText inputEmail;
	  EditText inputPassword;
	  
	  // ***** Réponse JSON *******
	  private static String KEY_SUCCESS = "success";

	  @Override
	  public void onCreate(Bundle savedInstanceState) 
	  {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.register);   

	    /* Définit le nom de l'Activity */

	    setTitle("Easy Event");

	    /* Importation des caractéristiques des champs et boutons */
	    inputFullName = (EditText) findViewById(R.id.registerName);
	    inputEmail = (EditText) findViewById(R.id.registerEmail);
	    inputPassword = (EditText) findViewById(R.id.registerPassword);
	    btnRegister = (Button) findViewById(R.id.btnRegister);
	    btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);

	   
	    /* Clic sur le bouton Register */
	    btnRegister.setOnClickListener(new View.OnClickListener() {      
	    	public void onClick(View view) {
	    		/* On récupère le contenu des EditText */
	    		String name = inputFullName.getText().toString();
	    		String email = inputEmail.getText().toString();
	    		String password = inputPassword.getText().toString();
	    		JSONObject jObj;

	    		/* Teste du mot de passe */
	    		if(isValidPassword(password)) {
	    			/* Teste de l'adresse mail */
	    			if(isValidEmail(email)) {
	    				/* On construit la liste des paramètres de la requête */
	    				ArrayList nameValuePairs = new ArrayList();
	    				nameValuePairs.add(new BasicNameValuePair("tag", "register"));
	    				nameValuePairs.add(new BasicNameValuePair("name", name));
	    				nameValuePairs.add(new BasicNameValuePair("email", email));
	    				nameValuePairs.add(new BasicNameValuePair("password", password));

	    			try {  
	    				/********************************************/
	    				/* Exécute la requête vers le serveur local */
	    				/********************************************/    

	    				HttpClient httpclient = new DefaultHttpClient();
	    				HttpPost httppost = new HttpPost("http://10.0.2.2/android_login_api/index.php");
	    				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    				HttpResponse response = httpclient.execute(httppost);
	    				HttpEntity entity = response.getEntity();
	    				InputStream is = entity.getContent();
	    				BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	    				StringBuilder sb = new StringBuilder();
	    				String line = reader.readLine();
	    				sb.append(line + "\n");
	    				is.close();

	    				/***************************/
	    				/* Résultats de la requête */
	    				/***************************/
	    				
	    				String result = sb.toString();
	    				jObj = new JSONObject(result);  

	    				/**********************************************/
	    				/* Si le résultat de la requête n'est pas nul */
	    				/**********************************************/

	    				if (jObj.getString(KEY_SUCCESS) != null) {
	    					String res = jObj.getString(KEY_SUCCESS); 

	    					/*********************************************************/
	    					/* Si il vaut 1, l'utilisateur est maintenant enregistré */
	    					/*********************************************************/

	    					if(Integer.parseInt(res) == 1) {

	    						Toast.makeText(getApplicationContext(), "Success Registred", Toast.LENGTH_SHORT).show();

	    						/***************************************/
	    						/* Lancement de l'Activity "DashBoard" */
	    						/***************************************/

	    						Intent login = new Intent(getApplicationContext(), LoginActivity.class);
	    						login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    						startActivity(login);

	    						/****************************/
	    						/* Ferme l'Activity "Login" */
	    						/****************************/

	    						finish();
	    					}

	    					/*****************************************/
	    					/* Si il vaut 0, erreur d'enregistrement */
	    					/*****************************************/

	    					else {
	    						Toast.makeText(getApplicationContext(), jObj.get("error_msg").toString(), Toast.LENGTH_SHORT).show();
	    					}
	    				}
	            }
	            catch(Exception e)
	            {  
	            }
	          }
	          else
	            Toast.makeText(getApplicationContext(), "email invalide !", Toast.LENGTH_SHORT).show();
	        }
	        else
	          Toast.makeText(getApplicationContext(), "Password trop court (&lt;8) !", Toast.LENGTH_SHORT).show();
	      }
	    });

	    /**************************/
	    /* Clic sur le lien Login */
	    /**************************/

	    btnLinkToLogin.setOnClickListener(new View.OnClickListener() {
	      public void onClick(View view) {
	        /***********************************/
	        /* Lancement de l'Activity "Login" */
	        /***********************************/

	        Intent i = new Intent(getApplicationContext(),  LoginActivity.class);
	        startActivity(i);

	        /****************************/
	        /* Ferme l'Activity "Login" */
	        /****************************/

	        finish();
	      }
	    });
	  }

	  /***************************/
	  /* Teste de l'adresse mail */
	  /***************************/ 

	  public final static Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
	        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
	        "\\@" +
	        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
	        "(" +
	        "\\." +
	        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
	        ")+"
	      );

	  public static boolean isValidEmail(String email) {
	     return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
	  }

	  /******************************************************/
	  /* Teste si le mot de passe fait plus de 8 caractères */
	  /******************************************************/ 

	  public static boolean isValidPassword(String password) {
	    if(password.length() > 0)
	      return true;
	    else
	      return false;
	  }

}
