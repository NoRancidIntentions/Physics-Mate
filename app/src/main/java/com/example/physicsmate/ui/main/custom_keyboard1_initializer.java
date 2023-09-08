package com.example.physicsmate.ui.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import com.example.physicsmate.R;

public class custom_keyboard1_initializer extends GridLayout {

    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9,
            button0, buttonPlus, buttonMinus, buttonMultiply, buttonDivide, buttonBackspace,
            buttonExponent, buttonLn, buttonlog10, buttonFactorial, buttonModulus, buttonSin, buttonCos,
            buttonTan, buttonPi, buttonE, buttonLeftBracket, buttonRightBracket, buttonArcSin, buttonArcCos, buttonArcTan,
            buttonSqrt, buttonImaginary;

    public custom_keyboard1_initializer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void initialize(Context context) {
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
        buttonModulus = keyboardView.findViewById(R.id.button_modulus);
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
        buttonSqrt = keyboardView.findViewById(R.id.buttonsqrt);
        buttonImaginary = keyboardView.findViewById(R.id.button_i);
        buttonBackspace = keyboardView.findViewById(R.id.buttonBackspace);
        buttonExponent = keyboardView.findViewById(R.id.button_power);
        buttonModulus = keyboardView.findViewById(R.id.button_modulus);


    }

}
