package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.enums.ViewType


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private var firstNum = ""
    private var secondNum = ""
    private var isFirstNumTyped = false
    private var operator:Operator = Operator.None

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val btnCancel = binding.btnCancelText
        val btnEquals = binding.btnEquals
        val btnCancelSingleText = binding.btnCancelSingleText
        val btnMod = binding.btnModulus
        val btnDivision = binding.btnDivision
        val btnMultiplication = binding.btnMultiplication
        val btnSubtraction = binding.btnSubstraction
        val btnAddition = binding.btnAddition
        val btnNum1 = binding.btnNumber1
        val btnNum2 = binding.btnNumber2
        val btnNum3 = binding.btnNumber3
        val btnNum4 = binding.btnNumber4
        val btnNum5 = binding.btnNumber5
        val btnNum6 = binding.tvBtnNumber6
        val btnNum7 = binding.btnNumber7
        val btnNum8 = binding.btnNumber8
        val btnNum9 = binding.btnNumber9
        val btnNum0 = binding.btnNumber0
        val btnDot = binding.btnDot
        val tvDisplayNumbers = binding.tvDisplayNumbers

        val mapOfButtons = HashMap<ViewType,View>()
        mapOfButtons.apply {
            this[ViewType.Cancel] = btnCancel
            this[ViewType.CancelSingleText] = btnCancelSingleText
            this[ViewType.Equals] = btnEquals
            this[ViewType.Addition] = btnAddition
            this[ViewType.Subtraction] = btnSubtraction
            this[ViewType.Multiplication] = btnMultiplication
            this[ViewType.Division] = btnDivision
            this[ViewType.Mod] = btnMod
            this[ViewType.Dot] = btnDot
            this[ViewType.TVDisplayNumbers] = tvDisplayNumbers
            this[ViewType.Dot] = btnDot
            this[ViewType.Btn0] = btnNum0
            this[ViewType.Btn1] = btnNum1
            this[ViewType.Btn2] = btnNum2
            this[ViewType.Btn3] = btnNum3
            this[ViewType.Btn4] = btnNum4
            this[ViewType.Btn5] = btnNum5
            this[ViewType.Btn6] = btnNum6
            this[ViewType.Btn7] = btnNum7
            this[ViewType.Btn8] = btnNum8
            this[ViewType.Btn9] = btnNum9
        }

//        btnCancel.setOnClickListener(this)
//        btnEquals.setOnClickListener(this)
//        btnCancelSingleText.setOnClickListener(this)
//        btnMod.setOnClickListener(this)
//        btnDivision.setOnClickListener(this)
//        btnMultiplication.setOnClickListener(this)
//        btnSubtraction.setOnClickListener(this)
//        btnAddition.setOnClickListener(this)
//        btnNum0.setOnClickListener(this)
//        btnNum1.setOnClickListener(this)
//        btnNum2.setOnClickListener(this)
//        btnNum3.setOnClickListener(this)
//        btnNum4.setOnClickListener(this)
//        btnNum5.setOnClickListener(this)
//        btnNum6.setOnClickListener(this)
//        btnNum7.setOnClickListener(this)
//        btnNum8.setOnClickListener(this)
//        btnNum9.setOnClickListener(this)
//

        CustomClickListener(mapOfButtons)


    }

//    override fun onClick(view: View?) {
//
//        when(view){
//            binding.btnCancelText ->{
//                binding.tvDisplayNumbers.text = ""
//                firstNum = ""
//                secondNum = ""
//                isFirstNumTyped = false
//            }
//            binding.btnEquals ->{
//                val result = calculate(firstNum,secondNum,operator)
//                binding.tvDisplayNumbers.text = result
//                firstNum = result
//                secondNum = ""
//                isFirstNumTyped = false
//                Log.i("tag","equal btn clicked")
//            }
//            binding.btnAddition ->{
//                binding.tvDisplayNumbers.text = "+"
//                isFirstNumTyped = true
//                operator = Operator.Addition
//            }
//            binding.btnSubstraction ->{
//                binding.tvDisplayNumbers.text = "-"
//                isFirstNumTyped = true
//                operator = Operator.Subtraction
//            }
//            binding.btnMultiplication ->{
//                binding.tvDisplayNumbers.text = "*"
//                isFirstNumTyped = true
//                operator = Operator.Multiplication
//            }
//            binding.btnDivision ->{
//                binding.tvDisplayNumbers.text = "/"
//                isFirstNumTyped = true
//                operator = Operator.Division
//            }
//            binding.btnModulus ->{
//                binding.tvDisplayNumbers.text = "%"
//                isFirstNumTyped = true
//                operator = Operator.Mod
//            }
//
//            binding.btnNumber1->{
//                if (!isFirstNumTyped){
//                    firstNum +=  "1"
//                    binding.tvDisplayNumbers.text = firstNum
//                }else{
//                    secondNum += "1"
//                    binding.tvDisplayNumbers.text = secondNum
//                }
//
//            }
//            binding.btnNumber2->{
//                if (!isFirstNumTyped){
//                    firstNum += "2"
//                    binding.tvDisplayNumbers.text = firstNum
//                }else{
//                    secondNum += "2"
//                    binding.tvDisplayNumbers.text = secondNum
//                }
//
//            }
//            binding.btnNumber3->{
//                if (!isFirstNumTyped){
//                    firstNum += "3"
//                    binding.tvDisplayNumbers.text = firstNum
//                }else{
//                    secondNum += "3"
//                    binding.tvDisplayNumbers.text = secondNum
//                }
//
//            }
//            binding.btnNumber4->{
//                if (!isFirstNumTyped){
//                    firstNum += "4"
//                    binding.tvDisplayNumbers.text = firstNum
//                }else{
//                    secondNum +=  "4"
//                    binding.tvDisplayNumbers.text = secondNum
//                }
//
//            }
//            binding.btnNumber5->{
//                if (!isFirstNumTyped){
//                    firstNum +=  "5"
//                    binding.tvDisplayNumbers.text = firstNum
//                }else{
//                    secondNum += "5"
//                    binding.tvDisplayNumbers.text = secondNum
//                }
//
//            }
//            binding.tvBtnNumber6->{
//                if (!isFirstNumTyped){
//                    firstNum += "6"
//                    binding.tvDisplayNumbers.text = firstNum
//                }else{
//                    secondNum +=  "6"
//                    binding.tvDisplayNumbers.text = secondNum
//                }
//
//            }
//            binding.btnNumber7->{
//                if (!isFirstNumTyped){
//                    firstNum +=  "7"
//                    binding.tvDisplayNumbers.text = firstNum
//                }else{
//                    secondNum += "7"
//                    binding.tvDisplayNumbers.text = secondNum
//                }
//
//            }
//            binding.btnNumber8->{
//                if (!isFirstNumTyped){
//                    firstNum += "8"
//                    binding.tvDisplayNumbers.text = firstNum
//                }else{
//                    secondNum +=  "8"
//                    binding.tvDisplayNumbers.text = secondNum
//                }
//
//            }
//            binding.btnNumber9->{
//                if (!isFirstNumTyped){
//                    firstNum +=  "9"
//                    binding.tvDisplayNumbers.text = firstNum
//                }else{
//                    secondNum += "9"
//                    binding.tvDisplayNumbers.text = secondNum
//                }
//
//            }
//            binding.btnNumber0->{
//                if (!isFirstNumTyped){
//                    firstNum +=  "0"
//                    binding.tvDisplayNumbers.text = firstNum
//                }else{
//                    secondNum += "0"
//                    binding.tvDisplayNumbers.text = secondNum
//                }
//            }
//        }
//
//    }

    private fun calculate(firstNumber:String,secondNumber:String,operator:Operator):String{
        var result =""
        try {
            when(operator){
                Operator.Addition->{
                    result = (firstNumber.toInt() + secondNumber.toInt()).toString()
                }
                Operator.Subtraction->{
                    result = (firstNumber.toInt() - secondNumber.toInt()).toString()
                }
                Operator.Multiplication->{
                    result = (firstNumber.toInt() * secondNumber.toInt()).toString()
                }
                Operator.Division->{
                    result = (firstNumber.toInt() / secondNumber.toInt()).toString()
                }
                Operator.None ->{
                    Log.i("tag","No operator selected")
                }
                else->{
                    result = (firstNumber.toInt().mod(  secondNumber.toInt())).toString()
                }

            }
        }catch (e:Exception){
            Log.e("tag","exception caught $e")
            binding.tvDisplayNumbers.text = ""
        }
        return result
    }

    enum class Operator{
        Multiplication,
        Division,
        Addition,
        Subtraction,
        Mod,
        None
    }
}