package com.example.mytai_imo;

import com.example.mytai_imo.utils.App;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
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
				layout.removeView(TodayActivity.this.findViewById(R.id.buttonx));
			}
		});
		
		findViewById(R.id.imageButton_weight).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showWeightDialog();
				
			}
		});
		
	}
	
	private void showWeightDialog() {
		final RelativeLayout layout = (RelativeLayout) findViewById(R.id.RelativeLayout);
		
		// カスタムビューを設定
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(
        				LAYOUT_INFLATER_SERVICE);
        final View dialog_layout = inflater.inflate(R.layout.weight_dialog,
        			(ViewGroup)findViewById(R.id.layout_root));
        final NumberPicker numPicker = (NumberPicker)dialog_layout.findViewById(R.id.numPicker);
        numPicker.setMinValue(0);
        numPicker.setMaxValue(9);
        numPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            int count = (int)App.Settings.getSettings().getBaseWeight();
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				if(oldVal == 9 && newVal == 0) {
					TextView textView = (TextView) dialog_layout.findViewById(R.id.textView_10);
					textView.setText(String.valueOf(++count));
				}
				if(oldVal == 0 && newVal == 9) {
					TextView textView = (TextView) dialog_layout.findViewById(R.id.textView_10);
					textView.setText(String.valueOf(--count));
				}
			}
		});
        
        // アラートダイアログ を生成
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("今日の体重を入力してね");
        builder.setView(dialog_layout);
        
        
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener () {
            public void onClick(DialogInterface dialog, int which) {
                // OK ボタンクリック処理
            	//layout.removeView(imageButtonWeight);
            }
        });

        // 表示
        builder.create().show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.today, menu);
		return true;
	}

	
}

