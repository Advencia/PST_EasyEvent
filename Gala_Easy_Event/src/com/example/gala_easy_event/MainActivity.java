package com.example.gala_easy_event;

import java.util.List;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ListActivity implements FetchDataListener {

	 private ProgressDialog dialog;
     
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);       
	        setContentView(R.layout.activity_main);       
	        initView(); 
	        
	      //barre de recherche, filtre
			EditText myFilter = (EditText) findViewById(R.id.monFiltre);
			  myFilter.addTextChangedListener(new TextWatcher() {
			 
			  public void afterTextChanged(Editable s) {
			  }
			 
			  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			  }
			 
			  public void onTextChanged(CharSequence s, int start, int before, int count) {
			  // adapter.getFilter().filter(s.toString());
			  }
			  });
	    }
	 
	    private void initView() {
	        // show progress dialog
	        dialog = ProgressDialog.show(this, "", "Loading...");
	         
	        String url = "http://10.0.2.2/etudiant.php";
	        FetchDataTask task = new FetchDataTask(this);
	        task.execute(url);
	    }
	     
	    @Override
	    public void onFetchComplete(List<Etudiant> data) {
	        // dismiss the progress dialog
	        if(dialog != null)  dialog.dismiss();
	        // create new adapter
	        EtudiantAdapter adapter = new EtudiantAdapter(this, data);
	        // set the adapter to list
	        setListAdapter(adapter); 
	        
	    }
	 
	    @Override
	    public void onFetchFailure(String msg) {
	        // dismiss the progress dialog
	        if(dialog != null)  dialog.dismiss();
	        // show failure message
	        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();       
	    }
	
}
