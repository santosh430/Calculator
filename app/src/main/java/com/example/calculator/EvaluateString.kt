package com.example.calculator

import android.util.Log
import java.lang.ArithmeticException
import java.util.*
import kotlin.jvm.JvmStatic

object EvaluateString {
    private var isExceptionCaught = false
    fun evaluate(expression: String): String {
        val tokens = expression.toCharArray()

        // Stack for numbers: 'values'
        val values = Stack<Double>()

        // Stack for Operators: 'ops'
        val ops = Stack<Char>()
        var i = 0
        while (i < tokens.size) {

            // Current token is a whitespace, skip it
            if (tokens[i] == ' ') {
                i++
                continue
            }

            // Current token is a number, push it to stack for numbers
            if (tokens[i] in '0'..'9') {
                val stringBuffer = StringBuffer()
                // There may be more than one digits in number
                while (i < tokens.size && tokens[i] >= '0' && tokens[i] <= '9') {
                    stringBuffer.append(tokens[i++])
                }
                values.push(stringBuffer.toString().toDouble())
            }
            else if (tokens[i] == '(') ops.push(tokens[i++])
            else if (tokens[i] == ')') {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()))
                ops.pop()
                i++
            }
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '×' || tokens[i] == '÷' || tokens[i] == '%') {
                // While top of 'ops' has same or greater precedence to current
                // token, which is an operator. Apply operator on top of 'ops'
                // to top two elements in values stack
                if ( !ops.empty() && ops.peek() == '-' && tokens[i] == '+')
                {
                    val firstValue = values.pop()
                    val secondValue = tokens[++i].toString().toDouble()
                    values.push(applyOp(ops.pop(),secondValue,firstValue))
                    if (firstValue > secondValue){
                        ops.push('-')
                    }
                    i++
                    continue
                }
                while (!ops.empty() && hasPrecedence(ops.peek(),tokens[i]))
                {
                    if (ops.peek() == '-' && !values.isEmpty()) {
                        ops.push(tokens[i])
                        values.push(tokens[++i].toString().toDouble())
                        values.push(applyOp(ops.pop(), values.pop(), values.pop()))
                        break
                    }
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()))
                }


                // Push current token to 'ops'.
                ops.push(tokens[i])
                i++
            }
            else if (tokens[i] == '.'){
                return ""
            }

        }

        // Entire expression has been parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty()){
            if (ops.peek() == '-'){
                return "-${values.pop()}"
            }
            values.push(applyOp(ops.pop(), values.pop(), values.pop()))

        }

        // Top of 'values' contains result, return it
        var result = values.pop().toString()

        return if (isExceptionCaught){
            result = ""
            result
        }else result
    }

    // Returns true if 'op2' has higher or same precedence as 'op1',
    // otherwise returns false.
    private fun hasPrecedence(op1: Char, op2: Char): Boolean {
        if (op2 == '(' || op2 == ')') return false
        if(op1 == '-' && (op2 == '%' || op2 == '×' || op2 == '÷' )) return true
        if (op1 in '0'..'9' || op2 in '0'..'9') return false
        return if ((op1 == '×' || op1 == '÷') && (op2 == '+' || op2 == '-')) false else true
    }

    // A utility method to apply an operator 'op' on operands 'a' 
    // and 'b'. Return the result.
    private fun applyOp(op: Char, b: Double, a: Double): Double {
        var result = 0.0
        try {
            when (op) {
                '+' -> result = a + b
                '-' -> result = a - b
                '×' -> result = a*b
                '÷' -> result = a / b
                '%' -> result = a % b
            }
            isExceptionCaught = false
        }catch (e:ArithmeticException){
            Log.e("exception","exception $e")
            isExceptionCaught = true
        }

        return result
    }

}