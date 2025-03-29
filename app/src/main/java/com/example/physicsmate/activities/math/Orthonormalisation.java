package com.example.physicsmate.activities.math;

import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.paris.Paris;
import com.example.physicsmate.Custom_methods;
import com.example.physicsmate.R;
import org.matheclipse.core.eval.ExprEvaluator;

import java.util.ArrayList;
import java.util.List;

import static com.example.physicsmate.Custom_methods.HtmlNumberFormatter;

public class Orthonormalisation extends AppCompatActivity {

    Button btnNext;
    EditText etDimension;
    LinearLayout vectorsContainer, orthonormalisation_LL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orthonormalisation);

        // Initialize UI elements
        btnNext = findViewById(R.id.orthonormalisation_btn);
        etDimension = findViewById(R.id.orthonormalisation_dim);
        vectorsContainer = findViewById(R.id.vectorsContainer);
        orthonormalisation_LL = findViewById(R.id.orthonormalisation_LL);

        // Set up button click listener
        btnNext.setOnClickListener(v -> {
            String dimStr = etDimension.getText().toString();
            if (dimStr.isEmpty() || Integer.parseInt(dimStr) <= 0) {
                Toast.makeText(this, "Enter a valid dimension", Toast.LENGTH_SHORT).show();
                return;
            }

            int dimension = Integer.parseInt(dimStr);
            createVectorInputFields(dimension);
        });
    }

    private void createVectorInputFields(int dimension) {
        vectorsContainer.removeAllViews();

        for (int i = 0; i < dimension; i++) {
            HorizontalScrollView scrollView = new HorizontalScrollView(this);
            scrollView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            LinearLayout vectorRow = new LinearLayout(this);
            vectorRow.setOrientation(LinearLayout.HORIZONTAL);

            for (int j = 0; j < dimension; j++) {
                EditText componentInput = new EditText(this);
                componentInput.setHint("v" + (i + 1) + "_" + (j + 1));
                Paris.styleBuilder(componentInput).add(R.style.custom_edittext_mini).apply();
                componentInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                vectorRow.addView(componentInput);
            }

            scrollView.addView(vectorRow);
            vectorsContainer.addView(scrollView);
        }

        Button solveButton = new Button(this);
        solveButton.setText("Orthonormalise");
        Paris.styleBuilder(solveButton).add(R.style.custom_button_enabled).apply();
        vectorsContainer.addView(solveButton);

        solveButton.setOnClickListener(v -> performOrthonormalisation(dimension));
    }

    private void performOrthonormalisation(int dimension) {
        List<List<Double>> vectors = new ArrayList<>();

        for (int i = 0; i < dimension; i++) {
            HorizontalScrollView scrollView = (HorizontalScrollView) vectorsContainer.getChildAt(i);
            LinearLayout vectorRow = (LinearLayout) scrollView.getChildAt(0);
            List<Double> vector = new ArrayList<>();

            for (int j = 0; j < dimension; j++) {
                EditText componentInput = (EditText) vectorRow.getChildAt(j);
                String componentText = componentInput.getText().toString();
                if (componentText.isEmpty()) {
                    Toast.makeText(this, "Please fill all components", Toast.LENGTH_SHORT).show();
                    return;
                }
                vector.add(Double.parseDouble(componentText));
            }
            vectors.add(vector);
        }

        //insert textview result
        TextView tvResult = new TextView(this);
        Paris.styleBuilder(tvResult).add(R.style.answer_textView).apply();
        orthonormalisation_LL.addView(tvResult);

        tvResult.setText(Html.fromHtml(parseStringForOrthonormalisation(orthogonalizeWithSymja(vectors)), Html.FROM_HTML_MODE_LEGACY));
    }

    private String parseStringForOrthonormalisation(String s) {
        //convert a string like {{3/Sqrt(10),1/Sqrt(10)},{-Sqrt(5/2)/5,3/5*Sqrt(5/2)}} into
        // "<font color=#CDDC39> (3/Sqrt(10),1/Sqrt(10) <br> (-Sqrt(5/2)/5,3/5*Sqrt(5/2) </font>"
        s = s.substring(1, s.length() - 1);
        s = s.replace("},{", "<br><br>");
        s = s.replace("{{", "");
        s = s.replace("}}", "");
        s = "<br>The orthonormalised vectors are <br><br>" + HtmlNumberFormatter(Orthonormalisation.this, s);
        //remove any { or } left
        s = s.replace("{", "");
        s = s.replace("}", "");
        return s;
    }

    private String orthogonalizeWithSymja(List<List<Double>> vectors) {
        // Initialize Symja evaluator
        ExprEvaluator evaluator = new ExprEvaluator();

        // Convert the input vectors to a Symja-compatible string format
        StringBuilder symjaInput = new StringBuilder("{");
        for (int i = 0; i < vectors.size(); i++) {
            symjaInput.append("{");
            for (int j = 0; j < vectors.get(i).size(); j++) {
                symjaInput.append(vectors.get(i).get(j));
                if (j < vectors.get(i).size() - 1) {
                    symjaInput.append(",");
                }
            }
            symjaInput.append("}");
            if (i < vectors.size() - 1) {
                symjaInput.append(",");
            }
        }
        symjaInput.append("}");

        // Call the Orthogonalize function in Symja
        String orthogonalizedVectors = Custom_methods.Orthogonalize(symjaInput.toString());
        return orthogonalizedVectors;
    }

}
