package ru.gb.gb_android_calc.model;

public interface Calculator {
    double perform (double arg1, double arg2, Operator operator);
    double performAC ();
}