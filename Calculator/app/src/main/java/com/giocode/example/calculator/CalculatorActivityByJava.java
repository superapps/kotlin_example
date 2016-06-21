package com.giocode.example.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class CalculatorActivityByJava extends AppCompatActivity {

    private TextView resultText;
    private Calculator calculator = new Calculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        setTitle("Calculator by Java");
        resultText = (TextView) findViewById(R.id.calculator_result);

        final EditText editText = ((EditText) findViewById(R.id.calculator_edit));
        editText.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String s = new String(source.subSequence(start, end).toString());
                if (s.contains("=")) {
                    editText.setText(Double.toString(calculator.getLastEval()));
                    return s.replace("=", "");
                }
                return source;
            }
        }});

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                resultText.setText(calculator.calculate(s.toString()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

}
