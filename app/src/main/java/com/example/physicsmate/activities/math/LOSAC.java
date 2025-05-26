package com.example.physicsmate.activities.math;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.paris.Paris;
import com.example.physicsmate.R;

import static com.example.physicsmate.Custom_methods.*;


public class LOSAC extends AppCompatActivity {

    Button losac1, losac2, losac3, losac4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_losac);

        losac1 = findViewById(R.id.button10);
        losac2 = findViewById(R.id.button4);
        losac3 = findViewById(R.id.button5);
        losac4 = findViewById(R.id.button7);

        losac1.setOnClickListener(view ->
        {
            startActivity(new Intent(this, LOSAC4.class));
            finish();
        });

        losac2.setOnClickListener(view ->
        {
            Intent intent = new Intent(LOSAC.this, LOSAC2.class);
            startActivity(intent);
            finish();
        });

        losac3.setOnClickListener(view ->
        {
            Intent intent = new Intent(LOSAC.this, LOSAC1.class);
            startActivity(intent);
            finish();
        });

        losac4.setOnClickListener(view ->
        {
            Intent intent = new Intent(LOSAC.this, LOSAC3.class);
            startActivity(intent);
            finish();
        });
    }

    public static class LOSAC1 extends AppCompatActivity {

        EditText AB, BC, C;
        Button btnCalculate;
        TextView tvresult;
        ScrollView SV_LOSAC1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            AB = findViewById(R.id.et1_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            BC = findViewById(R.id.et2_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            C = findViewById(R.id.et3_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            btnCalculate = findViewById(R.id.btnCalc_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            tvresult = findViewById(R.id.tvResults_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            SV_LOSAC1 = findViewById(R.id.SV_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            TextView ABtv = findViewById(R.id.tv1_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            TextView BCtv = findViewById(R.id.tv2_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            TextView Ctv = findViewById(R.id.tv3_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            ABtv.setText("Enter length of AB");
            BCtv.setText("Enter length of BC");
            Ctv.setText("Enter value of angle C in degrees");

            tvresult.setVisibility(View.GONE);
            Paris.styleBuilder(tvresult).add(R.style.answer_textView).apply();


            TextChangeListeners(tvresult, AB, BC, C);

            btnCalculate.setOnClickListener(view ->
            {

                double c = evalf(C.getText().toString().trim());

                if (AB.getText().toString().trim().isEmpty() || BC.getText().toString().trim().isEmpty() || C.getText().toString().trim().isEmpty()) {
                    Toast.makeText(LOSAC1.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                } else if (c < 0 || c >= 180) {
                    Toast.makeText(LOSAC1.this, "angle cannot be less than 0 or greater than or equal to 180", Toast.LENGTH_SHORT).show();
                } else {

                    double ab = evalf(AB.getText().toString().trim());
                    double bc = evalf(BC.getText().toString().trim());

                    double k = ab / Math.sin(Math.toRadians(c));
                    double a = Math.asin(bc / k);

                    double b = Math.PI - (a + Math.toRadians(c));

                    double ac = k * Math.sin(b);

                    double bdegree = Math.toDegrees(b);
                    double adegree = Math.toDegrees(a);


                    String AC = rounded_String(ac, AB, C, BC);
                    String ADEGREE = rounded_String(adegree, AB, C, BC);
                    String BDEGREE = rounded_String(bdegree, AB, C, BC);

                    double s = (ab + bc + ac) / 2; //half perimeter

                    double area = Math.sqrt(s * (s - ab) * (s - bc) * (s - ac));
                    String AREA = rounded_String(area, AB, C, BC);

                    String text = "Length of AC is approximately " + AC + "<br><br>" + "Angle a is approximately " +
                            ADEGREE + "Angle c is approximately " + BDEGREE + "<Area of the triangle is approximately " +
                            AREA;


                    tvresult.setText(Html.fromHtml(HtmlNumberFormatter(LOSAC1.this, text), Html.FROM_HTML_MODE_LEGACY));
                    tvresult.setVisibility(View.VISIBLE);

                    SV_LOSAC1.post(() -> SV_LOSAC1.smoothScrollTo(0, btnCalculate.getBottom()));
                }
            });


        }
    }

    public static class LOSAC2 extends AppCompatActivity {

        EditText AB, BC, B;
        Button btnCalculate;
        TextView tvresult;
        ScrollView SV_LOSAC2;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            AB = findViewById(R.id.et1_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            BC = findViewById(R.id.et2_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            B = findViewById(R.id.et3_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            btnCalculate = findViewById(R.id.btnCalc_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            tvresult = findViewById(R.id.tvResults_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            SV_LOSAC2 = findViewById(R.id.SV_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            TextView ABtv = findViewById(R.id.tv1_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            TextView BCtv = findViewById(R.id.tv2_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            TextView Btv = findViewById(R.id.tv3_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            ABtv.setText("Enter length of AB");
            BCtv.setText("Enter length of BC");
            Btv.setText("Enter value of angle B in degrees");

            tvresult.setVisibility(View.GONE);
            Paris.styleBuilder(tvresult).add(R.style.answer_textView).apply();


            TextChangeListeners(tvresult, AB, BC, B);

            btnCalculate.setOnClickListener(view ->
            {
                double b = evalf(B.getText().toString().trim());

                if (AB.getText().toString().trim().isEmpty() || BC.getText().toString().trim().isEmpty() || B.getText().toString().trim().isEmpty()) {
                    Toast.makeText(LOSAC2.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                } else if (b < 0 || b >= 180) {
                    Toast.makeText(LOSAC2.this, "angle cannot be less than 0 or greater than or equal to 180", Toast.LENGTH_SHORT).show();
                } else {
                    double ab = evalf(AB.getText().toString().trim());
                    double bc = evalf(BC.getText().toString().trim());

                    double ac = Math.sqrt((ab * ab) + (bc * bc) - (2 * ab * bc * (Math.cos(Math.toRadians(b)))));

                    double k = ac / Math.sin(Math.toRadians(b));
                    double c = Math.asin(ab / k);
                    double a = Math.asin(bc / k);

                    double s = (ab + bc + ac) / 2; //half perimeter

                    double area = Math.sqrt(s * (s - ab) * (s - bc) * (s - ac));
                    String AREA = rounded_String(area, AB, B, BC);

                    /*String text = "<font color=#6BBBE1>Length of AC is approximately </font> <font color=#CDDC39>"
                            + rounded_String(ac, AB, B, BC) + "</font><br><br>" + "<font color=#6BBBE1>Angle a is approximately </font> <font color=#CDDC39>"
                            + rounded_String(Math.toDegrees(a), AB, B, BC) + "</font><br><br>" + "<font color=#6BBBE1>Angle b is approximately </font> <font color=#CDDC39>"
                            + rounded_String(Math.toDegrees(c), AB, B, BC) + "</font><br><br>" + "<font color=#6BBBE1>Area of the triangle is approximately </font> <font color=#CDDC39>"
                            + AREA + "</font>";*/

                    String text = "Length of AC is approximately " + rounded_String(ac, AB, B, BC) + "<br><br>" + "Angle a is approximately " +
                            rounded_String(Math.toDegrees(a), AB, B, BC) + "Angle c is approximately " + rounded_String(Math.toDegrees(c), AB, B, BC) + "<Area of the triangle is approximately " +
                            AREA;


                    tvresult.setText(Html.fromHtml(HtmlNumberFormatter(LOSAC2.this, text), Html.FROM_HTML_MODE_LEGACY));
                    tvresult.setVisibility(View.VISIBLE);

                    SV_LOSAC2.post(() -> SV_LOSAC2.smoothScrollTo(0, btnCalculate.getBottom()));
                }
            });
        }
    }

    public static class LOSAC3 extends AppCompatActivity {

        EditText AB, BC, AC;
        Button btnCalculate;
        TextView tvresult;
        ScrollView SV_LOSAC3;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            AB = findViewById(R.id.et1_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            BC = findViewById(R.id.et2_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            AC = findViewById(R.id.et3_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            btnCalculate = findViewById(R.id.btnCalc_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            tvresult = findViewById(R.id.tvResults_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            SV_LOSAC3 = findViewById(R.id.SV_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            TextView ABtv = findViewById(R.id.tv1_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            TextView BCtv = findViewById(R.id.tv2_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            TextView ACtv = findViewById(R.id.tv3_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            ABtv.setText("Enter length of AB");
            BCtv.setText("Enter length of BC");
            ACtv.setText("Enter length of AC");

            TextChangeListeners(tvresult, AB, BC, AC);

            tvresult.setVisibility(View.GONE);
            Paris.styleBuilder(tvresult).add(R.style.answer_textView).apply();


            btnCalculate.setOnClickListener(view ->
            {
                if (AB.getText().toString().trim().isEmpty() || BC.getText().toString().trim().isEmpty() || AC.getText().toString().trim().isEmpty()) {
                    Toast.makeText(LOSAC3.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                } else {

                    double ab = evalf(AB.getText().toString().trim());
                    double bc = evalf(BC.getText().toString().trim());
                    double ac = evalf(AC.getText().toString().trim());

                    String b = rounded_String(Math.toDegrees(Math.acos(((ab * ab) + (bc * bc) - (ac * ac)) / (2 * ab * bc))), AB, BC, AC);
                    String c = rounded_String(Math.toDegrees(Math.acos(((ac * ac) + (bc * bc) - (ab * ab)) / (2 * ac * bc))), AB, BC, AC);
                    String a = rounded_String(Math.toDegrees(Math.acos(((ab * ab) + (ac * ac) - (bc * bc)) / (2 * ab * ac))), AB, BC, AC);

                    double s = (ab + bc + ac) / 2; //half perimeter

                    double area = Math.sqrt(s * (s - ab) * (s - bc) * (s - ac));
                    String AREA = rounded_String(area, AB, BC, AC);

                    //String text = "<font color=#6BBBE1>Angle a is approximately </font> <font color=#CDDC39>" + a + "</font><br><br>" + "<font color=#6BBBE1>Angle b is approximately </font> <font color=#CDDC39>" + b + "</font><br><br>" + "<font color=#6BBBE1>Angle c is approximately </font> <font color=#CDDC39>" + c + "</font><br><br>" + "<font color=#6BBBE1>Area of the triangle is approximately </font> <font color=#CDDC39>" + AREA + "</font>";

                    String text = "Angle a is approximately " + a + "<br><br>" + "Angle b is approximately " +
                            b + "<br><br>" + "Angle c is approximately " + c + "<br><br>" +
                            "Area of the triangle is approximately " + AREA;

                    tvresult.setText(Html.fromHtml(HtmlNumberFormatter(LOSAC3.this, text), Html.FROM_HTML_MODE_LEGACY));
                    tvresult.setVisibility(View.VISIBLE);

                    SV_LOSAC3.post(() -> SV_LOSAC3.smoothScrollTo(0, btnCalculate.getBottom()));

                }
            });
        }
    }

    public static class LOSAC4 extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            EditText AB, B, C;
            Button btnCalculate;
            TextView tvresult;
            ScrollView SV_LOSAC4;

            AB = findViewById(R.id.et1_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            B = findViewById(R.id.et2_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            C = findViewById(R.id.et3_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            btnCalculate = findViewById(R.id.btnCalc_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            tvresult = findViewById(R.id.tvResults_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            SV_LOSAC4 = findViewById(R.id.SV_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            TextView ABtv = findViewById(R.id.tv1_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            TextView Btv = findViewById(R.id.tv2_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            TextView Ctv = findViewById(R.id.tv3_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            ABtv.setText("Enter length of AB");
            Btv.setText("Enter the value of angle B in degrees");
            Ctv.setText("Enter the value of angle C in degrees");

            tvresult.setVisibility(View.GONE);
            Paris.styleBuilder(tvresult).add(R.style.answer_textView).apply();


            TextChangeListeners(tvresult, AB, B, C);

            btnCalculate.setOnClickListener(view ->
            {
                double b = evalf(B.getText().toString().trim());
                double c = evalf(C.getText().toString().trim());

                if (AB.getText().toString().trim().isEmpty() || B.getText().toString().trim().isEmpty() || C.getText().toString().trim().isEmpty()) {
                    Toast.makeText(LOSAC4.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                } else if (c < 0 || c >= 180 || b < 0 || b >= 180) {
                    Toast.makeText(LOSAC4.this, "angles cannot be negative or greater than 180", Toast.LENGTH_SHORT).show();
                } else if (b + c >= 180) {
                    Toast.makeText(LOSAC4.this, "Sum of angles cannot be greater than 180", Toast.LENGTH_SHORT).show();
                } else {

                    double ab = evalf(AB.getText().toString().trim());

                    double a = 180 - b - c;

                    double k = ab / Math.sin(Math.toRadians(c));

                    double bc = k * Math.sin(Math.toRadians(a));
                    double ac = k * Math.sin(Math.toRadians(b));


                    String AC = rounded_String(ac, AB, B, C);
                    String BC = rounded_String(bc, AB, B, C);
                    String ADEGREE = rounded_String(a, AB, B, C);

                    double s = (ab + bc + ac) / 2; //half perimeter

                    double area = Math.sqrt(s * (s - ab) * (s - bc) * (s - ac));
                    String AREA = rounded_String(area, AB, B, C);

                    String text = "Length of AC is approximately " + AC + "<br><br>" + "Length of BC is approximately " +
                            BC + "<br><br>" + "Angle a is approximately " + ADEGREE + "<br><br>" +
                            "Area of the triangle is approximately " + AREA;


                    tvresult.setText(Html.fromHtml(HtmlNumberFormatter(LOSAC4.this, text), Html.FROM_HTML_MODE_LEGACY));
                    tvresult.setVisibility(View.VISIBLE);

                    SV_LOSAC4.post(() -> SV_LOSAC4.smoothScrollTo(0, btnCalculate.getBottom()));
                }
            });
        }
    }
}
