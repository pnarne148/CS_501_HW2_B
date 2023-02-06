package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var clear = true
    private var operator = false
    private var decimal = false

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initialise()
        setNumberListeners()
        setOperationListeners()
        setClearListener()
        setDotListener()
    }

    private fun setDotListener() {
        binding.BtnDecimal.setOnClickListener {
            if(!decimal)
                if(clear)
                {
                    binding.TvInputWindow.setText("")
                    clear = false
                    binding.TvInputWindow.append("0.")
                }
                else if (operator)
                {
                    binding.TvInputWindow.append("0.")
                }
                else
                {
                    binding.TvInputWindow.append(".")
                }
            decimal = true
            operator = false
        }
    }

    private fun setClearListener() {
        binding.BtnClear.setOnClickListener {
            initialise()
            clear = true
            operator = false
        }

        binding.BtnBackBtn.setOnClickListener {
            if(!clear)
            {
                if(operator)
                {
                    if(binding.TvInputWindow.text.last() == ')')
                        binding.TvInputWindow.setText(binding.TvInputWindow.text.dropLast(1).drop(5))
                    else
                        binding.TvInputWindow.setText(binding.TvInputWindow.text.dropLast(3))
                }
                else
                {
                    binding.TvInputWindow.setText(binding.TvInputWindow.text.dropLast(1))
                }
            }
        }
    }

    private fun setOperationListeners() {
        binding.BtnAdd.setOnClickListener {
            operatorClick("+")
        }
        binding.BtnSubstract.setOnClickListener {
            operatorClick("-")
        }
        binding.BtnMultiply.setOnClickListener {
            operatorClick("*")
        }
        binding.BtnDivide.setOnClickListener {
            operatorClick("/")
        }
        binding.BtnSqrt.setOnClickListener {
            operatorClick("sqrt")
        }
    }

    private fun operatorClick(s: String) {
        if(!operator) {
            when (s) {
                "+" -> binding.TvInputWindow.append(" + ")
                "-" -> binding.TvInputWindow.append(" - ")
                "*" -> binding.TvInputWindow.append(" * ")
                "/" -> binding.TvInputWindow.append(" / ")
                "%" -> binding.TvInputWindow.append(" % ")
                "sqrt" -> binding.TvInputWindow.setText("Sqrt(" + binding.TvInputWindow.text + ")")
                else -> Toast.makeText(this, "INVALID", Toast.LENGTH_SHORT).show()
            }
            if(s != "sqrt")
                operator = true
            decimal=false
        }
    }

    private fun setNumberListeners() {
        binding.Btn0.setOnClickListener { view: View->
            numberClick(0)
        }
        binding.Btn1.setOnClickListener {
            numberClick(1)
        }
        binding.Btn2.setOnClickListener {
            numberClick(2)
        }
        binding.Btn3.setOnClickListener {
            numberClick(3)
        }
        binding.Btn4.setOnClickListener {
            numberClick(4)
        }
        binding.Btn5.setOnClickListener {
            numberClick(5)
        }
        binding.Btn6.setOnClickListener {
            numberClick(6)
        }
        binding.Btn7.setOnClickListener {
            numberClick(7)
        }
        binding.Btn8.setOnClickListener {
            numberClick(8)
        }
        binding.Btn9.setOnClickListener {
            numberClick(9)
        }
    }

    private fun numberClick(i: Int) {
        if(clear) {
            binding.TvInputWindow.setText("")
            clear = false
        }

        binding.TvInputWindow.append(i.toString())
        while (binding.TvInputWindow.text[0]=='0')
            binding.TvInputWindow.setText(binding.TvInputWindow.text.drop(1))

        operator = false
    }

    private fun initialise() {
        binding.TvInputWindow.setText("0.0")
        binding.TvResultWindow.setText("")
    }
}
