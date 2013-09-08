package com.example.mytai_imo;

import com.example.mytai_imo.utils.App;
import com.example.mytai_imo.utils.Graph;
import com.example.mytai_imo.utils.Weight;
import com.example.mytai_imo.utils.WeightDatabase;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TodayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.getActionBar().hide();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_today);
		
		WelcomeAnimationStart();
		
		RelativeLayout graphLayout = (RelativeLayout) findViewById(R.id.graphLayout);
		graphLayout.addView(Graph.getGraphView(this));
		
		findViewById(R.id.imageButton_weight).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showWeightDialog();
				
			}
		});
		
	}
	
	private void WelcomeAnimationStart() {
		final RelativeLayout layout = (RelativeLayout) findViewById(R.id.RelativeLayout);
		final ImageButton welcomeButton = (ImageButton) findViewById(R.id.buttonx);
		
		welcomeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FadeOutWelcome(welcomeButton);
			}
		});

		AnimationSet animationSet = new AnimationSet(true){{
			addAnimation(new AlphaAnimation(0, 1){{ setDuration(1000); }});
			addAnimation(new ScaleAnimation(0.5f, 1, 0.5f, 1) {{ setDuration(1000); }});
		}};
		welcomeButton.startAnimation(animationSet);
		welcomeButton.setVisibility(View.VISIBLE);
	}
	
	private void FadeOutWelcome(final ImageButton welcomeButton) {
		AnimationSet animationSet = new AnimationSet(true){{
			addAnimation(new AlphaAnimation(1, 0){{ setDuration(1000); }});
			addAnimation(new ScaleAnimation(1, 0.5f, 1, 0.5f) {{ setDuration(1000); }});
		}};
		animationSet.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {}
			@Override
			public void onAnimationRepeat(Animation animation) {}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				((ViewGroup)welcomeButton.getParent()).removeView(welcomeButton);
			}
		});
		welcomeButton.startAnimation(animationSet);
		welcomeButton.setVisibility(View.VISIBLE);
	}
	
	private void showWeightDialog() {
		float baseWeight = App.Settings.getSettings().getBaseWeight();
		// カスタムビューを設定
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(
        				LAYOUT_INFLATER_SERVICE);
        final View dialog_layout = inflater.inflate(R.layout.weight_dialog,
        			(ViewGroup)findViewById(R.id.layout_root));
        
        TextView hutaketa_text = (TextView) dialog_layout.findViewById(R.id.text_futaketa);
        int hutaketa = (int)baseWeight;
        hutaketa_text.setText(String.valueOf(hutaketa));
        final NumberPicker numPicker = (NumberPicker)dialog_layout.findViewById(R.id.numPicker);
        numPicker.setMinValue(0);
        numPicker.setMaxValue(9);
        int shosuDaiItii = (int)((baseWeight - hutaketa) * 10);
        numPicker.setValue(shosuDaiItii);
        numPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            int count = (int)App.Settings.getSettings().getBaseWeight();
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				if(oldVal == 9 && newVal == 0) {
					TextView textView = (TextView) dialog_layout.findViewById(R.id.text_futaketa);
					textView.setText(String.valueOf(++count));
				}
				if(oldVal == 0 && newVal == 9) {
					TextView textView = (TextView) dialog_layout.findViewById(R.id.text_futaketa);
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
            	
            	//((ViewGroup)imageButton_weight.getParent()).removeView(imageButton_weight);
				
            	int count = Integer.parseInt(((TextView)dialog_layout.findViewById(R.id.text_futaketa)).getText().toString());
				float result = count + (float)numPicker.getValue() * 0.1f;
				WeightDatabase.getInstance().InsertOrUpdateWeight(new Weight(result));

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
