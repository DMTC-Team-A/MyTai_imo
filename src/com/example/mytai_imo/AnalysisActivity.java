package com.example.mytai_imo;

import android.os.Bundle;


import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;


public class AnalysisActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_analysis);
		
		float kyounotaijuu = 88.8f;
		float TAIJU = 70.0F;
		float SINTYOU = 170.0F;
		float fBMI;
		int		BMI;
		TextView colorEdit = (TextView)findViewById(R.id.textView2);
		colorEdit.setTextColor(kyounotaijuu < 80 ? Color.BLUE : Color.RED);
		((TextView)findViewById(R.id.textView2)).setText(String.valueOf(kyounotaijuu));
		
		 fBMI = TAIJU / SINTYOU / SINTYOU * 10000 ;
		 BMI = (int)fBMI;
		TextView colorEdit2 = (TextView)findViewById(R.id.textView0);
		colorEdit2.setTextColor(20 < BMI && BMI < 24 ? Color.BLUE : (BMI < 20 ? Color.YELLOW : Color.RED));
		((TextView)findViewById(R.id.textView0)).setText(String.valueOf(BMI));

	//	((TextView)findViewById(R.id.textView4)).setText("適正体重です");
		
		if(20<=BMI && BMI<=24){
			((TextView)findViewById(R.id.textView4)).setText("適正体重です。");
		}else{
			if(BMI<20){
				((TextView)findViewById(R.id.textView4)).setText("やせ型です！");
			}else{
				((TextView)findViewById(R.id.textView4)).setText("肥満です！");
			}
		}
		
		((TextView)findViewById(R.id.textView5)).setText("昨日との差は"+ BMI);
		
	
		
		
		//20 < BMI && (BMI < 24) ? ((TextView)findViewById(R.id.textView4)).setText("適正体重です。") : (BMI<20 ? ((TextView)findViewById(R.id.textView4)).setText("やせ型です！") : ((TextView)findViewById(R.id.textView4)).setText("肥満です！")
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.analysis, menu);
		return true;
	}

}
