package com.example.physicsmate.activities;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.physicsmate.R;
import com.example.physicsmate.ui.main.UI_Solver_Tools_Linker;

import static com.example.physicsmate.Custom_methods.*;


public class VEAdd extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veadd);

        EditText etAi, etAj, etAk, etBi, etBj, etBk;
        Button btnVEAdd;
        TextView tvVEAddResults;
        ScrollView SV_VEAdd;

        etAi = findViewById(R.id.etAi);
        etAj = findViewById(R.id.etAj);
        etAk = findViewById(R.id.etAk);
        etBi = findViewById(R.id.etBi);
        etBj = findViewById(R.id.etBj);
        etBk = findViewById(R.id.etBk);
        SV_VEAdd = findViewById(R.id.SV_VE);

        btnVEAdd = findViewById(R.id.btnVEAdd);

        tvVEAddResults = findViewById(R.id.tvVEAddResults);

        tvVEAddResults.setVisibility(View.GONE);

        et_Scroller(etAi, VEAdd.this);
        et_Scroller(etAj, VEAdd.this);
        et_Scroller(etAj, VEAdd.this);
        et_Scroller(etBi, VEAdd.this);
        et_Scroller(etBj, VEAdd.this);
        et_Scroller(etBk, VEAdd.this);


        TextChangeListeners(tvVEAddResults, etBj, etAk, etBk, etAi, etBi, etAj);

        btnVEAdd.setOnClickListener(view ->
        {
            if (etAi.getText().toString().trim().isEmpty() || etAj.getText().toString().trim().isEmpty() || etAk.getText().toString().trim().isEmpty() || etBi.getText().toString().trim().isEmpty() || etBj.getText().toString().trim().isEmpty() || etBk.getText().toString().trim().isEmpty()) {
                Toast.makeText(VEAdd.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                double Ai = eval(etAi.getText().toString().trim());
                double Aj = eval(etAj.getText().toString().trim());
                double Ak = eval(etAk.getText().toString().trim());
                double Bi = eval(etBi.getText().toString().trim());
                double Bj = eval(etBj.getText().toString().trim());
                double Bk = eval(etBk.getText().toString().trim());

                //Only for this project
                double Ci = Ai + Bi;
                double Cj = Aj + Bj;
                double Ck = Ak + Bk; //till here

                double Amag = Math.sqrt((Ai * Ai) + (Aj * Aj) + (Ak * Ak));
                double Bmag = Math.sqrt((Bi * Bi) + (Bj * Bj) + (Bk * Bk));
                double Cmag = Math.sqrt((Ci * Ci) + (Cj * Cj) + (Ck * Ck));


                double ABangle = DoubleNanChecker(Math.toDegrees(Math.acos(((Ai * Bi) + (Aj * Bj) + (Ak * Bk)) / (Amag * Bmag))));
                double BCangle = DoubleNanChecker(Math.toDegrees(Math.acos(((Ci * Bi) + (Cj * Bj) + (Ck * Bk)) / (Cmag * Bmag))));
                double ACangle = DoubleNanChecker(Math.toDegrees(Math.acos(((Ai * Ci) + (Aj * Cj) + (Ak * Ck)) / (Amag * Cmag))));


                if (Cj < 0.0 && Ck > 0.0) {
                    String result = "<font color = #6BBBE1> Vector C =  </font> <font color = #CDDC39>" + rounded_String(Ci) + "i - " + rounded_String(-Cj) + "j + " + rounded_String(Ck) + "k </font>" + "<br>" + "<br>" + "<font color = #6BBBE1> Magnitude of Vector A is  </font> <font color = #CDDC39>" + Amag + "</font>" + "<br>" + "<br>" + "<font color = #6BBBE1> Magnitude of Vector B is  </font> <font color = #CDDC39>" + rounded_String(Bmag) + "</font>" + "<br>" + "<br>" + "<font color = #6BBBE1> Magnitude of Vector C is  </font> <font color = #CDDC39>" + rounded_String(Cmag) + "</font>" + "<br>" + "<br>" + "<font color = #6BBBE1> Angle between vector A and B is  </font> <font color = #CDDC39>" + ABangle + "</font>" + "<br>" + "<br>" + "<font color = #6BBBE1> Angle between vector B and C is  </font> <font color = #CDDC39>" + BCangle + "</font>" + "<br>" + "<br>" + "<font color = #6BBBE1> Angle between vector A and C is  </font> <font color = #CDDC39>" + ACangle + "</font>";

                    tvVEAddResults.setText(Html.fromHtml(result, Html.FROM_HTML_MODE_LEGACY));
                    tvVEAddResults.setVisibility(View.VISIBLE);


                } else if (Cj > 0.0 && Ck < 0.0) {
                    String result = "<font color = #6BBBE1> Vector C =  </font> <font color = #CDDC39>" + rounded_String(Ci) + "i + " + rounded_String(Cj) + "j - " + rounded_String(-Ck) + "k </font>" + "<br>" + "<br>" + "<font color = #6BBBE1> Magnitude of Vector A is  </font> <font color = #CDDC39>" + Amag + "</font>" + "<br>" + "<br>" + "<font color = #6BBBE1> Magnitude of Vector B is  </font> <font color = #CDDC39>" + rounded_String(Bmag) + "</font>" + "<br>" + "<br>" + "<font color = #6BBBE1> Magnitude of Vector C is  </font> <font color = #CDDC39>" + rounded_String(Cmag) + "</font>" + "<br>" + "<br>" + "<font color = #6BBBE1> Angle between vector A and B is  </font> <font color = #CDDC39>" + ABangle + "</font>" + "<br>" + "<br>" + "<font color = #6BBBE1> Angle between vector B and C is  </font> <font color = #CDDC39>" + BCangle + "</font>" + "<br>" + "<br>" + "<font color = #6BBBE1> Angle between vector A and C is  </font> <font color = #CDDC39>" + ACangle + "</font>";

                    tvVEAddResults.setText(Html.fromHtml(result, Html.FROM_HTML_MODE_LEGACY));
                    tvVEAddResults.setVisibility(View.VISIBLE);

                } else if (Cj < 0.0 && Ck < 0.0) {
                    String result = "<font color = #6BBBE1> Vector C =  </font> <font color = #CDDC39>" + rounded_String(Ci) + "i - " + rounded_String(-Cj) + "j - " + rounded_String(-Ck) + "k </font>" + "<br>" + "<br>" + "<font color = #6BBBE1> Magnitude of Vector A is  </font> <font color = #CDDC39>" + Amag + "</font>" + "<br>" + "<br>" + "<font color = #6BBBE1> Magnitude of Vector B is  </font> <font color = #CDDC39>" + rounded_String(Bmag) + "</font>" + "<br>" + "<br>" + "<font color = #6BBBE1> Magnitude of Vector C is  </font> <font color = #CDDC39>" + rounded_String(Cmag) + "</font>" + "<br>" + "<br>" + "<font color = #6BBBE1> Angle between vector A and B is  </font> <font color = #CDDC39>" + ABangle + "</font>" + "<br>" + "<br>" + "<font color = #6BBBE1> Angle between vector B and C is  </font> <font color = #CDDC39>" + BCangle + "</font>" + "<br>" + "<br>" + "<font color = #6BBBE1> Angle between vector A and C is  </font> <font color = #CDDC39>" + ACangle + "</font>";

                    tvVEAddResults.setText(Html.fromHtml(result, Html.FROM_HTML_MODE_LEGACY));
                    tvVEAddResults.setVisibility(View.VISIBLE);


                } else {
                    String result = "<font color = #6BBBE1> Vector C =  </font> <font color = #CDDC39>" + rounded_String(Ci) + "i + " + rounded_String(Cj) + "j + " + rounded_String(Ck) + "k </font>" + "<br>" + "<br>" + "<font color = #6BBBE1> Magnitude of Vector A is  </font> <font color = #CDDC39>" + Amag + "<br>" + "<br>" + "</font>" + "<font color = #6BBBE1> Magnitude of Vector B is  </font> <font color = #CDDC39>" + rounded_String(Bmag) + "<br>" + "<br>" + "</font>" + "<font color = #6BBBE1> Magnitude of Vector C is  </font> <font color = #CDDC39>" + rounded_String(Cmag) + "<br>" + "<br>" + "</font>" + "<font color = #6BBBE1> Angle between vector A and B is  </font> <font color = #CDDC39>" + ABangle + "<br>" + "<br>" + "</font>" + "<font color = #6BBBE1> Angle between vector B and C is  </font> <font color = #CDDC39>" + BCangle + "<br>" + "<br>" + "</font>" + "<font color = #6BBBE1> Angle between vector A and C is  </font> <font color = #CDDC39>" + ACangle + "</font>";

                    tvVEAddResults.setText(Html.fromHtml(result, Html.FROM_HTML_MODE_LEGACY));
                    tvVEAddResults.setVisibility(View.VISIBLE);
                }

                SV_VEAdd.post(() -> SV_VEAdd.smoothScrollTo(0, btnVEAdd.getBottom()));
            } //everything for btn inside this
        });


    }


}
