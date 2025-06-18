package com.example.physicsmate.activities.math;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.*;
import com.example.physicsmate.R;
import com.example.physicsmate.ui.BaseActivity;

import static com.example.physicsmate.Custom_methods.*;


public class VEMultiply extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veadd);

        EditText etAi, etAj, etAk, etBi, etBj, etBk;
        Button btnVEAdd;
        TextView tvVEAddResults;
        ScrollView SV_VEMult;

        etAi = findViewById(R.id.etAi);
        etAj = findViewById(R.id.etAj);
        etAk = findViewById(R.id.etAk);
        etBi = findViewById(R.id.etBi);
        etBj = findViewById(R.id.etBj);
        etBk = findViewById(R.id.etBk);
        SV_VEMult = findViewById(R.id.SV_VE);

        btnVEAdd = findViewById(R.id.btnVEAdd);

        tvVEAddResults = findViewById(R.id.tvVEAddResults);

        tvVEAddResults.setVisibility(View.GONE);

        et_Scroller(etAi, VEMultiply.this);

        et_Scroller(etAj, VEMultiply.this);

        et_Scroller(etAk, VEMultiply.this);

        et_Scroller(etBi, VEMultiply.this);

        et_Scroller(etBj, VEMultiply.this);

        et_Scroller(etBk, VEMultiply.this);


        TextChangeListeners(tvVEAddResults, etAi, etBi, etAj, etBj, etAk, etBk);

        btnVEAdd.setOnClickListener(view ->
        {
            if (etAi.getText().toString().trim().isEmpty() || etAj.getText().toString().trim().isEmpty() || etAk.getText().toString().trim().isEmpty() || etBi.getText().toString().trim().isEmpty() || etBj.getText().toString().trim().isEmpty() || etBk.getText().toString().trim().isEmpty()) {
                Toast.makeText(VEMultiply.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                double Ai = evalf(etAi.getText().toString().trim());
                double Aj = evalf(etAj.getText().toString().trim());
                double Ak = evalf(etAk.getText().toString().trim());
                double Bi = evalf(etBi.getText().toString().trim());
                double Bj = evalf(etBj.getText().toString().trim());
                double Bk = evalf(etBk.getText().toString().trim());

                /*Only for this project
                double Ci = Ai + Bi;
                double Cj = Aj + Bj;
                double Ck = Ak + Bk; */     //till here

                double Amag = Math.sqrt((Ai * Ai) + (Aj * Aj) + (Ak * Ak));
                double Bmag = Math.sqrt((Bi * Bi) + (Bj * Bj) + (Bk * Bk));

                double Ci = (Aj * Bk) - (Ak * Bj); //i
                double Cj = (Ak * Bi) - (Ai * Bk); //j
                double Ck = (Ai * Bj) - (Aj * Bi); //k

                double Cmag = Math.sqrt((Ci * Ci) + (Cj * Cj) + (Ck * Ck));

                double dotproductAB = (Ai * Bi) + (Aj * Bj) + (Ak * Bk);
                double dotproductBC = (Bi * Ci) + (Bj * Cj) + (Bk * Ck);
                double dotproductAC = (Ai * Ci) + (Aj * Cj) + (Ak * Ck);

                double ABangle = DoubleNanChecker(Math.toDegrees(Math.acos(dotproductAB / (Amag * Bmag))));
                double ACangle = DoubleNanChecker(Math.toDegrees(Math.acos(dotproductBC / (Cmag * Bmag))));
                double BCangle = DoubleNanChecker(Math.toDegrees(Math.acos(dotproductAC / (Amag * Cmag))));


                if (Cj < 0 & Ck > 0) {
                    String result = "<font color = #6BBBE1> Dot product of vector A and B is  </font> <font color = #CDDC39>" + rounded_String(dotproductAB) + "</font><br><br>" + "<font color = #6BBBE1> Cross product of vector A and B is  </font> <font color = #CDDC39>" + rounded_String(Ci) + "i - " + rounded_String(-Cj) + "j + " + rounded_String(Ck) + "k" + "</font><br><br>" + "<font color = #6BBBE1> Magnitude of vector A is </font> <font color = #CDDC39>" + rounded_String(Amag) + "</font><br><br>" + "<font color = #6BBBE1> Magnitude of vector B is </font> <font color = #CDDC39>" + rounded_String(Bmag) + "</font><br><br>" + "<font color = #6BBBE1> Magnitude of vector C is </font> <font color = #CDDC39>" + rounded_String(Cmag) + "</font><br><br>" + "<font color = #6BBBE1> Angle between vector A and B is </font> <font color = #CDDC39>" + ABangle + "</font><br><br>" + "<font color = #6BBBE1> Angle between vector B and C is </font> <font color = #CDDC39>" + BCangle + "</font><br><br>" + "<font color = #6BBBE1> Angle between vector A and C is </font> <font color = #CDDC39>" + ACangle + "</font>";

                    tvVEAddResults.setText(Html.fromHtml(result, Html.FROM_HTML_MODE_LEGACY));
                    tvVEAddResults.setVisibility(View.VISIBLE);

                } else if (Cj > 0 & Ck < 0) {
                    String result = "<font color = #6BBBE1> Dot product of vector A and B is  </font> <font color = #CDDC39>" + rounded_String(dotproductAB) + "</font><br><br>" + "<font color = #6BBBE1> Cross product of vector A and B is </font> <font color = #CDDC39>" + rounded_String(Ci) + "i + " + rounded_String(Cj) + "j - " + rounded_String(-Ck) + "k" + "</font><br><br>" + "<font color = #6BBBE1> Magnitude of vector A is </font> <font color = #CDDC39>" + rounded_String(Amag) + "</font><br><br>" + "<font color = #6BBBE1> Magnitude of vector B is </font> <font color = #CDDC39>" + rounded_String(Bmag) + "</font><br><br>" + "<font color = #6BBBE1> Magnitude of vector C is </font> <font color = #CDDC39>" + rounded_String(Cmag) + "</font><br><br>" + "<font color = #6BBBE1> Angle between vector A and B is </font> <font color = #CDDC39>" + ABangle + "</font><br><br>" + "<font color = #6BBBE1> Angle between vector B and C is </font> <font color = #CDDC39>" + BCangle + "</font><br><br>" + "<font color = #6BBBE1> Angle between vector A and C is </font> <font color = #CDDC39>" + ACangle + "</font>";

                    tvVEAddResults.setText(Html.fromHtml(result, Html.FROM_HTML_MODE_LEGACY));
                    tvVEAddResults.setVisibility(View.VISIBLE);

                } else if (Cj < 0 & Ck < 0) {
                    String result = "<font color = #6BBBE1> Dot product of vector A and B is  </font> <font color = #CDDC39>" + rounded_String(dotproductAB) + "</font><br><br>" + "<font color = #6BBBE1> Cross product of vector A and B is </font> <font color = #CDDC39>" + rounded_String(Ci) + "i - " + rounded_String(-Cj) + "j - " + rounded_String(-Ck) + "k" + "</font><br><br>" + "<font color = #6BBBE1> Magnitude of vector A is </font> <font color = #CDDC39>" + rounded_String(Amag) + "</font><br><br>" + "<font color = #6BBBE1> Magnitude of vector B is </font> <font color = #CDDC39>" + rounded_String(Bmag) + "</font><br><br>" + "<font color = #6BBBE1> Magnitude of vector C is </font> <font color = #CDDC39>" + rounded_String(Cmag) + "</font><br><br>" + "<font color = #6BBBE1> Angle between vector A and B is </font> <font color = #CDDC39>" + ABangle + "</font><br><br>" + "<font color = #6BBBE1> Angle between vector B and C is </font> <font color = #CDDC39>" + BCangle + "</font><br><br>" + "<font color = #6BBBE1> Angle between vector A and C is </font> <font color = #CDDC39>" + ACangle + "</font>";

                    tvVEAddResults.setText(Html.fromHtml(result, Html.FROM_HTML_MODE_LEGACY));
                    tvVEAddResults.setVisibility(View.VISIBLE);

                } else {
                    String result = "<font color = #6BBBE1> Dot product of vector A and B is  </font> <font color = #CDDC39>" + rounded_String(dotproductAB) + "</font><br><br>" + "<font color = #6BBBE1> Cross product of vector A and B is </font> <font color = #CDDC39>" + rounded_String(Ci) + "i + " + rounded_String(Cj) + "j + " + rounded_String(Ck) + "k" + "</font><br><br>" + "<font color = #6BBBE1> Magnitude of vector A is </font> <font color = #CDDC39>" + rounded_String(Amag) + "</font><br><br>" + "<font color = #6BBBE1> Magnitude of vector B is </font> <font color = #CDDC39>" + rounded_String(Amag) + "</font><br><br>" + "<font color = #6BBBE1> Magnitude of vector C is </font> <font color = #CDDC39>" + rounded_String(Amag) + "</font><br><br>" + "<font color = #6BBBE1> Angle between vector A and B is </font> <font color = #CDDC39>" + ABangle + "</font><br><br>" + "<font color = #6BBBE1> Angle between vector B and C is </font> <font color = #CDDC39>" + BCangle + "</font><br><br>" + "<font color = #6BBBE1> Angle between vector A and C is </font> <font color = #CDDC39>" + ACangle + "</font>";

                    tvVEAddResults.setText(Html.fromHtml(result, Html.FROM_HTML_MODE_LEGACY));
                    tvVEAddResults.setVisibility(View.VISIBLE);
                }

                SV_VEMult.post(() -> SV_VEMult.smoothScrollTo(0, btnVEAdd.getBottom()));
            } //everything for btn inside this
        });
    }
}
