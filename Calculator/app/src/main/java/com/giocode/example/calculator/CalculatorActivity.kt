package com.giocode.example.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import com.udojava.evalex.Expression
import org.jetbrains.anko.*

class CalculatorActivity : AppCompatActivity() {

    val calculator = Calculator()
    var resultEdit: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        consistView();
    }

    private fun consistView() {
        verticalLayout {
            padding = dip(16)
            editText() {
                textSize = sp(15).toFloat()
                hint = "1+1/3"
                textChangedListener {
                    onTextChanged {
                        text, start, before, count ->
                        resultEdit?.text = calculator.calculate(text.toString())
                    }
                }
            }
            resultEdit = textView() {
                textSize = sp(15).toFloat()
                gravity = Gravity.RIGHT
            }
        }
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
