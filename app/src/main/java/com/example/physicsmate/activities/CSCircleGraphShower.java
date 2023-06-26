package com.example.physicsmate.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.physicsmate.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

import static com.example.physicsmate.Custom_methods.mChartDataSetter;
import static com.example.physicsmate.Custom_methods.mChartProperties;


public class CSCircleGraphShower extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cscircle_graph_shower);

        LineChart lineChart = findViewById(R.id.CircleGraphLineChart);

        lineChart.setExtraOffsets(15f, 0f, 50f, 0f);

        ArrayList<Entry> yValues = mChartProperties(lineChart);

        double h = getIntent().getDoubleExtra("h", 0);
        double k = getIntent().getDoubleExtra("k", 0);
        double r = getIntent().getDoubleExtra("r", 1);

        for (float i = 0; i < 360; i += 0.005) {
            float x = (float) (Math.cos((i)) * r + h);
            float y = (float) (Math.sin((i)) * r + k);
            yValues.add(new Entry(x, y));
        }

        mChartDataSetter(lineChart, this, false, yValues);

        /*LineDataSet set1 = new LineDataSet(yValues, "");

        set1.setFillAlpha(110);
        set1.setDrawCircles(false);
        set1.setLineWidth(1.7f);
        set1.setColor(Color.WHITE);
        set1.setValueTextColor(Color.WHITE);
        set1.setValueTextSize(15f);

        set1.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);


        mChartDataSetter(lineChart, this, false, yValues);
        lineChart.setMinimumHeight(lineChart.getWidth());*/


    }
}