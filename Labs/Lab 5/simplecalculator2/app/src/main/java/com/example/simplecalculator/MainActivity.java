package com.example.simplecalculator;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/***************************************************************************
 * Calculator Main
 * Description: Basic calculator app that supports addition, subtraction,
 *              multiplication, division, and percentages. Features a button
 *              to swap between positive and negative values.
 ***************************************************************************/

public class MainActivity extends AppCompatActivity {

    // Initialize variables.
    String prevNumber = "0";
    String op = "+";
    boolean isNewOp = true;
    EditText textBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textBox = findViewById(R.id.editText);
    }

    /***********************************************
     * Number Event
     * Description: Takes new value input and adds
     *              it to the previous number.
     ***********************************************/
    public void numberEvent(View view) {

        // If operator was placed before it, empty ed1 and set isNewOp to false.
        if(isNewOp)
            textBox.setText("");
        isNewOp = false;

        // Grab previous numbers from ed1.
        String number = textBox.getText().toString();

        // Add appropriate number or operation based on button clicked.
        switch (view.getId()){

            case R.id.bu1:
                number += "1";
                break;
            case R.id.bu2:
                number += "2";
                break;

            case R.id.bu3:
                number += "3";
                break;

            case R.id.bu4:
                number += "4";
                break;

            case R.id.bu5:
                number += "5";
                break;

            case R.id.bu6:
                number += "6";
                break;

            case R.id.bu7:
                number += "7";
                break;

            case R.id.bu8:
                number += "8";
                break;

            case R.id.bu9:
                number += "9";
                break;

            case R.id.bu0:
                number += "0";
                break;

            case R.id.buDot:
                number += ".";
                break;

            case R.id.buPlusMinus:
                // If a negative is present, remove it. If not, add one.
                if(number.indexOf('-') == -1 )
                    number  = "-" + number;
                else
                    number = number.substring(1);
                break;
        }

        // Submit new value to ed1 and display it.
        textBox.setText(number);
    }

    /***************************************************
     * Operator Event
     * Description: Saves the operator type and empties
     *              the text box for a second value
     ***************************************************/
    public void operatorEvent(View view) {
        isNewOp = true; // Reset operator condition to accept second value.
        prevNumber = textBox.getText().toString(); // Store previous value.

        switch (view.getId()){

            case R.id.buDivide: op = "÷"; break;
            case R.id.buMultiply: op = "*"; break;
            case R.id.buPlus: op = "+"; break;
            case R.id.buMinus: op = "-"; break;
        }

    }

    /*******************************************
     * Equals Event
     * Description:
     *******************************************/
    public void equalEvent(View view) {
        String newNumber = textBox.getText().toString();
        double result = 0.0;
        int tempVal;

        // Uses the operator as a switch and manipulates the old and current numbers accordingly.
        switch (op){

            case "+":
                result = Double.parseDouble(prevNumber) + Double.parseDouble(newNumber);
                break;
            case "-":
                result = Double.parseDouble(prevNumber) - Double.parseDouble(newNumber);
                break;
            case "*":
                result = Double.parseDouble(prevNumber) * Double.parseDouble(newNumber);
                break;

            case "/":
                result = Double.parseDouble(prevNumber) / Double.parseDouble(newNumber);
                break;
        }

        // If the final result doesn't contain decimals, convert it to an integer before displaying.
        if(result%1 == 0 ){
            tempVal = (int) result;
            textBox.setText(tempVal + "");
        }
        // If if does contain decimals, display it as is.
        else {
            textBox.setText(result + "");
        }
    }

    /*************************************************
     * All Clear Event
     * Description: Erases current and old value
     *              and sets operator to new.
     *************************************************/
    public void acEvent(View view) {
        textBox.setText("0");
        prevNumber = "0";
        isNewOp = true;
    }

    /**********************************************
     * Percent Event
     * Description: Converts the current number to
     *              a percentage.
     **********************************************/
    public void percentEvent(View view) {
        double no = Double.parseDouble(textBox.getText().toString()) / 100;
        textBox.setText(no+"");
        isNewOp = true;
    }
}