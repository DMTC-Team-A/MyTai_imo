package com.example.mytai_imo;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TodayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_today);
		
		final RelativeLayout layout = (RelativeLayout) findViewById(R.id.RelativeLayout);
		findViewById(R.id.buttonx).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				layout.removeView(TodayActivity.this.findViewById(R.id.imageView1));
				layout.removeView(TodayActivity.this.findViewById(R.id.buttonx));
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.today, menu);
		return true;
	}

}
