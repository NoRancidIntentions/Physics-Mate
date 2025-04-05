package com.example.physicsmate.activities.physics;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.physicsmate.R;

import java.util.Locale;

import static com.example.physicsmate.Custom_methods.*;

public class tmpclass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmp);

        EditText etBi;
        Button btnVEAdd;
        TextView tvVEAddResults;
        ScrollView SV_VEAdd;

        etBi = findViewById(R.id.etBi);
        SV_VEAdd = findViewById(R.id.SV_VE);
        btnVEAdd = findViewById(R.id.btnVEAdd);
        tvVEAddResults = findViewById(R.id.tvVEAddResults);
        tvVEAddResults.setVisibility(View.GONE);


        et_Scroller(etBi, tmpclass.this);



        TextChangeListeners(tvVEAddResults, etBi);

        btnVEAdd.setOnClickListener(view -> {
            if (etBi.getText().toString().trim().isEmpty()) {
                Toast.makeText(tmpclass.this, "Say Yes", Toast.LENGTH_SHORT).show();
            } else {
                String Bi = etBi.getText().toString().toLowerCase().trim();

                String result;

                if (Bi.equals("yes")) {
                    result = "Heh, You are gay :D";

                } else if (Bi.equals("no")) {
                    result = "Idc, You are gay anyway";

                } else {
                    result = "You are gay cuz you didn't answer in yes or no";

                }

                tvVEAddResults.setText(Html.fromHtml(HtmlNumberFormatter(tmpclass.this, result), Html.FROM_HTML_MODE_LEGACY));
                tvVEAddResults.setVisibility(View.VISIBLE);

                SV_VEAdd.post(() -> SV_VEAdd.smoothScrollTo(0, btnVEAdd.getBottom()));

            }
        });


    }


}