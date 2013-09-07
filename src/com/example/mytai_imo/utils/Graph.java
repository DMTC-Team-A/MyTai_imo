package com.example.mytai_imo.utils;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYValueSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.view.View;

public class Graph {
	public static View getGraphView(Activity thisPage) {
		Float[] weightDiffs = new Float[]{0f,0.1f,0.3f,-0.1f,0.1f,0.2f,-0.3f};
		
		GraphicalView graphView = LineChartView(thisPage, weightDiffs);
		graphView.setBackgroundColor(Color.WHITE);
		return graphView;
	}
	
	private static GraphicalView LineChartView(Activity thisPage, Float[] yDoubleValue) 
    {
        // (2) グラフのタイトル、X軸Y軸ラベル、色等の設定
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setChartTitle("");     // グラフタイトル
        renderer.setChartTitleTextSize(24);             //
        renderer.setXTitle("日付");                     // X軸タイトル
        renderer.setAxisTitleTextSize(16);              // 
        renderer.setPointSize(7f);                      // ポイントマーカーサイズ
        renderer.setXAxisMin(0);  // X軸最小値
        renderer.setXAxisMax(6);    // X軸最大値
        renderer.setYAxisMin(-1.0);                     // Y軸最小値
        renderer.setYAxisMax(1.0);                    // Y軸最大値
        renderer.setXLabelsAlign(Align.CENTER);         // X軸ラベル配置位置
        renderer.setYLabelsAlign(Align.RIGHT);          // Y軸ラベル配置位置
        renderer.setAxesColor(Color.LTGRAY);            // X軸、Y軸カラー
        renderer.setXLabels(7);                         // X軸ラベルのおおよその数
        renderer.setYLabels(10);                         // Y軸ラベルのおおよその数
        renderer.setLabelsTextSize(24);
        // グリッド表示
        renderer.setShowGrid(true);
        // グリッド色
        renderer.setGridColor(Color.parseColor("#00FFFF")); // グリッドカラーaqua
        // グラフ描画領域マージン top, left, bottom, right
        renderer.setMargins(new int[] { 0, 0, 0, 0 });  // 

        // (3) データ系列の色、マーク等の設定
        XYSeriesRenderer r = new XYSeriesRenderer();
        r.setColor(Color.GREEN);
        r.setPointStyle(PointStyle.CIRCLE);
        r.setFillPoints(true);
        renderer.addSeriesRenderer(r);

        // (4) データ系列　データの設定
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        XYValueSeries series = new XYValueSeries("");     // データ系列凡例
        for (int i = 0; i < yDoubleValue.length; i++)
        {
            series.add(i, yDoubleValue[i]);
        }
        dataset.addSeries(series);
        
        GraphicalView mChartView=ChartFactory.getLineChartView(thisPage, dataset, renderer);
        
        return mChartView;
    }
	
	private Graph(){
		
	}
}
