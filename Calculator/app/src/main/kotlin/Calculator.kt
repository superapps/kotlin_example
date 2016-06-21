package com.giocode.example.calculator

import com.udojava.evalex.Expression

class Calculator {
    var lastEval = 0.0

    fun calculate(text: String): String {
        var sumString: String = ""
        val expression = Expression(text);
        try {
            lastEval = expression.eval().toDouble()
            sumString = lastEval.toString()
        } catch (ignore: Exception) {
        }
        return sumString;
    }
}