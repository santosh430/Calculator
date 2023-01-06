package com.example.calculator

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.calculator.enums.ViewType
import com.example.calculator.enums.Operator

class CustomClickListener(private val hashMapOfButtons: HashMap<ViewType,View>) : View.OnClickListener {

    init {
        initClickListener()
    }

    private var firstNum = ""
    private var secondNum = ""
    private var isFirstNumTyped = false
    private var operator: Operator = Operator.None
    private val tvDisplayNumbers = hashMapOfButtons[ViewType.TVDisplayNumbers] as TextView

    private fun initClickListener() {
        hashMapOfButtons.values.forEach { view ->
            if (view is Button) {
                view.setOnClickListener(this)
            }
        }
    }

    override fun onClick(view: View?) {

        when (view) {
            hashMapOfButtons[ViewType.Cancel]-> {
                tvDisplayNumbers.text = ""
                firstNum = ""
                secondNum = ""
                isFirstNumTyped = false
            }
            hashMapOfButtons[ViewType.Equals]-> {
                val result = calculate(firstNum, secondNum, operator)
                tvDisplayNumbers.text = result
                firstNum = result
                secondNum = ""
                isFirstNumTyped = false
                Log.i("tag", "equal btn clicked")
            }
            hashMapOfButtons[ViewType.Addition] -> {
                tvDisplayNumbers.text = "+"
                isFirstNumTyped = true
                operator = Operator.Addition
            }
            hashMapOfButtons[ViewType.Subtraction] ->{
                tvDisplayNumbers.text = "-"
                isFirstNumTyped = true
                operator = Operator.Subtraction
            }
            hashMapOfButtons[ViewType.Multiplication] ->{
                tvDisplayNumbers.text = "*"
                isFirstNumTyped = true
                operator = Operator.Multiplication
            }
            hashMapOfButtons[ViewType.Division] ->{
                tvDisplayNumbers.text = "/"
                isFirstNumTyped = true
                operator = Operator.Division
            }
            hashMapOfButtons[ViewType.Mod] ->{
                tvDisplayNumbers.text = "%"
                isFirstNumTyped = true
                operator = Operator.Mod
            }

            hashMapOfButtons[ViewType.Btn1] -> {
                if (!isFirstNumTyped) {
                    firstNum += "1"
                    tvDisplayNumbers.text = firstNum
                } else {
                    secondNum += "1"
                    tvDisplayNumbers.text = secondNum
                }

            }
            hashMapOfButtons[ViewType.Btn2]->{
                if (!isFirstNumTyped){
                    firstNum += "2"
                    tvDisplayNumbers.text = firstNum
                }else{
                    secondNum += "2"
                    tvDisplayNumbers.text = secondNum
                }

            }
            hashMapOfButtons[ViewType.Btn3]->{
                if (!isFirstNumTyped){
                    firstNum += "3"
                    tvDisplayNumbers.text = firstNum
                }else{
                    secondNum += "3"
                    tvDisplayNumbers.text = secondNum
                }

            }
            hashMapOfButtons[ViewType.Btn4]->{
                if (!isFirstNumTyped){
                    firstNum += "4"
                    tvDisplayNumbers.text = firstNum
                }else{
                    secondNum +=  "4"
                    tvDisplayNumbers.text = secondNum
                }

            }
            hashMapOfButtons[ViewType.Btn5]->{
                if (!isFirstNumTyped){
                    firstNum +=  "5"
                    tvDisplayNumbers.text = firstNum
                }else{
                    secondNum += "5"
                    tvDisplayNumbers.text = secondNum
                }

            }
            hashMapOfButtons[ViewType.Btn6]->{
                if (!isFirstNumTyped){
                    firstNum += "6"
                    tvDisplayNumbers.text = firstNum
                }else{
                    secondNum +=  "6"
                    tvDisplayNumbers.text = secondNum
                }

            }
            hashMapOfButtons[ViewType.Btn7]->{
                if (!isFirstNumTyped){
                    firstNum +=  "7"
                    tvDisplayNumbers.text = firstNum
                }else{
                    secondNum += "7"
                    tvDisplayNumbers.text = secondNum
                }

            }
            hashMapOfButtons[ViewType.Btn8]->{
                if (!isFirstNumTyped){
                    firstNum += "8"
                    tvDisplayNumbers.text = firstNum
                }else{
                    secondNum +=  "8"
                   tvDisplayNumbers.text = secondNum
                }

            }
            hashMapOfButtons[ViewType.Btn9]->{
                if (!isFirstNumTyped){
                    firstNum +=  "9"
                    tvDisplayNumbers.text = firstNum
                }else{
                    secondNum += "9"
                   tvDisplayNumbers.text = secondNum
                }

            }
            hashMapOfButtons[ViewType.Btn0] -> {
                if (!isFirstNumTyped) {
                    firstNum += "0"
                    tvDisplayNumbers.text = firstNum
                } else {
                    secondNum += "0"
                    tvDisplayNumbers.text = secondNum
                }
            }
        }
    }

    private fun calculate(firstNumber: String, secondNumber: String, operator: Operator): String {
        var result = ""
        try {
            when (operator) {
                Operator.Addition -> {
                    result = (firstNumber.toInt().plus(secondNumber.toInt())).toString()
                }
                Operator.Subtraction -> {
                    result = (firstNumber.toInt().minus(secondNumber.toInt())).toString()
                }
                Operator.Multiplication -> {
                    result = (firstNumber.toInt().times(secondNumber.toInt())).toString()
                }
                Operator.Division -> {
                    result = (firstNumber.toInt().div(secondNumber.toInt())).toString()
                }
                Operator.Mod -> {
                    result = (firstNumber.toInt().mod(secondNumber.toInt())).toString()
                }
                else -> {
                    Log.i("tag", "No operator selected")
                }
            }
        } catch (e: Exception) {
            Log.e("tag", "exception caught $e")
            val tv = hashMapOfButtons[ViewType.TVDisplayNumbers] as TextView
            tv.text = ""
        }
        return result
    }

}
