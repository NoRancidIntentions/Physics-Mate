package com.example.physicsmate.ui.main;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import com.example.physicsmate.R;

public class custom_keyboard1_initializer extends GridLayout {

    private EditText targetEditText;
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9,
            button0, buttonPlus, buttonMinus, buttonMultiply, buttonDivide, buttonBackspace,
            buttonExponent, buttonLn, buttonlog10, buttonFactorial, buttonModulus, buttonSin, buttonCos,
            buttonTan, buttonPi, buttonE, buttonLeftBracket, buttonRightBracket, buttonArcSin, buttonArcCos, buttonArcTan,
            buttonElectronMass, buttonProtonMass, buttonNeutronMass, buttonElectronCharge, buttonPermittivity, buttonPermeability,
            buttonPlank, buttonG, buttonC, buttonDecimal;
    private View scrollView;

    public custom_keyboard1_initializer(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Inflate the custom keyboard layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View keyboardView = inflater.inflate(R.layout.custom_keyboard1, this, true);

        // Initialize buttons
        button1 = keyboardView.findViewById(R.id.button1);
        button2 = keyboardView.findViewById(R.id.button2);
        button3 = keyboardView.findViewById(R.id.button3);
        button4 = keyboardView.findViewById(R.id.button4);
        button5 = keyboardView.findViewById(R.id.button5);
        button6 = keyboardView.findViewById(R.id.button6);
        button7 = keyboardView.findViewById(R.id.button7);
        button8 = keyboardView.findViewById(R.id.button8);
        button9 = keyboardView.findViewById(R.id.button9);
        button0 = keyboardView.findViewById(R.id.button0);
        buttonPlus = keyboardView.findViewById(R.id.buttonPlus);
        buttonMinus = keyboardView.findViewById(R.id.buttonMinus);
        buttonMultiply = keyboardView.findViewById(R.id.buttonMultiply);
        buttonDivide = keyboardView.findViewById(R.id.buttonDivide);
        buttonLn = keyboardView.findViewById(R.id.buttonLn);
        buttonlog10 = keyboardView.findViewById(R.id.buttonLog10);
        buttonFactorial = keyboardView.findViewById(R.id.button_factorial);
        buttonSin = keyboardView.findViewById(R.id.button_sin);
        buttonCos = keyboardView.findViewById(R.id.button_cos);
        buttonTan = keyboardView.findViewById(R.id.button_tan);
        buttonPi = keyboardView.findViewById(R.id.button_pi);
        buttonE = keyboardView.findViewById(R.id.button_e);
        buttonLeftBracket = keyboardView.findViewById(R.id.button_left_bracket);
        buttonRightBracket = keyboardView.findViewById(R.id.button_right_bracket);
        buttonArcSin = keyboardView.findViewById(R.id.button_arcsin);
        buttonArcCos = keyboardView.findViewById(R.id.button_arccos);
        buttonArcTan = keyboardView.findViewById(R.id.button_arctan);
        buttonBackspace = keyboardView.findViewById(R.id.buttonBackspace);
        buttonExponent = keyboardView.findViewById(R.id.button_power);
        buttonModulus = keyboardView.findViewById(R.id.button_modulus);
        buttonElectronMass = keyboardView.findViewById(R.id.buttonElectronMass);
        buttonProtonMass = keyboardView.findViewById(R.id.buttonProtonMass);
        buttonNeutronMass = keyboardView.findViewById(R.id.buttonNeutronMass);
        buttonElectronCharge = keyboardView.findViewById(R.id.buttonElectronCharge);
        buttonPermittivity = keyboardView.findViewById(R.id.buttonPermittivity);
        buttonPermeability = keyboardView.findViewById(R.id.buttonPermeability);
        buttonPlank = keyboardView.findViewById(R.id.buttonPlank);
        buttonG = keyboardView.findViewById(R.id.buttonG);
        buttonC = keyboardView.findViewById(R.id.buttonLightSpeed);
        buttonDecimal = keyboardView.findViewById(R.id.buttonDecimal);

        buttonElectronMass.setText(Html.fromHtml("m<sub>e</sub>"));
        buttonProtonMass.setText(Html.fromHtml("m<sub>p</sub>"));
        buttonNeutronMass.setText(Html.fromHtml("m<sub>n</sub>"));
        buttonElectronCharge.setText(Html.fromHtml("e<sup>-</sup>"));
        buttonPermittivity.setText(Html.fromHtml("ε<sub>0</sub>"));
        buttonPermeability.setText(Html.fromHtml("μ<sub>0</sub>"));

        // Set button click listeners
        button1.setOnClickListener(v -> insertText("1"));
        button2.setOnClickListener(v -> insertText("2"));
        button3.setOnClickListener(v -> insertText("3"));
        button4.setOnClickListener(v -> insertText("4"));
        button5.setOnClickListener(v -> insertText("5"));
        button6.setOnClickListener(v -> insertText("6"));
        button7.setOnClickListener(v -> insertText("7"));
        button8.setOnClickListener(v -> insertText("8"));
        button9.setOnClickListener(v -> insertText("9"));
        button0.setOnClickListener(v -> insertText("0"));
        buttonPlus.setOnClickListener(v -> insertText("+"));
        buttonMinus.setOnClickListener(v -> insertText("-"));
        buttonMultiply.setOnClickListener(v -> insertText("*"));
        buttonDivide.setOnClickListener(v -> insertText("/"));
        buttonBackspace.setOnClickListener(v -> {
            if (targetEditText != null) {
                int cursorPosition = targetEditText.getSelectionStart();
                Editable editable = targetEditText.getText();
                if (cursorPosition > 0) {
                    editable.delete(cursorPosition - 1, cursorPosition);
                }
            }
        });

        buttonExponent.setOnClickListener(v -> insertText("^"));
        buttonLn.setOnClickListener(v -> insertText("ln("));
        buttonlog10.setOnClickListener(v -> insertText("log10("));
        buttonFactorial.setOnClickListener(v -> insertText("!"));
        buttonModulus.setOnClickListener(v -> insertText("||"));
        buttonSin.setOnClickListener(v -> insertText("sin("));
        buttonCos.setOnClickListener(v -> insertText("cos("));
        buttonTan.setOnClickListener(v -> insertText("tan("));
        buttonPi.setOnClickListener(v -> insertText("pi"));
        buttonE.setOnClickListener(v -> insertText("e"));
        buttonLeftBracket.setOnClickListener(v -> insertText("("));
        buttonRightBracket.setOnClickListener(v -> insertText(")"));
        buttonArcSin.setOnClickListener(v -> insertText("arcsin("));
        buttonArcCos.setOnClickListener(v -> insertText("arccos("));
        buttonArcTan.setOnClickListener(v -> insertText("arctan("));
        buttonDecimal.setOnClickListener(v -> insertText("."));
        //on long press of buttonDecimal, insert a comma
        buttonDecimal.setOnLongClickListener(v -> {
            insertText(", ");
            return true;
        });

        buttonElectronMass.setOnClickListener(v -> insertText("9.1093837*10^(-31)"));
        buttonProtonMass.setOnClickListener(v -> insertText("1.6726219*10^(-27)"));
        buttonNeutronMass.setOnClickListener(v -> insertText("1.674927*10^(-27)"));
        buttonElectronCharge.setOnClickListener(v -> insertText("1.60217662*10^(-19)"));
        buttonPermittivity.setOnClickListener(v -> insertText("8.854187817*10^(-12)"));
        buttonPermeability.setOnClickListener(v -> insertText("4*pi*10^(-7)"));
        buttonPlank.setOnClickListener(v -> insertText("6.62607015*10^(-34)"));
        buttonG.setOnClickListener(v -> insertText("6.6743*10^(-11)"));
        buttonC.setOnClickListener(v -> insertText("299792458"));

        setVisibility(View.GONE);
    }

    public void setTargetEditText(EditText editText) {
        targetEditText = editText;
    }

    public void setScrollView(View view) {
        scrollView = view;
    }

    public void showKeyboard() {
        //get height of keyboard
        double keyboardHeight = getHeight();

        //increase height of parent view by height of keyboard
        scrollView.setPadding(0, 0, 0, (int) keyboardHeight);
        setVisibility(View.VISIBLE);

    }

    public void hideKeyboard() {
        scrollView.setPadding(0, 0, 0, 0);
        setVisibility(View.GONE);

    }

    public String getKeyboardText() {
        return targetEditText.getText().toString();
    }

    private void insertText(CharSequence text) {
        if (targetEditText != null) {
            // Insert text at the current cursor position
            int cursorPosition = targetEditText.getSelectionStart();
            Editable editable = targetEditText.getText();
            editable.insert(cursorPosition, text);
        }
    }


}


