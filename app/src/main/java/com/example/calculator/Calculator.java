package com.example.calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Calculator {
    private final Resources resources;
    private final TextView outputField;
    private final Toast divisionByZero;
    private final int[] numbers;
    private final DecimalFormat format;
    private final int maxNumLen;

    private enum clickCounter {
        NOT_USED,
        USED,
    }
    private clickCounter equalCounter;
    private clickCounter plusCounter;
    private clickCounter minusCounter;
    private clickCounter multiplyCounter;
    private clickCounter divisionCounter;


    private double firstOperand;
    private double secondOperand;
    private double result;

    Calculator(Context context, TextView outputField, int[] numbers) {
        this.resources = context.getResources();
        this.divisionByZero =  Toast.makeText(context, this.resources.getString(R.string.devision_by_zero),Toast.LENGTH_SHORT);
        this.outputField = outputField;
        this.numbers = numbers;
        this.format = new DecimalFormat("0.########");
        this.maxNumLen = 11;

        this.equalCounter = clickCounter.NOT_USED;
        this.plusCounter = clickCounter.NOT_USED;
        this.minusCounter = clickCounter.NOT_USED;
        this.multiplyCounter = clickCounter.NOT_USED;
        this.divisionCounter = clickCounter.NOT_USED;
    }
    public void clearField(){
        outputField.setText(resources.getString(R.string._0));
    }
    public void resetCounters() {
        equalCounter = clickCounter.NOT_USED;
        plusCounter = clickCounter.NOT_USED;
        minusCounter = clickCounter.NOT_USED;
        multiplyCounter = clickCounter.NOT_USED;
        divisionCounter = clickCounter.NOT_USED;
    }

    public void numInput(View view) {
            int maxLen = maxNumLen;
            if (outputField.getText().toString().contains(resources.getString(R.string.minus_symbol)))
                maxLen++;
            if (outputField.getText().toString().contains(resources.getString(R.string.decimal_separator)))
                maxLen++;
            if (outputField.getText().toString().length() < maxLen ){
                if (outputField.getText().toString().equals(resources.getString(R.string._0)))
                    outputField.setText("");
                for (int i:numbers){
                    if (i == view.getId()){
                        outputField.append(((Button) view).getText());
                    }
                }
            }
        }
    public void decimalSeparator() {
        int maxLen = maxNumLen;
        if (outputField.getText().toString().contains(resources.getString(R.string.minus_symbol)))
            maxLen++;
        if (outputField.getText().toString().length() < maxLen ){
            if (!outputField.getText().toString().contains(resources.getString(R.string.decimal_separator))) {
                outputField.append(resources.getString(R.string.decimal_separator));
            }
            else {
                outputField.setText(outputField.getText().toString().replace(resources.getString(R.string.decimal_separator), ""));
            }
        }
    }
    public void resetAll() {
        clearField();
        resetCounters();
    }
    @SuppressLint("SetTextI18n")
    public void negativeSign() {
        if (!outputField.getText().toString().equals("0")) {
            if (!outputField.getText().toString().startsWith(resources.getString(R.string.minus_symbol)))
                outputField.setText(resources.getString(R.string.minus_symbol) + outputField.getText().toString());
            else
                outputField.setText(outputField.getText().toString().replace(resources.getString(R.string.minus_symbol), ""));
        }
    }

    public void equalBtn() {
        switch (equalCounter) {
            case NOT_USED:
                if (plusCounter.equals(clickCounter.USED)) {
                    equalCounter = clickCounter.USED;
                    secondOperand = Double.parseDouble(outputField.getText().toString());
                    result = firstOperand + secondOperand;
                    outputField.setText(format.format(result).replace(',', '.'));
                }
                if (minusCounter.equals(clickCounter.USED)) {
                    equalCounter = clickCounter.USED;
                    secondOperand = Double.parseDouble(outputField.getText().toString());
                    result = firstOperand - secondOperand;
                    outputField.setText(format.format(result).replace(',', '.'));
                }
                if (multiplyCounter.equals(clickCounter.USED)) {
                    equalCounter = clickCounter.USED;
                    secondOperand = Double.parseDouble(outputField.getText().toString());
                    result = firstOperand * secondOperand;
                    outputField.setText(format.format(result).replace(',', '.'));
                }
                if (divisionCounter.equals(clickCounter.USED)) {
                    if(Double.parseDouble(outputField.getText().toString()) == 0.0){
                        divisionByZero.show();
                        resetAll();
                        return;
                    }
                    equalCounter = clickCounter.USED;
                    secondOperand = Double.parseDouble(outputField.getText().toString());
                    result = firstOperand / secondOperand;
                    outputField.setText(format.format(result).replace(',', '.'));
                }
                break;
            case USED:
                if (plusCounter.equals(clickCounter.USED)) {
                    firstOperand = result;
                    result = firstOperand + secondOperand;
                    outputField.setText(format.format(result).replace(',', '.'));
                }
                if (minusCounter.equals(clickCounter.USED)) {
                    firstOperand = result;
                    result = firstOperand - secondOperand;
                    outputField.setText(format.format(result).replace(',', '.'));
                }
                if (multiplyCounter.equals(clickCounter.USED)) {
                    firstOperand = result;
                    result = firstOperand * secondOperand;
                    outputField.setText(format.format(result).replace(',', '.'));
                }
                if (divisionCounter.equals(clickCounter.USED)) {
                    firstOperand = result;
                    result = firstOperand / secondOperand;
                    outputField.setText(format.format(result).replace(',', '.'));
                }
                break;
        }
    }
    public void plusBtn() {
        if (equalCounter.equals(clickCounter.USED)) {
            resetCounters();
        }
        if (minusCounter.equals(clickCounter.USED)) {
            secondOperand = Double.parseDouble(outputField.getText().toString());
            result = firstOperand - secondOperand;
            firstOperand = result;
            resetAll();
            plusCounter = clickCounter.USED;
        }
        else if (multiplyCounter.equals(clickCounter.USED)) {
            secondOperand = Double.parseDouble(outputField.getText().toString());
            result = firstOperand * secondOperand;
            firstOperand = result;
            resetAll();
            plusCounter = clickCounter.USED;
        }
        else if (divisionCounter.equals(clickCounter.USED)) {
            if(Double.parseDouble(outputField.getText().toString()) == 0.0){
                divisionByZero.show();
                resetAll();
                return;
            }
            secondOperand = Double.parseDouble(outputField.getText().toString());
            result = firstOperand / secondOperand;
            firstOperand = result;
            resetAll();
            plusCounter = clickCounter.USED;
        }

        else
            addition(plusCounter.ordinal());
    }
    public void minusBtn() {
        if (equalCounter.equals(clickCounter.USED))
            resetCounters();
        if (plusCounter.equals(clickCounter.USED)) {
            secondOperand = Double.parseDouble(outputField.getText().toString());
            result = firstOperand + secondOperand;
            firstOperand = result;
            resetAll();
            minusCounter = clickCounter.USED;
        }
        else if (multiplyCounter.equals(clickCounter.USED)) {
            secondOperand = Double.parseDouble(outputField.getText().toString());
            result = firstOperand * secondOperand;
            firstOperand = result;
            resetAll();
            minusCounter = clickCounter.USED;
        }
        else if (divisionCounter.equals(clickCounter.USED)) {
            if(Double.parseDouble(outputField.getText().toString()) == 0.0){
                divisionByZero.show();
                resetAll();
                return;
            }
            secondOperand = Double.parseDouble(outputField.getText().toString());
            result = firstOperand / secondOperand;
            firstOperand = result;
            resetAll();
            minusCounter = clickCounter.USED;
        }
        else
            subtraction(minusCounter.ordinal());
    }
    public void multiplicationBtn() {
        if (equalCounter.equals(clickCounter.USED))
            resetCounters();
        if (plusCounter.equals(clickCounter.USED)) {
            secondOperand = Double.parseDouble(outputField.getText().toString());
            result = firstOperand + secondOperand;
            firstOperand = result;
            resetAll();
            multiplyCounter = clickCounter.USED;
        }
        else if (minusCounter.equals(clickCounter.USED)) {
            secondOperand = Double.parseDouble(outputField.getText().toString());
            result = firstOperand - secondOperand;
            firstOperand = result;
            resetAll();
            multiplyCounter = clickCounter.USED;
        }
        else if (divisionCounter.equals(clickCounter.USED)) {
            if(Double.parseDouble(outputField.getText().toString()) == 0.0){
                divisionByZero.show();
                resetAll();
                return;
            }
            secondOperand = Double.parseDouble(outputField.getText().toString());
            result = firstOperand / secondOperand;
            firstOperand = result;
            resetAll();
            multiplyCounter = clickCounter.USED;
        }
        else
            multiplication(multiplyCounter.ordinal());
    }
    public void divisionBtn() {
        if (equalCounter.equals(clickCounter.USED))
            resetCounters();
        if (plusCounter.equals(clickCounter.USED)) {
            secondOperand = Double.parseDouble(outputField.getText().toString());
            result = firstOperand + secondOperand;
            firstOperand = result;
            resetAll();
            divisionCounter = clickCounter.USED;
        }
        else if (minusCounter.equals(clickCounter.USED)) {
            secondOperand = Double.parseDouble(outputField.getText().toString());
            result = firstOperand - secondOperand;
            firstOperand = result;
            resetAll();
            divisionCounter = clickCounter.USED;
        }
        else if (multiplyCounter.equals(clickCounter.USED)) {
            secondOperand = Double.parseDouble(outputField.getText().toString());
            result = firstOperand * secondOperand;
            firstOperand = result;
            resetAll();
            divisionCounter = clickCounter.USED;
        }
        else
            division(divisionCounter.ordinal());
    }

    private void addition(int condition) {
        if (condition == 0) {
            plusCounter = clickCounter.USED;
            firstOperand = Double.parseDouble(outputField.getText().toString());
            clearField();
        }
        if (condition == 1) {
            secondOperand = Double.parseDouble(outputField.getText().toString());
            result = firstOperand + secondOperand;
            firstOperand = result;
            clearField();
        }
    }
    private void subtraction(int condition) {
        if (condition == 0) {
            minusCounter = clickCounter.USED;
            firstOperand = Double.parseDouble(outputField.getText().toString());
            clearField();
        }
        if (condition == 1) {
            secondOperand = Double.parseDouble(outputField.getText().toString());
            result = firstOperand - secondOperand;
            firstOperand = result;
            clearField();
        }
    }
    private void multiplication(int condition) {
        if (condition == 0) {
            multiplyCounter = clickCounter.USED;
            firstOperand = Double.parseDouble(outputField.getText().toString());
            clearField();
        }
        if (condition == 1) {
            secondOperand = Double.parseDouble(outputField.getText().toString());
            result = firstOperand * secondOperand;
            firstOperand = result;
            clearField();
        }
    }
    private void division(int condition) {
        if (condition == 0) {
            divisionCounter = clickCounter.USED;
            firstOperand = Double.parseDouble(outputField.getText().toString());
            clearField();
        }
        if (condition == 1) {
            if(Double.parseDouble(outputField.getText().toString()) == 0.0){
                divisionByZero.show();
                resetAll();
                return;
            }
            secondOperand = Double.parseDouble(outputField.getText().toString());
            result = firstOperand / secondOperand;
            firstOperand = result;
            clearField();
        }
    }
}