package com.example.physicsmate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.physicsmate.R;

import static com.example.physicsmate.Custom_methods.et_Scroller;
import static com.example.physicsmate.Custom_methods.evalf;

public class CSCircleGraphInfoGetter extends AppCompatActivity {

    EditText etH, etK, etR;
    Button btnCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cscircle_graph_info_getter);

        etH = findViewById(R.id.etCSCirH);
        etR = findViewById(R.id.etCSCirR);
        btnCalc = findViewById(R.id.button21);
        etK = findViewById(R.id.etCSCirK);

        et_Scroller(etH, this);
        et_Scroller(etK, this);
        et_Scroller(etR, this);


        btnCalc.setOnClickListener(v -> {
            if (etH.getText().toString().isEmpty() || etK.getText().toString().isEmpty() || etR.getText().toString().isEmpty()) {
                Toast.makeText(CSCircleGraphInfoGetter.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            } else {
                double h = evalf(etH.getText().toString());
                double k = evalf(etK.getText().toString());
                double r = evalf(etR.getText().toString());

                Intent intent = new Intent(CSCircleGraphInfoGetter.this, CSCircleGraphShower.class);

                intent.putExtra("h", h);
                intent.putExtra("k", k);
                intent.putExtra("r", r);
                startActivity(intent);

                /*lineChart.setMinimumHeight(lineChart.getWidth());

                LineDataSet set1 = new LineDataSet(yValuesPos, "");
                LineDataSet set2 = new LineDataSet(yValuesNeg, "");

                set1.setFillAlpha(110);
                set1.setDrawCircles(false);
                set1.setLineWidth(1.7f);
                set1.setColor(Color.WHITE);
                set1.setValueTextColor(Color.WHITE);
                set1.setValueTextSize(15f);

                set2.setFillAlpha(110);
                set2.setDrawCircles(false);
                set2.setLineWidth(1.7f);
                set2.setColor(Color.WHITE);
                set2.setValueTextColor(Color.WHITE);
                set2.setValueTextSize(15f);


                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(set1);
                dataSets.add(set2);

                LineData data = new LineData(dataSets);
                lineChart.setData(data);

                lineChart.setVisibility(View.VISIBLE);
            }

                LineDataSet dataSet1 = new LineDataSet(yValuesPos, null);
                LineDataSet dataSet2 = new LineDataSet(yValuesNeg, null);

                LineData lineData = new LineData(dataSet1, dataSet2);

                dataSet1.setColor(Color.RED);
                dataSet1.setValueTextColor(Color.BLACK);
                dataSet1.setLineWidth(2f);

                dataSet2.setColor(R.color.);
                dataSet2.setValueTextColor(Color.BLACK);
                dataSet2.setLineWidth(2f);

                dataSet1.setDrawCircles(false);
                dataSet2.setDrawCircles(false);*/

                //mChartDataSetter(lineChart,this, yValuesPos, yValuesNeg);

                //lineChart.setData(lineData);
            }
        });

    }
}
