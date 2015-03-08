package com.example.gala_easy_event_ter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StatsAdapter extends ArrayAdapter<Stats>{
	
	private List<Stats> tab_stats;
	
	public StatsAdapter(Context context, List<Stats> stats) {
		super(context, R.layout.stats, stats);
		this.tab_stats = stats;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater li = LayoutInflater.from(getContext());
			v = li.inflate(R.layout.stats, null);
		}
		
		final Stats statistic = tab_stats.get(position);

		if (statistic != null) {
			TextView simple = (TextView) v.findViewById(R.id.textView32);
			TextView bouteille = (TextView) v.findViewById(R.id.textView42);
			TextView vip = (TextView) v.findViewById(R.id.textView52);
			
			if (simple != null) {
				simple.setText(statistic.getSimple());
			}
			
			if(bouteille != null){
				bouteille.setText(statistic.getBouteille());
			}
			
			if (vip != null) {
				vip.setText(statistic.getVip());
			}

		}
		return v;
	}

}
