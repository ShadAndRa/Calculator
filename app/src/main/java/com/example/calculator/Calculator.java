package com.example.calculator;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Calculator {
    private final Resources resources;
    private final TextView outputField;
    private final int[] numbers;
    private final DecimalFormat format;
    private final int maxNumLen;

    Calculator(Resources resources, TextView outputField, int[] numbers) {
        this.resources = resources;
        this.outputField = outputField;
        this.numbers = numbers;
        this.format = new DecimalFormat("0.########");
        this.maxNumLen = 11;
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
        public void clear() {
            outputField.setText(resources.getString(R.string._0));
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
}