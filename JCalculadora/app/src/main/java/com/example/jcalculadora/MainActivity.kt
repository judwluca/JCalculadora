package com.example.jcalculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val conta = findViewById<TextView>(R.id.math)
        val result = findViewById<TextView>(R.id.resultado)
        val backspaceBtt = findViewById<ImageButton>(R.id.btt_4)
        val clearBtt = findViewById<Button>(R.id.btt_1)
        val equals = findViewById<Button>(R.id.btt_24)

        var inicialized: Int = 0
        var operator: Int = 0
        var resultShown: Int = 0
        var numberText: CharSequence = ""




        /**val num_01 = findViewById<Button>(R.id.btt_17)
        val num_02 = findViewById<Button>(R.id.btt_18)
        val num_03 = findViewById<Button>(R.id.btt_19)
        val num_04 = findViewById<Button>(R.id.btt_13)
        val num_05 = findViewById<Button>(R.id.btt_14)
        val num_06 = findViewById<Button>(R.id.btt_15)
        val num_07 = findViewById<Button>(R.id.btt_9)
        val num_08 = findViewById<Button>(R.id.btt_10)
        val num_09 = findViewById<Button>(R.id.btt_11)

        num_01.setOnClickListener {
        conta.text = "${conta.text}1"
        }*/

        // Adding the list of numbers
        val numberButtonIds = listOf(
            // Numbers
            R.id.btt_22 ,R.id.btt_17, R.id.btt_18, R.id.btt_19,
            R.id.btt_13, R.id.btt_14, R.id.btt_15, R.id.btt_9,
            R.id.btt_10, R.id.btt_11,
            // Caracters
            R.id.btt_2 ,R.id.btt_3, R.id.btt_5, R.id.btt_6,
            R.id.btt_7 ,R.id.btt_8, R.id.btt_12, R.id.btt_16,
            R.id.btt_20 ,R.id.btt_23,
        )

        // Set OnClickListener for each number dynamically
        numberButtonIds.forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener {
                val number = findViewById<Button>(buttonId).text
                numberText = number

                if (resultShown == 1){
                    resultShown = 0
                    conta.text = result.text
                    result.text = "0"
                }

                if (number == "x²"){
                    numberText = "^"
                    operator = 1
                }

                if (number == "²√x"){
                    numberText = "√"
                    operator = 1
                }

                if (number == "x"){
                    numberText = "*"
                    operator = 1
                }

                if (number == "%" || number == "÷" || number == "-"|| number == "+"){
                    operator = 1
                }

                if (inicialized == 0 && operator == 0) {
                    inicialized = 1;
                    conta.text = numberText;
                }
                else if (inicialized == 1){
                    conta.append(numberText)
                }else{
                    conta.text = "ERRO"
                }
                operator = 0
            }
        }

        // Backspace Button
        backspaceBtt.setOnClickListener(){
            val remov = conta.text
            if (remov.length > 1) {
                conta.text = remov.substring(0, remov.length - 1)
            }
            else{
                inicialized = 0;
                conta.text = "0";
            }
        }

        clearBtt.setOnClickListener(){
            inicialized = 0;
            conta.text = "0";
            result.text = "0";
        }

        equals.setOnClickListener(){
            val calculatedResult = Expression(conta.text.toString()).calculate()

            if (calculatedResult.isNaN()){
                result.text = "#Erro!"
            }else{
                result.text = calculatedResult.toString()
                resultShown = 1
            }

        }
    }
}