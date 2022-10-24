package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView outputField;

    final private DecimalFormat format = new DecimalFormat("0.########");

    int[] numbers;
    int a = R.id.zero;
    final int maxNumLen = 10;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputField  = (TextView) findViewById(R.id.output_field);

        numbers = new int[]{
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
    }

    public void onClickNum(View view){
        int maxLen = maxNumLen;
        if (outputField.getText().toString().contains(getString(R.string.minus_symbol)))
            maxLen++;
        if (outputField.getText().toString().contains(getString(R.string.decimal_separator)))
            maxLen++;
        if (outputField.getText().toString().length() < maxLen ){
            if (outputField.getText().toString().equals("0"))
                outputField.setText("");
            for (int i:numbers){
                if (i == view.getId()){
                    outputField.append(((Button) view).getText());
                }
            }
        }
    }

    public void onClickPoint(View view){
        int maxLen = maxNumLen;
        if (outputField.getText().toString().contains(getString(R.string.minus_symbol)))
            maxLen++;
        if (outputField.getText().toString().length() < maxLen ){
            if (!outputField.getText().toString().contains(getString(R.string.decimal_separator))) {
                outputField.append(getString(R.string.decimal_separator));
            }
            else {
                outputField.setText(outputField.getText().toString().replace(getString(R.string.decimal_separator), ""));
            }
        }
    }

    public void onClickClear(View view){
        outputField.setText(getString(R.string._0));
    }

    @SuppressLint("SetTextI18n")
    public void onClickNegative(View view){
        if (!outputField.getText().toString().equals("0")) {
            if (!outputField.getText().toString().startsWith(getString(R.string.minus_symbol)))
                outputField.setText(getString(R.string.minus_symbol) + outputField.getText().toString());
            else
                outputField.setText(outputField.getText().toString().replace(getString(R.string.minus_symbol), ""));
        }
    }
}