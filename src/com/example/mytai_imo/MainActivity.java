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
		this.getActionBar().hide();
		
		if(App.Settings.isFirstSettingCompleted()) {
			App.Transition(this, TodayActivity.class);
		}
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		App.SetTransition(R.id.start_button, SettingsActivity.class,this);

	}

}
