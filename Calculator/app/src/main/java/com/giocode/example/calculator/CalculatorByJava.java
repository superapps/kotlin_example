package com.giocode.example.calculator;

import com.udojava.evalex.Expression;

public class CalculatorByJava {

    public Double lastEval;

    public String calculate(String text) {
        String sumString = "";
        Expression expression = new Expression(text);
        try {
            lastEval = expression.eval().doubleValue();
            sumString = lastEval.toString();
        } catch (Exception ignore) {
        }
        return sumString;
    }

//    public Double getLastEval() {
//        return lastEval;
//    }
//
//    public CalculatorByJava setLastEval(Double lastEval) {
//        this.lastEval = lastEval;
//        return this;
//    }

//    public CalculatorByJava setLastEval(String lastEval) {
//        this.lastEval = Double.valueOf(lastEval);
//        return this;
//    }
}
