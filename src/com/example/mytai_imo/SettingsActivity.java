package com.example.mytai_imo;

import com.example.mytai_imo.utils.App;
import com.example.mytai_imo.utils.App.UserData;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.getActionBar().hide();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		// Show the Up button in the action bar.
		setupActionBar();
		if(App.Settings.isFirstSettingCompleted()) {
			UserData userData = App.Settings.getSettings();
			setUserData(userData);
		}
		
	//	App.SetTransition(R.id.register_button, TodayActivity.class, this);
		
		App.SetTransition(R.id.imageButton1, TodayActivity.class, this);
		
		findViewById(R.id.imageButton1).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//設定を取得した後↓を実行する
				String id = ((EditText)findViewById(R.id.edittext_id)).getText().toString();
				String name = ((EditText)findViewById(R.id.edittext_name)).getText().toString();
				Float height = Float.parseFloat(((EditText)findViewById(R.id.edittext_height)).getText().toString());
				Float baseWeight = Float.parseFloat(((EditText)findViewById(R.id.edittext_base_weight)).getText().toString());
				
				TimePicker timePicker = (TimePicker)findViewById(R.id.timePicker);
				int hour = timePicker.getCurrentHour();
				int minute = timePicker.getCurrentMinute();
				
				App.Settings.setSettings(name, id, height, baseWeight, hour, minute);
				
				App.Transition(SettingsActivity.this, TodayActivity.class);
			}
		});
		
	}
	
	private void setUserData(UserData userData) {
		((EditText)findViewById(R.id.edittext_id)).setText(userData.getId());
		((EditText)findViewById(R.id.edittext_name)).setText(userData.getName());
		((EditText)findViewById(R.id.edittext_height)).setText(String.valueOf(userData.getHeight()));
		((EditText)findViewById(R.id.edittext_base_weight)).setText(String.valueOf(userData.getBaseWeight()));
		
		TimePicker timePicker = (TimePicker)findViewById(R.id.timePicker);
		timePicker.setCurrentHour(userData.getTime().first);
		timePicker.setCurrentMinute(userData.getTime().second);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
