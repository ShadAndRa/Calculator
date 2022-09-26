package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

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

    private boolean dividePressed = false;
    private boolean multiplyPressed = false;
    private boolean minusPressed = false;
    private boolean plusPressed = false;

    private double result = 0;
    private double last_operand = 0;

    final private DecimalFormat format = new DecimalFormat("0.########");

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
                    if (!outputField.getText().toString().contains(getString(R.string.decimal_separator))){
                        outputField.append(getString(R.string.decimal_separator));
                    }
                    break;
                case R.id.zero:
                    break;
                case R.id.one:
                    outputField.setText(getString(R.string._1));
                    break;
                case R.id.two:
                    outputField.setText(getString(R.string._2));
                    break;
                case R.id.three:
                    outputField.setText(getString(R.string._3));
                    break;
                case R.id.four:
                    outputField.setText(getString(R.string._4));
                    break;
                case R.id.five:
                    outputField.setText(getString(R.string._5));
                    break;
                case R.id.six:
                    outputField.setText(getString(R.string._6));
                    break;
                case R.id.seven:
                    outputField.setText(getString(R.string._7));
                    break;
                case R.id.eight:
                    outputField.setText(getString(R.string._8));
                    break;
                case R.id.nine:
                    outputField.setText(getString(R.string._9));
                    break;
                default:
                    break;
            }
        else
            switch (view.getId()){
                case R.id.decimal_separator:
                    if (!outputField.getText().toString().contains(getString(R.string.decimal_separator))) {
                        outputField.append(getString(R.string.decimal_separator));
                    }
                    else {
                        outputField.setText(outputField.getText().toString().replace(getString(R.string.decimal_separator), ""));
                    }
                    break;
                case R.id.zero:
                    outputField.append(getString(R.string._0));
                    break;
                case R.id.one:
                    outputField.append(getString(R.string._1));
                    break;
                case R.id.two:
                    outputField.append(getString(R.string._2));
                    break;
                case R.id.three:
                    outputField.append(getString(R.string._3));
                    break;
                case R.id.four:
                    outputField.append(getString(R.string._4));
                    break;
                case R.id.five:
                    outputField.append(getString(R.string._5));
                    break;
                case R.id.six:
                    outputField.append(getString(R.string._6));
                    break;
                case R.id.seven:
                    outputField.append(getString(R.string._7));
                    break;
                case R.id.eight:
                    outputField.append(getString(R.string._8));
                    break;
                case R.id.nine:
                    outputField.append(getString(R.string._9));
                    break;
                default:
                    break;
            }
    }

    public void onClickClear(View view){
        outputField.setText(getString(R.string._0));
        dividePressed = false;
        multiplyPressed = false;
        minusPressed = false;
        plusPressed = false;
    }

    public void onClickNegative(View view){
        if (!outputField.getText().toString().equals("0")) {
            if (!outputField.getText().toString().startsWith(getString(R.string.minus_symbol)))
                outputField.setText(getString(R.string.minus_symbol) + outputField.getText().toString());
            else
                outputField.setText(outputField.getText().toString().replace(getString(R.string.minus_symbol), ""));
        }
    }

    public void onClickOperand(View view){
        switch (view.getId()){
            case R.id.equal:
                if(plusPressed)
                    onClickOperand(plus);
                if(minusPressed)
                    onClickOperand(minus);
                if(multiplyPressed)
                    onClickOperand(multiply);
                if(dividePressed)
                    onClickOperand(divide);

                outputField.setText(String.valueOf(format.format(result)));
                break;
            case R.id.plus:
                if(minusPressed) {
                    onClickOperand(minus);
                    onClickClear(fieldReset);
                    plusPressed = true;
                    break;
                }
                if(multiplyPressed) {
                    onClickOperand(multiply);
                    onClickClear(fieldReset);
                    plusPressed = true;
                    break;
                }
                if(dividePressed) {
                    onClickOperand(divide);
                    onClickClear(fieldReset);
                    plusPressed = true;
                    break;
                }

                if(plusPressed){
                    result = result + Double.parseDouble(outputField.getText().toString());
                    onClickClear(fieldReset);
                    plusPressed = true;
                    break;
                }
                result = Double.parseDouble(outputField.getText().toString());
                onClickClear(fieldReset);
                plusPressed = true;
                break;
            case R.id.minus:
                if(plusPressed) {
                    onClickOperand(plus);
                    onClickClear(fieldReset);
                    minusPressed = true;
                    break;
                }
                if(multiplyPressed) {
                    onClickOperand(multiply);
                    onClickClear(fieldReset);
                    minusPressed = true;
                    break;
                }
                if(dividePressed) {
                    onClickOperand(divide);
                    onClickClear(fieldReset);
                    minusPressed = true;
                    break;
                }

                if(minusPressed){
                    result = result - Double.parseDouble(outputField.getText().toString());
                    onClickClear(fieldReset);
                    minusPressed = true;
                    break;
                }
                result = Double.parseDouble(outputField.getText().toString());
                onClickClear(fieldReset);
                minusPressed = true;
                break;
            case R.id.multiplication:
                if(plusPressed) {
                    onClickOperand(plus);
                    onClickClear(fieldReset);
                    multiplyPressed = true;
                    break;
                }
                if(minusPressed) {
                    onClickOperand(minus);
                    onClickClear(fieldReset);
                    multiplyPressed = true;
                    break;
                }
                if(dividePressed) {
                    onClickOperand(divide);
                    onClickClear(fieldReset);
                    multiplyPressed = true;
                    break;
                }

                if(multiplyPressed){
                    result = result * Double.parseDouble(outputField.getText().toString());
                    onClickClear(fieldReset);
                    multiplyPressed = true;
                    break;
                }
                result = Double.parseDouble(outputField.getText().toString());
                onClickClear(fieldReset);
                multiplyPressed = true;
                break;
            case R.id.division:
                if(plusPressed) {
                    onClickOperand(plus);
                    onClickClear(fieldReset);
                    dividePressed = true;
                    break;
                }
                if(minusPressed) {
                    onClickOperand(minus);
                    onClickClear(fieldReset);
                    dividePressed = true;
                    break;
                }
                if(multiplyPressed) {
                    onClickOperand(multiply);
                    onClickClear(fieldReset);
                    dividePressed = true;
                    break;
                }

                if(dividePressed){
                    if(Double.parseDouble(outputField.getText().toString()) != 0) {
                        result = result / Double.parseDouble(outputField.getText().toString());
                        onClickClear(fieldReset);
                        dividePressed = true;
                    }
                    else {
                        Toast.makeText(this, R.string.division_by_zero, Toast.LENGTH_LONG).show();
                        result = 0;
                        onClickClear(fieldReset);
                    }
                    break;
                }
                result = Double.parseDouble(outputField.getText().toString());
                onClickClear(fieldReset);
                dividePressed = true;
                break;
        }
    }
}