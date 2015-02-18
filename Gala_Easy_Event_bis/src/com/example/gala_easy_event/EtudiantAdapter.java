package com.example.gala_easy_event;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Filter;
import android.widget.TextView;

public class EtudiantAdapter extends ArrayAdapter<Etudiant> {
	private List<Etudiant> items;
	private Filter filtreEtudiant;
	
	/*associe le modele d'un item à l'adapter*/
	public EtudiantAdapter(Context context, List<Etudiant> items) {
		super(context, R.layout.app_custom_list, items);
		this.items = items;
		
	}

	/*recupere le nombre d'item*/
	@Override
	public int getCount() {
		return items.size();
	}

	/*****recuperation des donnees*****/
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		//v.setOnLongClickListener();
		if (v == null) {
			LayoutInflater li = LayoutInflater.from(getContext());
			v = li.inflate(R.layout.app_custom_list, null);
		}

		final Etudiant app = items.get(position);

		if (app != null) {
			TextView prenom = (TextView) v.findViewById(R.id.prenom);
			TextView nom = (TextView) v.findViewById(R.id.nom);
			TextView email = (TextView) v.findViewById(R.id.email);
			TextView prevente = (TextView) v.findViewById(R.id.prevente);
			CheckBox validation = (CheckBox) v.findViewById(R.id.checkbox);
			//validation.setTag (position); 
			
			if (prenom != null) {
				prenom.setText(app.getPrenom());
			}

			if (nom != null) {
				nom.setText(app.getNom());
			}

			if (email != null) {
				email.setText(app.getEmail());
			}
			
			if (prevente != null) {
				prevente.setText(app.getPrevente());
			}
			
			if (validation != null){
					if(app.getValidation().contains("oui")){					
						validation.setChecked(true);
					}
					else{
						validation.setChecked(false);
					}
				}
			
		/**********gestion checkbox********/
			validation.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	            @Override
	            public void onCheckedChanged(CompoundButton button, boolean checked) {
	                // Cast it so we can access the public functions
	                MainActivity myActivity = (MainActivity) getContext();

	                if (checked){
	                	String email = app.getEmail();
	                    myActivity.checkboxSelected(email);	                    
	                }
	            }
	        });
		/****************************/
		}
		return v;
	}
	
	public List<Etudiant> getItemList() {
        return items;
    }
 
    public void setItemList(List<Etudiant> items) {
        this.items = items;
    }

}
