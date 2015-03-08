package com.example.gala_easy_event_ter;

import java.util.List;

import android.widget.TableLayout;
import android.widget.TextView;

public interface FetchStatsListener {
	public void onStatsComplete(List<Stats> stats);
	public void onStatsFailure(String msg);
}
