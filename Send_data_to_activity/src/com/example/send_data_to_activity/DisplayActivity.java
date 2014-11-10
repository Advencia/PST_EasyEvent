package com.example.send_data_to_activity;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends Activity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.display);
 
        Bundle b = getIntent().getExtras();
        TextView name = (TextView) findViewById(R.id.nameValue);
       
        name.setText(b.getCharSequence("name"));
    }
}
