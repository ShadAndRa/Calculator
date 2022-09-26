package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button decimalSeparator;
    private Button num0;
    private Button num1;
    private Button num2;
    private Button num3;
    private Button num4;
    private Button num5;
    private Button num6;
    private Button num7;
    private Button num8;
    private Button num9;

    private Button fieldReset;

    private Button divide;
    private Button multiply;
    private Button minus;
    private Button plus;

    private Button equal;

    private Button negativeSign;

    private TextView outputField;

    private boolean decimalSeparatorPresent = false;

    private boolean dividePressed = false;
    private boolean multiplyPressed = false;
    private boolean minusPressed = false;
    private boolean plusPressed = false;

    private double operand1;
    private double operand2;
    private double result;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        decimalSeparator = (Button) findViewById(R.id.decimal_separator);
        num0 = (Button) findViewById(R.id.zero);
        num1 = (Button) findViewById(R.id.one);
        num2 = (Button) findViewById(R.id.two);
        num3 = (Button) findViewById(R.id.three);
        num4 = (Button) findViewById(R.id.four);
        num5 = (Button) findViewById(R.id.five);
        num6 = (Button) findViewById(R.id.six);
        num7 = (Button) findViewById(R.id.seven);
        num8 = (Button) findViewById(R.id.eight);
        num9 = (Button) findViewById(R.id.nine);

        fieldReset = (Button) findViewById(R.id.clear);

        divide = (Button) findViewById(R.id.division);
        multiply = (Button) findViewById(R.id.multiplication);
        minus = (Button) findViewById(R.id.minus);
        plus = (Button) findViewById(R.id.plus);

        equal = (Button) findViewById(R.id.equal);

        negativeSign = (Button) findViewById(R.id.negative_sign);

        outputField  = (TextView) findViewById(R.id.output_field);
    }

    public void onClickNum(View view){
        if (outputField.getText().toString().equals("0"))
            switch (view.getId()){
                case R.id.decimal_separator:
                    if (!decimalSeparatorPresent){
                        outputField.append(getResources().getString(R.string.decimal_separator));
                        decimalSeparatorPresent = true;
                    }
                    break;
                case R.id.zero:
                    break;
                case R.id.one:
                    outputField.setText(getResources().getString(R.string._1));
                    break;
                case R.id.two:
                    outputField.setText(getResources().getString(R.string._2));
                    break;
                case R.id.three:
                    outputField.setText(getResources().getString(R.string._3));
                    break;
                case R.id.four:
                    outputField.setText(getResources().getString(R.string._4));
                    break;
                case R.id.five:
                    outputField.setText(getResources().getString(R.string._5));
                    break;
                case R.id.six:
                    outputField.setText(getResources().getString(R.string._6));
                    break;
                case R.id.seven:
                    outputField.setText(getResources().getString(R.string._7));
                    break;
                case R.id.eight:
                    outputField.setText(getResources().getString(R.string._8));
                    break;
                case R.id.nine:
                    outputField.setText(getResources().getString(R.string._9));
                    break;
                default:
                    break;
            }
        else
            switch (view.getId()){
                case R.id.decimal_separator:
                    if (!decimalSeparatorPresent){
                        outputField.append(getResources().getString(R.string.decimal_separator));
                        decimalSeparatorPresent = true;
                    }
                    break;
                case R.id.zero:
                    outputField.append(getResources().getString(R.string._0));
                    break;
                case R.id.one:
                    outputField.append(getResources().getString(R.string._1));
                    break;
                case R.id.two:
                    outputField.append(getResources().getString(R.string._2));
                    break;
                case R.id.three:
                    outputField.append(getResources().getString(R.string._3));
                    break;
                case R.id.four:
                    outputField.append(getResources().getString(R.string._4));
                    break;
                case R.id.five:
                    outputField.append(getResources().getString(R.string._5));
                    break;
                case R.id.six:
                    outputField.append(getResources().getString(R.string._6));
                    break;
                case R.id.seven:
                    outputField.append(getResources().getString(R.string._7));
                    break;
                case R.id.eight:
                    outputField.append(getResources().getString(R.string._8));
                    break;
                case R.id.nine:
                    outputField.append(getResources().getString(R.string._9));
                    break;
                default:
                    break;
            }
    }

    public void onClickClear(View view){
        outputField.setText(getResources().getString(R.string._0));
        operand1 = 0.0;
        operand2 = 0.0;
        result = 0.0;
        decimalSeparatorPresent = false;
        dividePressed = false;
        multiplyPressed = false;
        minusPressed = false;
        plusPressed = false;
    }

    public void onClickNegative(View view){
        if (!outputField.getText().toString().equals("0")) {
            if (!outputField.getText().toString().endsWith(getResources().getString(R.string.minus_symbol)))
                outputField.append(getResources().getString(R.string.minus_symbol));
            else
                outputField.setText(outputField.getText().toString().replace(getResources().getString(R.string.minus_symbol), ""));
        }
    }
}