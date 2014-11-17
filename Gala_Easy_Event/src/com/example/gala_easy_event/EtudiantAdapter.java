package com.example.gala_easy_event;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EtudiantAdapter extends ArrayAdapter<Etudiant> {
	private List<Etudiant> items;

	public EtudiantAdapter(Context context, List<Etudiant> items) {
		super(context, R.layout.app_custom_list, items);
		this.items = items;
	}

	@Override
	public int getCount() {
		return items.size();
	}

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

}
