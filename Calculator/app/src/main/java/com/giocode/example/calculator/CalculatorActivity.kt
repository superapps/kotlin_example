package com.giocode.example.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import com.udojava.evalex.Expression

class CalculatorActivity : AppCompatActivity() {

    val calculator = Calculator()
    val resultEdit: TextView by lazy {
        findViewById(R.id.calculator_result) as TextView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        val edit = findViewById(R.id.calculator_edit) as EditText
        edit.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                resultEdit.text = calculator.calculate(text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    inner class Calculator {
        fun calculate(text: String): String {
            var sumString: String = ""
            val expression = Expression(text);
            try {
                sumString = expression.eval().toDouble().toString()
            } catch (ignore: Exception) {
            }
            return sumString;
        }
    }
}
