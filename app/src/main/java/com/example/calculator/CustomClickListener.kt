package com.example.calculator

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.calculator.enums.DataUpdateType
import com.example.calculator.enums.ViewType
import com.example.calculator.enums.Operator

class CustomClickListener(
    private val hashMapOfButtons: HashMap<ViewType, View>,
    private val iOCalcHistoryListener: MainActivity.IOCalcHistoryListener
) : View.OnClickListener {

    init {
        initClickListener()
    }

    private var firstNum = ""
    private var secondNum = ""
    private var isFirstNumTyped = false
    private var displayText = ""
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
                displayText = ""
                tvDisplayNumbers.text = ""
                firstNum = ""
                secondNum = ""
                isFirstNumTyped = false
            }
            hashMapOfButtons[ViewType.CancelSingleText]-> {
                if(!tvDisplayNumbers.text.isNullOrEmpty()){
                    displayText = tvDisplayNumbers.text.dropLast(1).toString()
                    tvDisplayNumbers.text = displayText
                    if (isFirstNumTyped){
                        secondNum = displayText
                    }else{
                        firstNum = displayText
                    }
                }

            }
            hashMapOfButtons[ViewType.ClearData]-> {
                displayText = ""
                tvDisplayNumbers.text = ""
                firstNum = ""
                secondNum = ""
                isFirstNumTyped = false
                iOCalcHistoryListener.listenDataChange(displayText,DataUpdateType.Delete)

            }
            hashMapOfButtons[ViewType.Equals]-> {
//                val result = calculate(firstNum, secondNum, operator)
                var finalCalcValue =""
                val result = EvaluateString.evaluate(displayText)
                if (result.isNotEmpty()) {
                    var isIntType = true
                    val subString = result.substringAfter(".")
                    subString.forEach {
                        for (i in 1..9){
                            val str = i.toString()
                            if (it.toString() == str){
                                isIntType = false
                            }
                        }
                    }
                    if (isIntType){
//                        result.removeSuffix(".")
                        for (i in result){
                            if (i == '.'){
                                break
                            }else{
                                finalCalcValue+=i.toString()
                            }
                        }
                    }else finalCalcValue = result
                    iOCalcHistoryListener.listenDataChange("$displayText=$finalCalcValue",DataUpdateType.Insert)
                }else return

                displayText = finalCalcValue

//                when(operator){
//                    Operator.Addition ->{
//                       displayText = "$displayText = $result"
//                    }
//                    Operator.Subtraction ->{
//                        displayText = "$displayText = $result"
//                    }
//                    Operator.Multiplication ->{
//                        displayText = "$displayText = $result"
//                    }
//                    Operator.Division->{
//                        displayText = "$displayText = $result"
//                    }
//                    Operator.Mod->{
//                        displayText = "$displayText = $result"
//                    }
//                    else -> displayText = ""
//
//                }
                tvDisplayNumbers.text = displayText
                firstNum = result

                secondNum = ""
                isFirstNumTyped = false
                Log.i("tag", "equal btn clicked")
            }
            hashMapOfButtons[ViewType.Addition] -> {
                displayText += "+"
                tvDisplayNumbers.text = displayText
                isFirstNumTyped = true
                operator = Operator.Addition
            }
            hashMapOfButtons[ViewType.Subtraction] ->{
                displayText += "-"
                tvDisplayNumbers.text = displayText
                isFirstNumTyped = true
                operator = Operator.Subtraction
            }
            hashMapOfButtons[ViewType.Multiplication] ->{
                displayText += "×"
                tvDisplayNumbers.text = displayText
                isFirstNumTyped = true
                operator = Operator.Multiplication
            }
            hashMapOfButtons[ViewType.Division] ->{
                displayText += "÷"
                tvDisplayNumbers.text = displayText
                isFirstNumTyped = true
                operator = Operator.Division
            }
            hashMapOfButtons[ViewType.Mod] ->{
                displayText += "%"
                tvDisplayNumbers.text = displayText
                isFirstNumTyped = true
                operator = Operator.Mod
            }
            hashMapOfButtons[ViewType.Dot] ->{
//                if (!isFirstNumTyped) {
//                    firstNum += "."
//                    displayText += "."
//                    tvDisplayNumbers.text = displayText
//                } else {
//                    displayText += "."
//                    secondNum += "."
//                    tvDisplayNumbers.text = displayText
//                }

                displayText += ".".trim()
                tvDisplayNumbers.text = displayText
            }
            hashMapOfButtons[ViewType.Btn1] -> {
//                if (!isFirstNumTyped) {
//                    displayText += "1".trim()
//                    firstNum += "1"
//                    tvDisplayNumbers.text = displayText
//                } else {
//                    displayText += "1".trim()
//                    secondNum += "1"
//                    tvDisplayNumbers.text = displayText
//                }

                displayText += "1".trim()
                tvDisplayNumbers.text = displayText
            }
            hashMapOfButtons[ViewType.Btn2]->{
//                if (!isFirstNumTyped){
//                    displayText += "2".trim()
//                    firstNum += "2"
//                    tvDisplayNumbers.text = displayText
//                }else{
//                    displayText += "2".trim()
//                    secondNum += "2"
//                    tvDisplayNumbers.text = displayText
//                }
                displayText += "2".trim()
                tvDisplayNumbers.text = displayText

            }
            hashMapOfButtons[ViewType.Btn3]->{
//                if (!isFirstNumTyped){
//                    displayText += "3".trim()
//                    firstNum += "3"
//                }else{
//                    displayText += "3".trim()
//                    secondNum += "3"
//                }

                displayText += "3".trim()
                tvDisplayNumbers.text = displayText

            }
            hashMapOfButtons[ViewType.Btn4]->{
//                if (!isFirstNumTyped){
//                    firstNum += "4"
//                    tvDisplayNumbers.text = firstNum
//                }else{
//                    secondNum +=  "4"
//                    tvDisplayNumbers.text = secondNum
//                }
                displayText += "4".trim()
                tvDisplayNumbers.text = displayText

            }
            hashMapOfButtons[ViewType.Btn5]->{
//                if (!isFirstNumTyped){
//                    firstNum +=  "5"
//                    tvDisplayNumbers.text = firstNum
//                }else{
//                    secondNum += "5"
//                    tvDisplayNumbers.text = secondNum
//                }
                displayText += "5".trim()
                tvDisplayNumbers.text = displayText

            }
            hashMapOfButtons[ViewType.Btn6]->{
//                if (!isFirstNumTyped){
//                    firstNum += "6"
//                    tvDisplayNumbers.text = firstNum
//                }else{
//                    secondNum +=  "6"
//                    tvDisplayNumbers.text = secondNum
//                }
                displayText += "6".trim()
                tvDisplayNumbers.text = displayText

            }
            hashMapOfButtons[ViewType.Btn7]->{
//                if (!isFirstNumTyped){
//                    firstNum +=  "7"
//                    tvDisplayNumbers.text = firstNum
//                }else{
//                    secondNum += "7"
//                    tvDisplayNumbers.text = secondNum
//                }
                displayText += "7".trim()
                tvDisplayNumbers.text = displayText

            }
            hashMapOfButtons[ViewType.Btn8]->{
//                if (!isFirstNumTyped){
//                    firstNum += "8"
//                    tvDisplayNumbers.text = firstNum
//                }else{
//                    secondNum +=  "8"
//                   tvDisplayNumbers.text = secondNum
//                }
                displayText += "8".trim()
                tvDisplayNumbers.text = displayText
            }
            hashMapOfButtons[ViewType.Btn9]->{
//                if (!isFirstNumTyped){
//                    firstNum +=  "9"
//                    tvDisplayNumbers.text = firstNum
//                }else{
//                    secondNum += "9"
//                   tvDisplayNumbers.text = secondNum
//                }
                displayText += "9".trim()
                tvDisplayNumbers.text = displayText

            }
            hashMapOfButtons[ViewType.Btn0] -> {
//                if (!isFirstNumTyped) {
//                    firstNum += "0"
//                    tvDisplayNumbers.text = firstNum
//                } else {
//                    secondNum += "0"
//                    tvDisplayNumbers.text = secondNum
//                }
                displayText += "0".trim()
                tvDisplayNumbers.text = displayText
            }
        }
    }

    private fun calculate(firstNumber: String, secondNumber: String, operator: Operator): String {
        var result = ""
        try {
            val isTypeFloat = firstNumber.contains(".") || secondNumber.contains(".")
            when (operator) {
                Operator.Addition -> {
                    result = if (isTypeFloat){
                        (firstNumber.toFloat().plus(secondNumber.toFloat())).toString()
                    }else {
                        (firstNumber.toLong().plus(secondNumber.toLong())).toString()
                    }
                }
                Operator.Subtraction -> {
                    result = if (isTypeFloat){
                        (firstNumber.toFloat().minus(secondNumber.toFloat())).toString()
                    }else {
                        (firstNumber.toLong().minus(secondNumber.toLong())).toString()
                    }
                }
                Operator.Multiplication -> {
                    result = if (isTypeFloat){
                        (firstNumber.toFloat().times(secondNumber.toFloat())).toString()
                    }else {
                        (firstNumber.toLong().times(secondNumber.toLong())).toString()
                    }
                }
                Operator.Division -> {
                    val temp = firstNumber.toFloat().div(secondNumber.toFloat()).toString()

                    result = if (isTypeFloat){
                        (firstNumber.toFloat().div(secondNumber.toFloat())).toString()
                    }else {
                        (firstNumber.toLong().div(secondNumber.toLong())).toString()
                    }
                }
                Operator.Mod -> {
                    result = if (isTypeFloat){
                        (firstNumber.toFloat().mod(secondNumber.toFloat())).toString()
                    }else {
                        (firstNumber.toLong().mod(secondNumber.toLong())).toString()
                    }
                }
                else -> {
                    Log.i("tag", "No operator selected")
                }
            }
        } catch (e: Exception) {
            Log.e("tag", "exception caught $e")
            tvDisplayNumbers.text = ""
        }
        return result
    }

}
