package com.giocode.example.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView

class CalculatorActivity : AppCompatActivity() {

    val calculator = CalculatorByJava()
    val resultText: TextView by lazy {
        findViewById(R.id.calculator_result) as TextView
    }
    val edit: EditText by lazy {
        findViewById(R.id.calculator_edit) as EditText
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        title = "Calculator by Kotlin"

        edit.filters = arrayOf(InputFilter {
            source, start, end, dest, dstart, dend ->
            var s = source.subSequence(start, end).toString()
            if (s.contains("=")) {
                                val temp = calculator.lastEval
                                calculator.lastEval = temp
                return@InputFilter calculator.lastEval.toString()
            }
            return@InputFilter source
        })

        edit.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                resultText.text = calculator.calculate(text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}