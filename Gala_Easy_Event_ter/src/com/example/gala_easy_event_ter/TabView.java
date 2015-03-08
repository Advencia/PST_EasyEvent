package com.example.gala_easy_event_ter;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;

public class TabView extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 

        final TabHost tabHost = getTabHost();

        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("Liste")
                .setContent(new Intent(this, MainActivity.class)));

        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator("Ajout")
                .setContent(new Intent(this, Form.class)));
        
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("Stats")
                .setContent(new Intent(this, MainStats.class)));
        //tabHost.getTabWidget().setBackgroundResource(R.drawable.stats); 
       }
}