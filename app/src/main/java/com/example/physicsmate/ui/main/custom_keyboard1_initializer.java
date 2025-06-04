package com.example.physicsmate.ui.main;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.example.physicsmate.R;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;

import static com.example.physicsmate.Custom_methods.evalf;

public class custom_keyboard1_initializer extends LinearLayout {

    final double pi = Math.PI;
    final double e = Math.E;
    final double h = 6.626070040e-34;
    final double c = 299792458;
    final double G = 6.674010551359e-11;
    final double mu_0 = 4 * pi * 1e-7;
    final double eps_0 = 8.854187817e-12;
    final double m_e = 9.10938356e-31;
    final double m_p = 1.67262158e-27;
    final double m_n = 1.674927e-27;
    final double k_B = 1.380649e-23;
    final double Na = 6.02214076e23;
    final double sigma = 5.670367e-8;
    final double gas_constant = 8.31446261815324;
    private final ViewGroup keyboardTabContent;
    private EditText targetEditText;
    private View scrollView;
    LinearLayout keyboard_layout, keyboard_tab_bar;

    public custom_keyboard1_initializer(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.custom_keyboard1, this, true);

        setVisibility(GONE);

        keyboard_layout = findViewById(R.id.keyboard_layout);
        setupOperatorBar();

        Button tab123 = findViewById(R.id.tab_numbers);
        Button tabABC = findViewById(R.id.tab_alphabets);
        Button tabGreek = findViewById(R.id.tab_greek);
        Button tabConst = findViewById(R.id.tab_constants);
        Button backspace = findViewById(R.id.backspace);

        backspace.setOnClickListener(v -> {
            int cursorPosition = targetEditText.getSelectionStart();
            if (targetEditText.getText() != null && cursorPosition > 0) {
                targetEditText.getText().delete(cursorPosition - 1, cursorPosition);
            }
        });

        backspace.setOnLongClickListener(v -> {
            int cursorPosition = targetEditText.getSelectionStart();
            if (targetEditText.getText() != null && cursorPosition > 0) {
                while (backspace.isPressed()) {
                    targetEditText.getText().delete(cursorPosition - 1, cursorPosition);
                }
            }
            return true;
        });

        View maybeScroll = findViewById(R.id.keyboard_tab_container);
        if (maybeScroll instanceof ScrollView) {
            keyboardTabContent = (ScrollView) maybeScroll;
        } else {
            keyboardTabContent = (ViewGroup) maybeScroll;
        }

        keyboard_tab_bar = findViewById(R.id.keyboard_tab_bar);

        tab123.setOnClickListener(v -> loadKeyboardLayout(R.layout.keyboard_numbers));
        tabABC.setOnClickListener(v -> loadKeyboardLayout(R.layout.keyboard_alphabets));
        tabGreek.setOnClickListener(v -> loadKeyboardLayout(R.layout.keyboard_greek));
        tabConst.setOnClickListener(v -> loadKeyboardLayoutForConstants());

        loadKeyboardLayout(R.layout.keyboard_numbers);
    }

    private void setupOperatorBar() {
        int[] operatorIds = new int[]{R.id.buttonDecimal, R.id.buttonPlus, R.id.buttonMinus, R.id.buttonMultiply,
                R.id.buttonDivide, R.id.button_power};

        for (int id : operatorIds) {
            Button btn = findViewById(id);
            if (btn == null) continue;
            String text = btn.getText().toString();
            btn.setOnClickListener(v -> insertText(text));
        }

        int[] specOperatorIds = new int[]{R.id.button_modulus, R.id.button_factorial,
                R.id.buttonLn, R.id.buttonLog10, R.id.button_sin, R.id.button_cos, R.id.button_tan,
                R.id.button_arcsin, R.id.button_arccos, R.id.button_arctan};

        for (int id : specOperatorIds) {
            Button btn = findViewById(id);
            if (btn == null) continue;
            CharSequence text = btn.getText();
            if (text != null) {
                btn.setOnClickListener(v -> insertText(text + "("));
            }
        }

        Button decimalBtn = findViewById(R.id.buttonDecimal);
        decimalBtn.setText(Html.fromHtml("<sup>.</sup> ,", Html.FROM_HTML_MODE_LEGACY));
        decimalBtn.setOnLongClickListener(v -> {
            insertText(", ");
            return true;
        });

        Button addition = findViewById(R.id.buttonPlus);
        addition.setText(Html.fromHtml("+ <sub>(</sub>", Html.FROM_HTML_MODE_LEGACY));
        addition.setOnLongClickListener(v -> {
            insertText(" + ");
            return true;
        });

        Button subtraction = findViewById(R.id.buttonMinus);
        subtraction.setText(Html.fromHtml("- <sub>)</sub>", Html.FROM_HTML_MODE_LEGACY));
        subtraction.setOnLongClickListener(v -> {
            insertText(" - ");
            return true;
        });
    }

    private void loadKeyboardLayout(int layoutResId) {
        keyboardTabContent.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        FlexboxLayout flexContainer = (FlexboxLayout) inflater.inflate(layoutResId, keyboardTabContent, false);

        if (keyboardTabContent instanceof ScrollView) {
            flexContainer.setFlexWrap(FlexWrap.WRAP);
            flexContainer.setFlexDirection(FlexDirection.ROW);
            flexContainer.setLayoutParams(new LayoutParams(
                    LayoutParams.MATCH_PARENT,        // fill horizontally
                    LayoutParams.WRAP_CONTENT));      // height wrap_content
            // Add to the ScrollView
            keyboardTabContent.addView(flexContainer);
            keyboardTabContent.setVisibility(VISIBLE);
        }

        for (int i = 0; i < flexContainer.getChildCount(); i++) {
            View v = flexContainer.getChildAt(i);
            if (v instanceof Button) {
                String text = ((Button) v).getText().toString();
                v.setOnClickListener(btn -> insertText(text));
            }
        }
    }

    private void loadKeyboardLayoutForConstants() {
        keyboardTabContent.removeAllViews();
        LayoutInflater inflater1 = LayoutInflater.from(getContext());
        FlexboxLayout layout = (FlexboxLayout) inflater1.inflate(R.layout.keyboard_constants, keyboardTabContent, false);
        keyboardTabContent.addView(layout);

        layout.getChildAt(0).setOnClickListener(v1 -> insertText(e));
        layout.getChildAt(1).setOnClickListener(v1 -> insertText(pi));
        layout.getChildAt(2).setOnClickListener(v1 -> insertText(h));
        layout.getChildAt(3).setOnClickListener(v1 -> insertText(c));
        layout.getChildAt(4).setOnClickListener(v1 -> insertText(G));
        layout.getChildAt(5).setOnClickListener(v1 -> insertText(mu_0));
        layout.getChildAt(6).setOnClickListener(v1 -> insertText(eps_0));
        layout.getChildAt(7).setOnClickListener(v1 -> insertText(m_e));
        layout.getChildAt(8).setOnClickListener(v1 -> insertText(m_p));
        layout.getChildAt(9).setOnClickListener(v1 -> insertText(m_n));
        layout.getChildAt(10).setOnClickListener(v1 -> insertText(k_B));
        layout.getChildAt(11).setOnClickListener(v1 -> insertText(Na));
        layout.getChildAt(12).setOnClickListener(v1 -> insertText(sigma));
        layout.getChildAt(13).setOnClickListener(v1 -> insertText(gas_constant));
    }

    public void setTargetEditText(EditText editText) {
        targetEditText = editText;
    }

    public void setScrollView(View view) {
        scrollView = view;
    }

    public void showKeyboard() {
        setVisibility(View.VISIBLE);
        if (scrollView != null) {
            post(() -> {
                scrollView.setPadding(0, 0, 0, getHeight());
                System.out.println("\n\n\ndone: " + getHeight() + "\n\n\n");
            });
        }
    }

    public void hideKeyboard() {
        if (scrollView != null) {
            scrollView.setPadding(0, 0, 0, 0);
        }
        setVisibility(View.GONE);
    }

    public String getKeyboardText() {
        return targetEditText != null ? targetEditText.getText().toString() : "";
    }

    private void insertText(CharSequence text) {
        if (targetEditText != null) {
            int cursorPosition = targetEditText.getSelectionStart();
            Editable editable = targetEditText.getText();
            editable.insert(cursorPosition, text);
        }
    }

    private void insertText(double num) {
        if (targetEditText != null) {
            int cursorPosition = targetEditText.getSelectionStart();
            Editable editable = targetEditText.getText();
            String text = String.valueOf(evalf(String.valueOf(num)));
            editable.insert(cursorPosition, text);
        }
    }

    private void insertTextInt(double num) {
        if (targetEditText != null) {
            int cursorPosition = targetEditText.getSelectionStart();
            Editable editable = targetEditText.getText();
            String text = String.valueOf((int) evalf(String.valueOf(num)));
            editable.insert(cursorPosition, text);
        }
    }
}
