package com.example.myapplication

import java.lang.Integer.parseInt
import kotlin.properties.Delegates

class CalculatorModel {

    companion object {
        fun add(input1: String, input2: String): String {
            try {
                val number1 = input1.toDouble()
                val number2 = input2.toDouble()
                return (number1 + number2).toString()
            } catch (e: NumberFormatException) {
                println("Error: One or both of the inputs are not valid numbers.")
                return "invalid"
            }
        }

        fun sub(input1: String, input2: String): String {
            try {
                val number1 = input1.toDouble()
                val number2 = input2.toDouble()
                return (number1 - number2).toString()
            } catch (e: NumberFormatException) {
                println("Error: One or both of the inputs are not valid numbers.")
                return "invalid"
            }
        }

        class InvalidOperandException(message: String) : Exception(message)

        private fun validatenumber(input2: String){
            val number2 = input2.toDouble()
            if (number2 == 0.0){
                throw InvalidOperandException("")
            }
        }

        fun div(input1: String, input2: String): String {
            try {
                validatenumber(input2)
                val number1 = input1.toDouble()
                val number2 = input2.toDouble()
                return (number1 / number2).toString()
            } catch (e: NumberFormatException) {
                println("Error: One or both of the inputs are not valid numbers.")
                return "invalid"
            } catch (e: InvalidOperandException){
                println(e.message)
                return "invalid operand"
            }
        }

        fun product(input1: String, input2: String): String {
            try {
                val number1 = input1.toDouble()
                val number2 = input2.toDouble()
                return (number1 * number2).toString()
            } catch (e: NumberFormatException) {
                println("Error: One or both of the inputs are not valid numbers.")
                return "invalid"
            }
        }

        fun mod(input1: String, input2: String): String {
            try {
                validatenumber(input2)
                val number1 = input1.toDouble()
                val number2 = input2.toDouble()
                return (number1 % number2).toString()
            } catch (e: NumberFormatException) {
                println("Error: One or both of the inputs are not valid numbers.")
                return "invalid"
            } catch (e: InvalidOperandException){
                println(e.message)
                return "invalid operand"
            }
        }

        fun sqrt(input1: String): String {
            try {
                val number1 = solve(input1)
                return Math.sqrt(number1).toString()
            } catch (e: NumberFormatException) {
                println("Error: One or both of the inputs are not valid numbers.")
                return "invalid"
            } catch (e: InvalidOperandException){
                println(e.message)
                return "invalid operand"
            }
        }

        fun solve(input1: String): Double {

            var div = input1.split("-")
            if (div.size > 1) {
                var result = solve(div[0])
                for (d in div.drop(1)) {
                    result -= solve(d)
                }
                return result
            }

            div = input1.split("+")
            if (div.size > 1) {
                var result = 0.0
                for (d in div) {
                    result += solve(d)
                }
                return result
            }

            div = input1.split("*")
            if (div.size > 1) {
                var result = 1.0
                for (d in div) {
                    result *= solve(d)
                }
                return result
            }

            div = input1.split("/")
            if (div.size > 1) {
                var result = solve(div[0])
                for (d in div.drop(1)) {
                    var divider = solve(d);
                    if (divider==0.0)
                    {
                        throw InvalidOperandException("Dividend cannot be zero")
                    }
                    result /= solve(d)
                }
                return result
            }
            System.out.println(input1.toDouble())
            return input1.toDouble()
        }
    }
}