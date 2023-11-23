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

    //if user presses keys 299792548, change all buttons to style button enabled for 0.5 sec
    /*public void easterEgg() {
        if (targetEditText.getText().toString().equals("299792548")) {
            Paris.style(button1).apply(R.style.custom_button_enabled);
            Paris.style(button2).apply(R.style.custom_button_enabled);
            Paris.style(button3).apply(R.style.custom_button_enabled);
            Paris.style(button4).apply(R.style.custom_button_enabled);
            Paris.style(button5).apply(R.style.custom_button_enabled);
            Paris.style(button6).apply(R.style.custom_button_enabled);
            Paris.style(button7).apply(R.style.custom_button_enabled);
            Paris.style(button8).apply(R.style.custom_button_enabled);
            Paris.style(button9).apply(R.style.custom_button_enabled);
            Paris.style(button0).apply(R.style.custom_button_enabled);
            Paris.style(buttonPlus).apply(R.style.custom_button_enabled);
            Paris.style(buttonMinus).apply(R.style.custom_button_enabled);
            Paris.style(buttonMultiply).apply(R.style.custom_button_enabled);
            Paris.style(buttonDivide).apply(R.style.custom_button_enabled);
            Paris.style(buttonBackspace).apply(R.style.custom_button_enabled);
            Paris.style(buttonExponent).apply(R.style.custom_button_enabled);
            Paris.style(buttonLn).apply(R.style.custom_button_enabled);
            Paris.style(buttonlog10).apply(R.style.custom_button_enabled);
            Paris.style(buttonFactorial).apply(R.style.custom_button_enabled);
            Paris.style(buttonModulus).apply(R.style.custom_button_enabled);
            Paris.style(buttonSin).apply(R.style.custom_button_enabled);
            Paris.style(buttonCos).apply(R.style.custom_button_enabled);
            Paris.style(buttonTan).apply(R.style.custom_button_enabled);
            Paris.style(buttonPi).apply(R.style.custom_button_enabled);
            Paris.style(buttonE).apply(R.style.custom_button_enabled);
            Paris.style(buttonLeftBracket).apply(R.style.custom_button_enabled);
            Paris.style(buttonRightBracket).apply(R.style.custom_button_enabled);
            Paris.style(buttonArcSin).apply(R.style.custom_button_enabled);
            Paris.style(buttonArcCos).apply(R.style.custom_button_enabled);
            Paris.style(buttonArcTan).apply(R.style.custom_button_enabled);
            Paris.style(buttonPhi).apply(R.style.custom_button_enabled);
            Paris.style(buttonImaginary).apply(R.style.custom_button_enabled);

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                Paris.style(button1).apply(R.style.custom_button_enabled_keyboard);
                Paris.style(button2).apply(R.style.custom_button_enabled_keyboard);
                Paris.style(button3).apply(R.style.custom_button_enabled_keyboard);
                Paris.style(button4).apply(R.style.custom_button_enabled_keyboard);
                Paris.style(button5).apply(R.style.custom_button_enabled_keyboard);
                Paris.style(button6).apply(R.style.custom_button_enabled_keyboard);
                Paris.style(button7).apply(R.style.custom_button_enabled_keyboard);
                Paris.style(button8).apply(R.style.custom_button_enabled_keyboard);
                Paris.style(button9).apply(R.style.custom_button_enabled_keyboard);
                Paris.style(button0).apply(R.style.custom_button_enabled_keyboard);
                Paris.style(buttonPlus).apply(R.style.custom_button_enabled_keyboard);
                Paris.style(buttonMinus).apply(R.style.custom_button_enabled_keyboard);
                Paris.style(buttonMultiply).apply(R.style.custom_button_enabled_keyboard);
                Paris.style(buttonDivide).apply(R.style.custom_button_enabled_keyboard);
                Paris.style(buttonBackspace).apply(R.style.custom_button_enabled_keyboard);
                Paris.style(buttonExponent).apply(R.style.custom_button_enabled_keyboard);
                Paris.style(buttonLn).apply(R.style.custom_button_enabled_keyboard);
                Paris.style(buttonlog10).apply(R.style.custom_button_enabled_keyboard);
            }, 500);
        } else {
            Paris.style(button1).apply(R.style.custom_button_enabled_keyboard);
            Paris.style(button2).apply(R.style.custom_button_enabled_keyboard);
            Paris.style(button3).apply(R.style.custom_button_enabled_keyboard);
            Paris.style(button4).apply(R.style.custom_button_enabled_keyboard);
            Paris.style(button5).apply(R.style.custom_button_enabled_keyboard);
            Paris.style(button6).apply(R.style.custom_button_enabled_keyboard);
            Paris.style(button7).apply(R.style.custom_button_enabled_keyboard);
            Paris.style(button8).apply(R.style.custom_button_enabled_keyboard);
            Paris.style(button9).apply(R.style.custom_button_enabled_keyboard);
            Paris.style(button0).apply(R.style.custom_button_enabled_keyboard);
            Paris.style(buttonPlus).apply(R.style.custom_button_enabled_keyboard);
            Paris.style(buttonMinus).apply(R.style.custom_button_enabled_keyboard);
            Paris.style(buttonMultiply).apply(R.style.custom_button_enabled_keyboard);
            Paris.style(buttonDivide).apply(R.style.custom_button_enabled_keyboard);
            Paris.style(buttonBackspace).apply(R.style.custom_button_enabled_keyboard);
            Paris.style(buttonExponent).apply(R.style.custom_button_enabled_keyboard);
            Paris.style(buttonLn).apply(R.style.custom_button_enabled_keyboard);
            Paris.style(buttonlog10).apply(R.style.custom_button_enabled_keyboard);
        }
    }*/

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


