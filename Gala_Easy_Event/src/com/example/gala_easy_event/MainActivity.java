package com.example.gala_easy_event;

import java.io.InterruptedIOException;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle; 
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity implements FetchDataListener {

	 private final Context context = this;
	 private ProgressDialog dialog;
	 private MenuItem refreshMenuItem;
     private List<Etudiant> list_items;
     SwipeRefreshLayout swipeLayout;
     FetchDataListener listener;
     EtudiantAdapter adapter;
     String msg;
     private Etudiant object_selected;
     
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);       
	        setContentView(R.layout.activity_main);       
	        initView();
	         
		    /****Barre de recherche *****/
		    EditText editTxt = (EditText) findViewById(R.id.editTxt);
	        editTxt.addTextChangedListener(new TextWatcher() {
	            final EtudiantAdapter adp = new EtudiantAdapter(context, list_items);
	            @Override
	            public void onTextChanged(CharSequence s, int start, int before, int count) {
	                adp.getFilter().filter(s.toString()); 
	                Log.d("var", "liste filtre");
	            }
	             
	            @Override
	            public void beforeTextChanged(CharSequence s, int start, int count,
	                    int after) {
	                 
	            }
	             
	            @Override
	            public void afterTextChanged(Editable s) {
	            	
	            }
	        });
	        
	        /*********Supprimer un item**********/
	        OnItemLongClickListener itemLongListener = new OnItemLongClickListener() {
	            @Override
	            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long rowid) {
	            	object_selected = (Etudiant) parent.getItemAtPosition(position);
	                
	                AlertDialog.Builder builder = new AlertDialog.Builder(context);
	                builder.setMessage("remove this student ?");
	                builder.setCancelable(false);
	    
	                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {	                    
	                    @Override
	                    public void onClick(DialogInterface dialog, int which) {
	                       
	                        adapter.remove(object_selected);
	                        adapter.notifyDataSetChanged();
	               
	                        Toast.makeText(getApplicationContext()," has been removed", Toast.LENGTH_SHORT).show();
	                    }
	                });
	                
	                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
	                    @Override
	                    public void onClick(DialogInterface dialog, int which) {
	                        dialog.cancel();
	                    }
	                });
	                builder.show();
	                return true;
	            }
	        };
	        getListView().setOnItemLongClickListener(itemLongListener);
	}	    
	    
	    /******CLick on item********/	    
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
	        list_items = data;	        
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
	
	    /*****Menu******/
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
	    
	    /*********Reload des données**********/
	    private class SyncData extends AsyncTask<String, Void, String> {
	    	EtudiantAdapter adapter = new EtudiantAdapter(context, list_items);
	        @Override
	        protected void onPreExecute() {
	            // set the progress bar view

	            refreshMenuItem.setActionView(R.layout.action_progressbar);
	 
	            refreshMenuItem.expandActionView();
	            initView();
	        }
	 
	        @Override
	        protected String doInBackground(String... params) {
	            try {
	            	//Thread.sleep(3000);
	            	
	            	FetchDataTask loadData = new FetchDataTask(listener);
	            	loadData.execute("http://10.0.2.2/etudiant.php");
	            	//setListAdapter(adapter);
	            	//adapter.notifyDataSetChanged();
	            } 
	            
	            catch (Exception e) {
	                //e.printStackTrace();
	            	Log.e("Error ", e.getMessage());
	            }
	            return null;
	        }
	 
	        @Override
	        protected void onPostExecute(String result) {
	        	adapter.upDateEntries(list_items);
	        	
	            refreshMenuItem.collapseActionView();
	            // remove the progress bar view
	            refreshMenuItem.setActionView(null);
	           
	        }
	    };
}
