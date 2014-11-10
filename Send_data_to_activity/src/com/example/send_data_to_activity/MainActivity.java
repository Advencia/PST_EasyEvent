package com.example.send_data_to_activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity implements OnClickListener {

	    Button button;
	    EditText name;
	 
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.form);
	 
	        //Get the ids of view objects
	        findAllViewsId();
	         
	        button.setOnClickListener(this);
	    }
	 
	    private void findAllViewsId() {
	        button = (Button) findViewById(R.id.submit);
	        name = (EditText) findViewById(R.id.name);
	    }
	    
	    @Override
	    public void onClick(View v) {
	        Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
	        //Create a bundle object
	        Bundle b = new Bundle();
	        
	        //Inserts a String value into the mapping of this Bundle
	        b.putString("name", name.getText().toString());
	         
	        //Add the bundle to the intent.
	        intent.putExtras(b);
	         
	        //start the DisplayActivity
	        startActivity(intent);
	    }
}

