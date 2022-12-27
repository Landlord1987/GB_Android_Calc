package ru.gb.gb_android_calc.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import ru.gb.gb_android_calc.R;
import ru.gb.gb_android_calc.model.CalculatorImpl;
import ru.gb.gb_android_calc.model.Operator;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView{
    private static final String KEY_PRESENTER = "presenter";

    private DecimalFormat formater = new DecimalFormat("#.##");

    private TextView res;

    private CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        res = findViewById(R.id.tv);

        if (savedInstanceState != null) {
            presenter = savedInstanceState.getParcelable(KEY_PRESENTER);
            showFormatted(presenter.getArgOne());
        } else {
            presenter = new CalculatorPresenter(this, new CalculatorImpl());
        }

        Map<Integer,Integer> digits = new HashMap<>();

        digits.put(R.id.btn0, 0);
        digits.put(R.id.btn1, 1);
        digits.put(R.id.btn2, 2);
        digits.put(R.id.btn3, 3);
        digits.put(R.id.btn4, 4);
        digits.put(R.id.btn5, 5);
        digits.put(R.id.btn6, 6);
        digits.put(R.id.btn7, 7);
        digits.put(R.id.btn8, 8);
        digits.put(R.id.btn9, 9);

        View.OnClickListener digitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    presenter.onDigitPressed(digits.get(v.getId()));
            }
        };

        findViewById(R.id.btn0).setOnClickListener(digitClickListener);
        findViewById(R.id.btn1).setOnClickListener(digitClickListener);
        findViewById(R.id.btn2).setOnClickListener(digitClickListener);
        findViewById(R.id.btn3).setOnClickListener(digitClickListener);
        findViewById(R.id.btn4).setOnClickListener(digitClickListener);
        findViewById(R.id.btn5).setOnClickListener(digitClickListener);
        findViewById(R.id.btn6).setOnClickListener(digitClickListener);
        findViewById(R.id.btn7).setOnClickListener(digitClickListener);
        findViewById(R.id.btn8).setOnClickListener(digitClickListener);
        findViewById(R.id.btn9).setOnClickListener(digitClickListener);

        Map<Integer, Operator> operators = new HashMap<>();

        operators.put(R.id.btnPlus, Operator.ADD);
        operators.put(R.id.btnMinus, Operator.SUB);
        operators.put(R.id.btnMult, Operator.MULT);
        operators.put(R.id.btnDivide, Operator.DIV);

        View.OnClickListener operatorsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onOperatorsPressed(operators.get(v.getId()));
            }
        };

        findViewById(R.id.btnPlus).setOnClickListener(operatorsClickListener);
        findViewById(R.id.btnMinus).setOnClickListener(operatorsClickListener);
        findViewById(R.id.btnMult).setOnClickListener(operatorsClickListener);
        findViewById(R.id.btnDivide).setOnClickListener(operatorsClickListener);

        findViewById(R.id.btnDot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onDotPressed();
            }
        });

        findViewById(R.id.btnAC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onACPressed();
            }
        });

    }

    @Override
    public void showView(String str) {
        res.setText(str);
    }

    private void setTextRes (TextView res, double argOne) {
        res.setText(String.format(Locale.getDefault(), "%f", argOne));
    }
    private void showFormatted (double value) {
        showView(formater.format(value));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(KEY_PRESENTER, presenter);
        super.onSaveInstanceState(outState);
    }
}