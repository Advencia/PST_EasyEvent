package com.example.add_item_list;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ListActivity {

	//les item seront stockes dans cette arraylist
		ArrayList<String> list = new ArrayList<String>();
		//declaration arrayadapter pour mettre item dans listview
		ArrayAdapter<String> adapter;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			
			Button btn = (Button) findViewById(R.id.btnAdd);
			adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
			
			OnClickListener listener = new OnClickListener() {
				
				public void onClick(View v) {
					EditText edit = (EditText) findViewById(R.id.txtItem);
					list.add(edit.getText().toString());
					edit.setText("");
					adapter.notifyDataSetChanged();
				}
			};
			btn.setOnClickListener(listener);
			setListAdapter(adapter);
		
			//barre de rechercher, filtre
			EditText myFilter = (EditText) findViewById(R.id.myFilter);
			  myFilter.addTextChangedListener(new TextWatcher() {
			 
			  public void afterTextChanged(Editable s) {
			  }
			 
			  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			  }
			 
			  public void onTextChanged(CharSequence s, int start, int before, int count) {
			   adapter.getFilter().filter(s.toString());
			  }
			  });
		}
}
