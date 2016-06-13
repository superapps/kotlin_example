package com.giocode.example.calculator

import com.udojava.evalex.Expression

class Calculator {
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