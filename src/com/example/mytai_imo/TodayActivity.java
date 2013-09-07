package com.example.mytai_imo;

import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;

public class TodayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_today);
		
		setGraphData();
		
		final RelativeLayout layout = (RelativeLayout) findViewById(R.id.RelativeLayout);
		findViewById(R.id.buttonx).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				layout.removeView(TodayActivity.this.findViewById(R.id.imageView1));
				layout.removeView(TodayActivity.this.findViewById(R.id.buttonx));
			}
		});
		
	}
	
	private void setGraphData() {
		LineGraph graph = (LineGraph)findViewById(R.id.graph);
		graph.setRangeY(-1, 1);
		
		Line line = new Line();
		int x = 0;
		for(float y : new float[]{0,-0.3f,0.6f,-0.1f,-0.3f,-0.6f}) {
			LinePoint p = new LinePoint();
			p.setX(x++);
			p.setY(y);
			line.addPoint(p);
		}
		
		graph.addLine(line);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.today, menu);
		return true;
	}

}
