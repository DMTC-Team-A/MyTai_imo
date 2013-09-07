package com.example.mytai_imo;

import com.example.mytai_imo.utils.App;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
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
	
	float result;
  float kyounotaijuu = 88.8f;
  float TAIJU = 70.0F;
		float SINTYOU=170;
		float fBMI;
		int		BMI;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  this.getActionBar().hide();
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

  float baseWeight = App.Settings.getSettings().getBaseWeight();
  // カスタムビューを設定
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(
            LAYOUT_INFLATER_SERVICE);
        final View dialog_layout = inflater.inflate(R.layout.weight_dialog,
           (ViewGroup)findViewById(R.id.layout_root));
        
        TextView hutaketa_text = (TextView) dialog_layout.findViewById(R.id.textView_10);
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
     TextView textView = (TextView) dialog_layout.findViewById(R.id.textView_10);
     textView.setText(String.valueOf(++count));
    }
    if(oldVal == 0 && newVal == 9) {
     TextView textView = (TextView) dialog_layout.findViewById(R.id.textView_10);
     textView.setText(String.valueOf(--count));
    }
    
    result = count + (float)newVal * 0.1f; 
    ((TextView)findViewById(R.id.weight_result)).setText(String.valueOf(result));
    
	
	TAIJU=result;
	
	TextView colorEdit = (TextView)findViewById(R.id.textView2);
	colorEdit.setTextColor(kyounotaijuu < 80 ? Color.BLUE : Color.RED);
	((TextView)findViewById(R.id.textView2)).setText(String.valueOf(TAIJU));
	
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