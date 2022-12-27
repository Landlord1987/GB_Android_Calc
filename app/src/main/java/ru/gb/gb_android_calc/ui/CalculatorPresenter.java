package ru.gb.gb_android_calc.ui;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;

import ru.gb.gb_android_calc.model.Calculator;
import ru.gb.gb_android_calc.model.CalculatorImpl;
import ru.gb.gb_android_calc.model.Operator;

public class CalculatorPresenter implements Parcelable {
    private DecimalFormat formater = new DecimalFormat("#.##");

    private CalculatorView view;
    private Calculator calculator;

    private double argOne;
    private Double argTwo;

    public CalculatorPresenter() {
    }

    public double getArgOne() {
        return argOne;
    }

    public Double getArgTwo() {
        return argTwo;
    }

    private Operator selectedOperator;

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    protected CalculatorPresenter(Parcel in) {
        argOne = in.readDouble();
        if (in.readByte() == 0) {
            argTwo = null;
        } else {
            argTwo = in.readDouble();
        }
    }

    public static final Creator<CalculatorPresenter> CREATOR = new Creator<CalculatorPresenter>() {
        @Override
        public CalculatorPresenter createFromParcel(Parcel in) {
            return new CalculatorPresenter(in);
        }

        @Override
        public CalculatorPresenter[] newArray(int size) {
            return new CalculatorPresenter[size];
        }
    };

    public void onDigitPressed(int digit) {

        if (argTwo == null) {
            argOne = argOne * 10 + digit;
            showFormatted(argOne);
        } else {
            argTwo = argTwo * 10 + digit;
            showFormatted(argTwo);
        }
    }

    public void onOperatorsPressed(Operator operator) {
        if (selectedOperator != null) {
            argOne = calculator.perform(argOne, argTwo, selectedOperator);
            showFormatted(argOne);
        }
        argTwo = 0.0;

        selectedOperator = operator;
    }

    public void onDotPressed() {
    }

    private void showFormatted(double value) {
        view.showView(formater.format(value));
    }

    public void onACPressed() {
        argOne = calculator.performAC();
        showFormatted(argOne);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(argOne);
        if (argTwo == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(argTwo);
        }
    }
}
