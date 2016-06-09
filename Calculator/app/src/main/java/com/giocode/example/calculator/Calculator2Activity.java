package com.giocode.example.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class Calculator2Activity extends AppCompatActivity {

    private TextView resultText;
    private Calculator calculator = new Calculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        resultText = (TextView) findViewById(R.id.calculator_result);

        ((EditText)findViewById(R.id.calculator_edit)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                resultText.setText(calculator.calculate(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
