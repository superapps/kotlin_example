package com.giocode.example.calculator

import com.udojava.evalex.Expression

object Calculator {
    var result: Result = Result()

    fun calculate(text: String): String = this innerCalculate text

    private infix fun innerCalculate(text: String): String {
        var sumString: String = ""
        val expression = Expression(text);
        try {
            val result = expression.eval().toDouble()
            sumString = result.toString()
            this.result = Result(result, text)
        } catch (ignore: Exception) {
        }
        return sumString;
    }
}