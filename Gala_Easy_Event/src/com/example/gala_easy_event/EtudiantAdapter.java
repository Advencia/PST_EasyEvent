package com.example.gala_easy_event;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

	/*recuperation des donnees*/
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		if (v == null) {
			LayoutInflater li = LayoutInflater.from(getContext());
			v = li.inflate(R.layout.app_custom_list, null);
		}

		Etudiant app = items.get(position);

		if (app != null) {
			TextView prenom = (TextView) v.findViewById(R.id.prenom);
			TextView nom = (TextView) v.findViewById(R.id.nom);
			TextView email = (TextView) v.findViewById(R.id.email);
			TextView prevente = (TextView) v.findViewById(R.id.prevente);

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

		}

		return v;
	}
	
	/****fonction de reload des données****/
	public void upDateEntries(List<Etudiant> newitems) {
        items = newitems;
        notifyDataSetChanged();
     }
	
	/*fonction de filtre utilisé pour la barre de recherche*/
/*	private class EtudiantFilter extends Filter {
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
		    FilterResults results = new FilterResults();
		   
		    // Si il n'y a rien dans le filtre on renvoi toute la liste
		    if (constraint == null || constraint.length() == 0) {
		        results.values = items;
		        results.count = items.size();
		    }
		    
		    else {
		        // on crée une nouvelle liste pour l'affichage du resultat s'il y a une recherche
		        List<Etudiant> itemsfiltre = new ArrayList<Etudiant>();
		         
		        //si on a un etudiant dont le prenom commence par les caracteres du filtre, on l'ajoute a la liste
		        for (Etudiant etudiant : items) {
		            if (etudiant.getNom().toUpperCase().startsWith(constraint.toString().toUpperCase()))
		                itemsfiltre.add(etudiant);
		        }
		         
		        results.values = itemsfiltre;
		        results.count = itemsfiltre.size();
		    }
		    return results;
		}
		
		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
		     
		    // lorsque tout est recuperer on dis a l'adapter de retourner la liste
		    if (results.count == 0)
		        notifyDataSetInvalidated();
		    else {
		        items = (List<Etudiant>) results.values;
		        notifyDataSetChanged();
		    } 
		}
		
		
		public Filter getFilter() {
		    if (filtreEtudiant == null)
		        filtreEtudiant = new EtudiantFilter();
		     
		    return filtreEtudiant;
		}
		
	}*/
	
	/*public List<Etudiant> getItemList() {
        return items;
    }
 
    public void setItemList(List<Etudiant> items) {
        this.items = items;
    }*/

}
