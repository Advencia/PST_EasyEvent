package com.example.gala_easy_event;

import java.io.InterruptedIOException;
import java.util.ArrayList;
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
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
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
     String email;
     private Etudiant object_selected;
     
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);       
	        setContentView(R.layout.activity_main);     
	        ListView listView = (ListView) findViewById(android.R.id.list);
	        initView();
	        
	        //pull refresh 
	        final SwipeRefreshLayout swipeContain = (SwipeRefreshLayout) findViewById(R.id.swipe);
	        // Setup refresh listener which triggers new data loading
	        swipeContain.setOnRefreshListener(new OnRefreshListener() {
	            @Override
	            public void onRefresh() {
	            	refreshView();
	                swipeContain.setRefreshing(false);
	            } 
	        });
	        // Configure the refreshing colors
	        swipeContain.setColorSchemeResources(android.R.color.holo_blue_bright, 
	                android.R.color.holo_purple, 
	                android.R.color.holo_blue_light, 
	                android.R.color.holo_red_light);	
	    
	         
		    /****Barre de recherche *****/
	        final ListView lv = (ListView) findViewById(android.R.id.list);
		    EditText editTxt = (EditText) findViewById(R.id.editTxt);
	        editTxt.addTextChangedListener(new TextWatcher() {
	            final EtudiantAdapter adp = new EtudiantAdapter(context, list_items);
	            @Override
	            public void onTextChanged(CharSequence s, int start, int before, int count) {
	            	 int textlength = s.length();
		               ArrayList<Etudiant> tempArrayList = new ArrayList<Etudiant>();
		               for(Etudiant c: list_items){
		                  if (textlength <= c.getNom().length()) {
		                     if (c.getNom().toLowerCase().contains(s.toString().toLowerCase())) {
		                        tempArrayList.add(c);
		                     }
		                  }
		               }
		               adapter = new EtudiantAdapter(context, tempArrayList);
		               lv.setAdapter(adapter);
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
	    /*    OnItemLongClickListener itemLongListener = new OnItemLongClickListener() {
	            @Override
	            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long rowid) {
	            	object_selected = (Etudiant) parent.getItemAtPosition(position);
	                
	                AlertDialog.Builder builder = new AlertDialog.Builder(context);
	                builder.setMessage("remove this student ?");
	                builder.setCancelable(false);
	    
	                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {	                    
	                    @Override
	                    public void onClick(DialogInterface dialog, int which) {
	                       //suppression local
	                        adapter.remove(object_selected);
	                        adapter.notifyDataSetChanged();
	                       
	                       //suppression serveur
	                        object_selected.getEmail(); //on récupère l'email pr faire correspondre a la db ds le php
	                        deleteView();
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
	        */
	    /****************Suppression clic simple********************/
	     ListView liste = getListView();
	        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	        	@Override
	        	public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
	        		object_selected = (Etudiant) av.getItemAtPosition(pos);
	        		email = object_selected.getEmail();	        		
	        		onClickDoSomething(v);
	        		Log.d("supp", object_selected.getEmail());
	        	}
	        });	        
	    }  
	        
	    public void onClickDoSomething(View view) {
	    	//adapter.remove(object_selected);
            //adapter.notifyDataSetChanged();
            //deleteView();
	    	DeleteDataTask delete = new DeleteDataTask(object_selected, email);
	    	delete.execute();
	    	
            Toast.makeText(getApplicationContext()," has been removed", Toast.LENGTH_SHORT).show();
	    	}	        
	    
	    /*************************************/
	    private void initView() {
	        // show progress dialog
	        dialog = ProgressDialog.show(this, "", "Loading...");	         
	        String url = "http://10.0.2.2/etudiant.php";
	        FetchDataTask task = new FetchDataTask(this);
	        task.execute(url);
	    }
	    
	    private void refreshView() {
	    	String url = "http://10.0.2.2/etudiant.php";
	        FetchDataTask task = new FetchDataTask(this);
	        task.execute(url);
	    }
	    private void deleteView() {
	    	DeleteDataTask delete = new DeleteDataTask(object_selected, email);
	        delete.execute("http://10.0.2.2/delete.php");
	    	Toast toast = Toast.makeText(MainActivity.this, "suppression réussi", Toast.LENGTH_LONG);
	        toast.show();
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
	    
	    /******************Refresh button functions*******************/
	 /*   @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	      MenuInflater inflater = getMenuInflater();
	      inflater.inflate(R.menu.main, menu);
	      return true;
	    } 
	
	    /*****Menu******/
	  /*  @Override
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
	    } */
	    
	    
	    /*********Reload des données**********/
	  /*  private class SyncData extends AsyncTask<String, Void, String> {
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
	    };*/
}
