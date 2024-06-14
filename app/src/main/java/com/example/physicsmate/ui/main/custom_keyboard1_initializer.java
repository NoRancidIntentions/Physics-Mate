package com.example.physicsmate.ui.main;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import com.airbnb.paris.Paris;
import com.example.physicsmate.R;

public class custom_keyboard1_initializer extends GridLayout {

    private EditText targetEditText;
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9,
            button0, buttonPlus, buttonMinus, buttonMultiply, buttonDivide, buttonBackspace,
            buttonExponent, buttonLn, buttonlog10, buttonFactorial, buttonModulus, buttonSin, buttonCos,
            buttonTan, buttonPi, buttonE, buttonLeftBracket, buttonRightBracket, buttonArcSin, buttonArcCos, buttonArcTan,
            buttonPhi, buttonImaginary;
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
        buttonPhi = keyboardView.findViewById(R.id.buttonPhi);
        buttonImaginary = keyboardView.findViewById(R.id.button_i);
        buttonBackspace = keyboardView.findViewById(R.id.buttonBackspace);
        buttonExponent = keyboardView.findViewById(R.id.button_power);
        buttonModulus = keyboardView.findViewById(R.id.button_modulus);

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
        buttonPhi.setOnClickListener(v -> insertText("phi"));
        buttonImaginary.setOnClickListener(v -> insertText("i"));

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


