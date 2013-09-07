package com.example.mytai_imo;

import com.example.mytai_imo.R;
import com.example.mytai_imo.SettingsActivity;

import com.example.mytai_imo.utils.App;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		App.Initialize(this);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		App.SetTransition(R.id.start_button, SettingsActivity.class,this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}