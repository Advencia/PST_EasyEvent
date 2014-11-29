package com.example.gala_easy_event;

import java.io.InterruptedIOException;
import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle; 
//import android.os.Handler;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.text.Editable;
//import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.AbsListView;
//import android.widget.EditText;
//import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity implements FetchDataListener/*, SwipeRefreshLayout.OnRefreshListener*/ {

	 private final Context context = this;
	private ProgressDialog dialog;
	 private MenuItem refreshMenuItem;
     private List<Etudiant> items;
    // SwipeRefreshLayout swipeLayout;
     FetchDataListener listener;
     EtudiantAdapter adapter;
     
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);       
	        setContentView(R.layout.activity_main);       
	        initView();
	        
	        /**********swipe refresh******/
	       
	  /*     swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
	        swipeLayout.setOnRefreshListener(this);
	        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
	                android.R.color.holo_green_light,
	                android.R.color.holo_orange_light,
	                android.R.color.holo_red_light);
	    }	     
	    @Override public void onRefresh() {
	        new Handler().postDelayed(new Runnable() {
	            @Override public void run() {
	                EtudiantAdapter adapter = new EtudiantAdapter(context, items);
	                adapter.notifyDataSetChanged();
	                swipeLayout.setRefreshing(false);
	            }
	        }, 5000);  */
	        
	   /*     final SwipeRefreshLayout swipeView = (SwipeRefreshLayout) findViewById(R.id.swipe);
	        swipeView.setEnabled(true);
	        ListView lView = (ListView) findViewById(android.R.id.list);
	        final EtudiantAdapter adp = new EtudiantAdapter(this, items);
	        lView.setAdapter(adp);
	     
	        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
	            @Override
	            public void onRefresh() {
	                swipeView.setRefreshing(true);
	                ( new Handler()).postDelayed(new Runnable() {
	                    @Override
	                    public void run() {
	                        swipeView.setRefreshing(false);
	                       // adp.notifyDataSetChanged();
	                    }
	                }, 3000);
	            }
	        });
	        
	        lView.setOnScrollListener(new AbsListView.OnScrollListener() {
	            @Override
	            public void onScrollStateChanged(AbsListView absListView, int i) {
	            }
	     
	            @Override
	            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
	                    if (firstVisibleItem == 0)
	                        swipeView.setEnabled(true);
	                    else
	                        swipeView.setEnabled(false);
	            }
	        });
	        */
	        
		    /****Barre de recherche *****/
		/*    EditText editTxt = (EditText) findViewById(R.id.editTxt);
	        editTxt.addTextChangedListener(new TextWatcher() {
	            final EtudiantAdapter adp = new EtudiantAdapter(context, items);
	            @Override
	            public void onTextChanged(CharSequence s, int start, int before, int count) {
	                //System.out.println("Text ["+s+"]");
	                adp.getFilter().filter(s.toString());                           
	            }
	             
	            @Override
	            public void beforeTextChanged(CharSequence s, int start, int count,
	                    int after) {
	                 
	            }
	             
	            @Override
	            public void afterTextChanged(Editable s) {
	            	
	            }
	        });*/

	    }
	   
	  
	    /***CLick on item*****/	    
	    public void onClickDoSomething(View view) {
	    	Toast toast = Toast.makeText(MainActivity.this, "Essai de Toast", Toast.LENGTH_LONG);
	        toast.show();
	    	}
	    
	    /*********************/
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
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	      MenuInflater inflater = getMenuInflater();
	      inflater.inflate(R.menu.main, menu);
	      return true;
	    } 
	
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	      switch (item.getItemId()) {
	      case R.id.action_refresh:
	        Toast.makeText(this, "Refresh selected", Toast.LENGTH_SHORT).show();
	        refreshMenuItem = item;
	        new SyncData().execute();
            return true;
	      	default:
            return super.onOptionsItemSelected(item);
	       }

	    } 
	    
	    private class SyncData extends AsyncTask<String, Void, String> {
	        @Override
	        protected void onPreExecute() {
	            // set the progress bar view
	            refreshMenuItem.setActionView(R.layout.action_progressbar);
	 
	            refreshMenuItem.expandActionView();
	        }
	 
	        @Override
	        protected String doInBackground(String... params) {
	            try {
	            	//EtudiantAdapter adapter = new EtudiantAdapter(context, items);
	            	//adapter.notifyDataSetChanged();
	            	FetchDataTask loadData = new FetchDataTask(listener);
	            	loadData.execute();
	            	Thread.sleep(3000);
	            } 
	            catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            return null;
	        }
	 
	        @Override
	        protected void onPostExecute(String result) {
	        	adapter.upDateEntries(items);
	            refreshMenuItem.collapseActionView();
	            // remove the progress bar view
	            refreshMenuItem.setActionView(null);
	        }
	    };
}
