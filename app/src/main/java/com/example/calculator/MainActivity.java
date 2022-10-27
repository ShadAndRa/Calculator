package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView outputField;
    private int[] numbers;
    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputField  = findViewById(R.id.output_field);
        numbers = new int[] {
                R.id.zero,
                R.id.one,
                R.id.two,
                R.id.three,
                R.id.four,
                R.id.five,
                R.id.six,
                R.id.seven,
                R.id.eight,
                R.id.nine
        };
        calculator = new Calculator(getResources(), outputField,numbers);
    }

    public void onClickNum(View view) {
        calculator.numInput(view);
    }
    public void onClickPoint(View view) {
        calculator.decimalSeparator();
    }
    public void onClickClear(View view) {
        calculator.resetAll();
    }
    public void onClickNegative(View view) {
        calculator.negativeSign();
    }

    public void onClickEqual(View view) {
        calculator.equalBtn();
    }
    public void onClickPlus(View view) {
        calculator.plusBtn();
    }
    public void onClickMinus(View view) {
        calculator.minusBtn();
    }
    public void onClickMultiplication(View view) {
        calculator.multiplicationBtn();
    }
    public void onClickDivision(View view) {
        calculator.divisionBtn();
    }
}