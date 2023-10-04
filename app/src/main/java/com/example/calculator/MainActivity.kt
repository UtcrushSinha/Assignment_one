package com.example.calculator

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {


    private lateinit var inputText: EditText
    private var currentInput: StringBuilder = StringBuilder()
    private var currentOperator: String? = null
    private var operand1: Double? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val orientation = resources.configuration.orientation

        // Load the appropriate layout based on orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_lanscape)
        } else {
            setContentView(R.layout.activity_main)
        }


         inputText = findViewById<EditText>(R.id.inputText)

    }





    fun onNumberClick(view: View) {
        val button = view as Button
        currentInput.append(button.tag.toString())

        if (operand1 != null && currentOperator != null) {
            // If we have an operand and operator, append the number to the current input
            inputText.setText("${operand1!!} $currentOperator ${currentInput.toString()}")
        } else {
            // Otherwise, just show the number
            operand1 = currentInput.toString().toDouble()
            inputText.setText(currentInput.toString())

        }


    }

    fun onOperatorClick(view: View) {
        val button = view as Button
        val clickedOperator = button.tag.toString()

        if (operand1 == null && currentInput.isEmpty()) {
            // If operand1 is not set, set it to the current input and store the operator

            inputText.setText("Error")

            //inputText.setText("Error")
        }else if(operand1 == null && currentInput.isNotEmpty()){
            operand1 = currentInput.toString().toDouble()
            currentOperator = clickedOperator
            inputText.setText("$operand1 $currentOperator ")
            currentInput.clear()
        }else if (currentOperator != null) {
            // If operand1 is set and an operator is already selected, perform the calculation
            calculate()
            // After calculation, set the new operator
            currentOperator = clickedOperator
            inputText.append(" $currentOperator ")
            currentInput.clear()
        }else if(operand1!=null && currentOperator == null ){

            currentOperator = clickedOperator
            inputText.append("$currentOperator")
            currentInput.clear()
        }



    }

    private fun calculate() {
        val operand2 = currentInput.toString().toDouble()
        val result = when (currentOperator) {
            "+" -> operand1!! + operand2
            "-" -> operand1!! - operand2
            "*" -> operand1!! * operand2
            "/" -> operand1!! / operand2
            else -> 0.0
        }
        val formattedResult = String.format("%.2f", result)
        inputText.setText(formattedResult)
        operand1 = result
        currentInput.clear()
    }

    fun onEqualClick(view: View) {
        if (operand1 != null && currentOperator != null && currentInput.isNotEmpty()) {
            val operand2 = currentInput.toString().toDouble()
            val result = when (currentOperator) {
                "+" -> operand1!! + operand2
                "-" -> operand1!! - operand2
                "*" -> operand1!! * operand2
                "/" -> operand1!! / operand2
                else -> 0.0
            }

            val formattedResult = String.format("%.2f", result)
            inputText.setText(formattedResult)
            operand1 = result
            currentOperator = null
            currentInput.clear()
        }
    }

    fun onClearClick(view: View) {
        operand1 = null
        currentOperator = null
        currentInput.clear()
        inputText.setText("")
    }

















}