package com.example.mytai_imo;

import com.example.mytai_imo.R;
import com.example.mytai_imo.SettingsActivity;
import com.example.mytai_imo.utils.App;
//import com.example.mytai_imo.utils.WeightDatabase;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		App.Initialize(this);
		if(App.Settings.GetAllData().length == 0) {
			App.Settings.InputTestData();	//後で消す
		}
		//WeightDatabase.getInstance().ResetDatabase();	//後で消す
		//WeightDatabase.getInstance().InputTestData();	//後で消す
		this.getActionBar().hide();
		
		if(App.Settings.isFirstSettingCompleted()) {
			//App.Transition(this, TodayActivity.class);
		}
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		App.SetTransition(R.id.start_button, SettingsActivity.class,this);

	}

}
