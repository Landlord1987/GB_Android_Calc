package ru.gb.gb_android_calc.model;

public class CalculatorImpl implements Calculator {

    @Override
    public double perform(double arg1, double arg2, Operator operator) {
        switch (operator) {
            case ADD:
                return arg1 + arg2;
            case SUB:
                return arg1 - arg2;
            case MULT:
                return arg1 * arg2;
            case DIV:
                return arg1 / arg2;
            case AC:
                return 0;
        }
        return 0.0;
    }

    @Override
    public double performAC() {
        return 0;
    }
}
