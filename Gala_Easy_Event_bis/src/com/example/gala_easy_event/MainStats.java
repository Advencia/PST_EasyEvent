package com.example.gala_easy_event;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

public class MainStats extends Activity{
	private ProgressDialog dialog;

	@Override
	 public void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	    	setContentView(R.layout.stats);
	    	StatsView();
	}
	
	private void StatsView() {       
        dialog = ProgressDialog.show(this, "", "Loading...");	         
       // String url = "http://10.0.2.2/statistiques.php";
        FetchStats statistics = new FetchStats();
        statistics.execute();
    }
}
