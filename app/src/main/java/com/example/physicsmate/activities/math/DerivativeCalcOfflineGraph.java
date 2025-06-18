package com.example.physicsmate.activities.math;

import android.graphics.Color;
import android.os.Bundle;
import com.example.physicsmate.R;
import com.example.physicsmate.ui.BaseActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import static com.example.physicsmate.Custom_methods.addArrayListEntries;
import static com.example.physicsmate.Custom_methods.mChartProperties;

public class DerivativeCalcOfflineGraph extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_chart_universal);


        String input = getIntent().getStringExtra("input");
        String DerInt = getIntent().getStringExtra("DerInt");
        float xmin = (float) getIntent().getDoubleExtra("minX", 0.1);
        float xmax = (float) getIntent().getDoubleExtra("maxX", 50.0);

        LineChart derCalcOffGraph = findViewById(R.id.lineChart);

        mChartProperties(derCalcOffGraph);
        ArrayList<Entry> inputLineArray = new ArrayList<>();
        ArrayList<Entry> derLineArray = new ArrayList<>();


        addArrayListEntries(input, xmin, xmax, inputLineArray);
        //________________________________________________________________________

        addArrayListEntries(DerInt, xmin, xmax, derLineArray);
        //________________________________________________________________________


        LineDataSet dataSet1 = new LineDataSet(inputLineArray, "Function");
        LineDataSet dataSet2 = new LineDataSet(derLineArray, "Derivative");
        dataSet1.setDrawCircles(false);
        dataSet1.setColor(Color.RED);
        dataSet1.setValueTextColor(Color.WHITE);
        dataSet2.setDrawCircles(false);
        dataSet2.setColor(Color.WHITE);
        dataSet2.setValueTextColor(Color.WHITE);

        // Create a LineData object with the dataSet
        LineData lineData = new LineData(dataSet1, dataSet2);
        lineData.setValueTextColor(Color.WHITE);

        // Set the data to the chart
        derCalcOffGraph.setData(lineData);

        // Invalidate the chart to refresh the view
        derCalcOffGraph.invalidate();
    }


}