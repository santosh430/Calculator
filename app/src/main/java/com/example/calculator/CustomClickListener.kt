package com.example.calculator

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.calculator.enums.DataUpdateType
import com.example.calculator.enums.ViewType
import java.math.BigDecimal

class CustomClickListener(
    private val context: Context,
    private val hashMapOfButtons: HashMap<ViewType, View>,
    private val iOCalcHistoryListener: MainActivity.IOCalcHistoryListener
) : View.OnClickListener {

    init {
        initClickListener()
    }

    private var displayText = ""
    private var isLeftBracketTyped = false
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

            }
            hashMapOfButtons[ViewType.CancelSingleText]-> {
                if(!tvDisplayNumbers.text.isNullOrEmpty()){
                    displayText = tvDisplayNumbers.text.dropLast(1).toString()
                    tvDisplayNumbers.text = displayText
                }
            }
            hashMapOfButtons[ViewType.ClearData]-> {
                displayText = ""
                tvDisplayNumbers.text = ""
                iOCalcHistoryListener.listenDataChange(displayText,DataUpdateType.Delete)

            }
            hashMapOfButtons[ViewType.Equals]-> {
                val finalCalcValue: String
                var result = ""
                if (!displayText.contains('.') && displayText.isNotEmpty()) {
                    result = EvaluateString.evaluate(displayText)
                }
                if (result.isNotEmpty()) {
                    var isIntType = true
                    val bigDecimal = BigDecimal(result).toString()
                    if (bigDecimal.contains('.')) {
                        val subString = bigDecimal.substringAfter(".")
                        subString.forEach {
                            for (i in 1..9) {
                                val str = i.toString()
                                if (it.toString() == str) {
                                    isIntType = false
                                }
                            }
                        }
                    }
                    finalCalcValue = if (isIntType){
                        BigDecimal(result).toBigInteger().toString()
                    }else BigDecimal(result).toString()
                    iOCalcHistoryListener.listenDataChange("$displayText=$finalCalcValue",DataUpdateType.Insert)
                }else {
                    Toast.makeText(context, "Can't operate on decimal values", Toast.LENGTH_LONG).show()
                    return
                }

                displayText = finalCalcValue
                tvDisplayNumbers.text = displayText
            }
            hashMapOfButtons[ViewType.Addition] -> {
                displayText += "+"
                tvDisplayNumbers.text = displayText
            }
            hashMapOfButtons[ViewType.Subtraction] ->{
                displayText += "-"
                tvDisplayNumbers.text = displayText

            }
            hashMapOfButtons[ViewType.Multiplication] ->{
                displayText += "×"
                tvDisplayNumbers.text = displayText
            }
            hashMapOfButtons[ViewType.Division] ->{
                displayText += "÷"
                tvDisplayNumbers.text = displayText
            }
            hashMapOfButtons[ViewType.Mod] ->{
                displayText += "%"
                tvDisplayNumbers.text = displayText
            }
            hashMapOfButtons[ViewType.Brackets] ->{
                if (!isLeftBracketTyped){
                    displayText += "("
                    isLeftBracketTyped = true
                }else {
                    displayText += ")"
                    isLeftBracketTyped = false
                }
                tvDisplayNumbers.text = displayText
            }
            hashMapOfButtons[ViewType.Btn1] -> {
                displayText += "1"
                tvDisplayNumbers.text = displayText
            }
            hashMapOfButtons[ViewType.Btn2]->{
                displayText += "2"
                tvDisplayNumbers.text = displayText

            }
            hashMapOfButtons[ViewType.Btn3]->{
                displayText += "3"
                tvDisplayNumbers.text = displayText

            }
            hashMapOfButtons[ViewType.Btn4]->{
                displayText += "4"
                tvDisplayNumbers.text = displayText

            }
            hashMapOfButtons[ViewType.Btn5]->{
                displayText += "5"
                tvDisplayNumbers.text = displayText

            }
            hashMapOfButtons[ViewType.Btn6]->{
                displayText += "6"
                tvDisplayNumbers.text = displayText

            }
            hashMapOfButtons[ViewType.Btn7]->{
                displayText += "7"
                tvDisplayNumbers.text = displayText

            }
            hashMapOfButtons[ViewType.Btn8]->{
                displayText += "8"
                tvDisplayNumbers.text = displayText
            }
            hashMapOfButtons[ViewType.Btn9]->{
                displayText += "9"
                tvDisplayNumbers.text = displayText

            }
            hashMapOfButtons[ViewType.Btn0] -> {
                displayText += "0"
                tvDisplayNumbers.text = displayText
            }
        }
    }
}
