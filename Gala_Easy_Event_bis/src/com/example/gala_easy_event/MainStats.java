package com.example.gala_easy_event;

import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

public class MainStats extends ListActivity implements FetchStatsListener{
	private ProgressDialog dialog;
	private List<Stats> tab_stats;
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	    	setContentView(R.layout.main_stats);
	    	StatsView();
	}
	
	private void StatsView() {       
        dialog = ProgressDialog.show(this, "", "Loading...");	         
        UpdateStats upstats = new UpdateStats();
        upstats.execute();
        FetchStats statistics = new FetchStats(this);
        statistics.execute();
    }
	
	 public void onStatsComplete(List<Stats> stats) {
	        // dismiss the progress dialog
	        if(dialog != null)  dialog.dismiss();
	        // create new adapter
	        StatsAdapter adapter = new StatsAdapter(this, stats);
	        // set the adapter to list
	        setListAdapter(adapter); 
	        tab_stats = stats;
	    }
	 
	public void onStatsFailure(String msg) {
	        // dismiss the progress dialog
	        if(dialog != null)  dialog.dismiss();
	        // show failure message
	        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();       
	    }
	    
}
