package com.geodeveloper.calculatorapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.sqrt
import java.text.DecimalFormat

//bit.ly/fccslide
class MainActivity : AppCompatActivity() {
    //This hold the values from the editTextField
    private var operand1: Double = 0.0
    //Initialization of result value
    private var result: Double = 0.0
    //Initialization of the btnValue (i.e +,-,/ etc)
    private var btnValue = ""
    //Initialization of the symbol (i.e +,-,/ etc)
    private var symbol = ""
    //FLAGS
    private var minusFlag = 0
    private var multFlag = 0
    private var equalFlag = 0
    private var numberDigitFlag = 0
    private var decimalFlag = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //Create Menu items
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //Inflate the menu layout
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //When an item is clicked in the menu
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                R.id.menu_about -> {
                    val i = Intent(this, About::class.java)
                    startActivity(i)
                }
                R.id.menu_other_apps -> {
                    val openURL = Intent(Intent.ACTION_VIEW)
                    openURL.data = Uri.parse("https://play.google.com/store/apps/developer?id=Ejilola+Hammed+Ejitomiwa")
                    startActivity(openURL)
                }
                R.id.menu_rate -> {
                    val openURL = Intent(android.content.Intent.ACTION_VIEW)
                    openURL.data = Uri.parse("https://play.google.com/store/apps/developer?id=Ejilola+Hammed+Ejitomiwa")
                    startActivity(openURL)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //Click events for all buttons
    fun clickEvents(view: View) {
        //Detect which button is selected
        val btnSelected = view as Button
        //when each button is selected
        when (btnSelected) {
            //for button 0
            btn0 -> {
                btnValue = "0"
                //Call this function(controlNumDel)
                controlNumDel()
            }
            btn1 -> {
                //for button 1
                btnValue = "1"
                //Call this function(controlNumDel)
                controlNumDel()
            }
            btn2 -> {
                //for button 2
                btnValue = "2"
                //Call this function(controlNumDel)
                controlNumDel()
            }
            btn3 -> {
                //for button 3
                btnValue = "3"
                //Call this function(controlNumDel)
                controlNumDel()
            }
            btn4 -> {
                //for button 4
                btnValue = "4"
                //Call this function(controlNumDel)
                controlNumDel()
            }
            btn5 -> {
                //for button 5
                btnValue = "5"
                //Call this function(controlNumDel)
                controlNumDel()
            }
            btn6 -> {
                //for button 6
                btnValue = "6"
                //Call this function(controlNumDel)
                controlNumDel()
            }
            btn7 -> {
                //for button 7
                btnValue = "7"
                //Call this function(controlNumDel)
                controlNumDel()
            }
            btn8 -> {
                //for button 8
                btnValue = "8"
                //Call this function(controlNumDel)
                controlNumDel()
            }
            btn9 -> {
                //for button 9
                btnValue = "9"
                //Call this function(controlNumDel)
                controlNumDel()
            }
            btn00 -> {
                //for button 00
                btnValue = "00"
                //Call this function(controlNumDel)
                controlNumDel()
            }
            btnDot -> {
                btnValue = "."
                //Call this function(controlNumDel)
                controlNumDel()
            }

            btnPlus -> {
                //Initialize the numberDigitFlag to 0
                numberDigitFlag = 0
                //set the button value to +
                btnValue = "+"
                //set the symbol to +
                symbol = "+"

                if (display_text_field.text.toString().contains(".") || result_display.text.toString().contains(
                        "."
                    )
                ) {
                    decimalFlag = 1
                }
                /**Initially equals flag is = 0(this means equal  button has not been clicked),
                 * because only the equal button set the equalFlag to 1
                 *
                 * THEREFORE if equalFlag is = 0 keep adding the value from the display_text_field to operand1
                 * **/
                if (equalFlag == 0) {
                    operand1 += display_text_field.text.toString().toDouble()
                } else {
                    /**Here equal button has been clicked and the result_display_view has some value
                     * Therefore set the value of the operand1 to the current value of the result_display_view
                     * **/
                    operand1 = result_display.text.toString().toDouble()
                }
                //clear the display_text_field
                display_text_field.setText("")
                //Keep appending the values to the all_operation_display
                all_operation_display.append(btnValue)
            }
            btnDiv -> {
                //Initialize the numberDigitFlag to 0
                numberDigitFlag = 0
                //Set the bntValue to /
                btnValue = "/"
                //Set the symbol  to /
                symbol = "/"

                /**Initially equals flag is = 0(this means equal  button has not been clicked),
                 * because only the equal button set the equalFlag to 1
                 *
                 * THEREFORE if 0 keep adding the value from the display_text_field to operand1
                 * **/
                if (equalFlag == 0) {
                    /**
                     * **/
                    if (operand1 == 0.0) {
                        operand1 = display_text_field.text.toString().toDouble()
                    } else {
                        operand1 /= display_text_field.text.toString().toDouble()
                    }
                } else {
                    /**Here equal button has been clicked and the result_display_view has some value
                     * Therefore set the value of the operand1 to the current value of the result_display_view
                     * **/
                    operand1 = result_display.text.toString().toDouble()
                }
                //When plus button is clicked ,it clear the display text but still append at the all_operand
                display_text_field.setText("")
                //Append all to the all_operation_textView
                all_operation_display.append(btnValue)
            }
            btnMinus -> {
                //Initialize the numberDigitFlag to 0
                numberDigitFlag = 0
                //Set the btnValue to -
                btnValue = "-"
                //Set the symbol to -
                symbol = "-"
                //If any contain decimal value, then set the decimal flag to 1
                if (display_text_field.text.toString().contains(".") || result_display.text.toString().contains(
                        "."
                    )
                ) {
                    decimalFlag = 1
                }

                if (minusFlag == 0) {
                    try {
                        if (equalFlag == 0) {
                            operand1 += display_text_field.text.toString().toDouble()
                            minusFlag = 1
                        } else {
                            operand1 = result_display.text.toString().toDouble()
                        }

                    } catch (e: Exception) {
                    }
                } else {
                    operand1 -= display_text_field.text.toString().toDouble()
                }
                if (display_text_field.length() <= 0) {
                    display_text_field.append("-")
                } else {
                    display_text_field.setText("")
                }

                all_operation_display.append(btnValue)
            }
            btnEquals -> {
                equalOperation()
            }
            btnMult -> {
                numberDigitFlag = 0
                if (multFlag == 0) {
                    operand1 = 1.0
                    multFlag = 1
                }
                if (display_text_field.text.toString().contains(".")) {
                    decimalFlag = 1
                }
                btnValue = "x"
                symbol = "*"
                if (equalFlag == 0) {
                    operand1 *= display_text_field.text.toString().toDouble()
                } else {
                    operand1 = result_display.text.toString().toDouble()
                }
                display_text_field.setText("")
                all_operation_display.append(btnValue)
            }
            btnSqrt -> {
                //It initialize the numberDigitBack  back to 0
                numberDigitFlag = 0
                btnValue = "√"
                symbol = "√"
                if (operand1 == 0.0) {
                    all_operation_display.append(btnValue)
                } else {
                    clearAll()
                    numberDigitFlag = 0
                    btnValue = "√"
                    symbol = "√"
                    all_operation_display.append(btnValue)
                }
            }
            btnPercent -> {
                numberDigitFlag = 0
                btnValue = "%"
                symbol = "%"
                if (operand1 == 0.0) {
                    all_operation_display.append(btnValue)
                } else {
                    clearAll()
                    numberDigitFlag = 0
                    btnValue = "%"
                    symbol = "%"
                    all_operation_display.append(btnValue)
                }
            }

            btnDel -> {
                clearAll()
            }
        }
    }

    private fun equalOperation() {
        val currentValue = display_text_field.text.toString().toDouble()
        if (symbol == "+") {
            result = operand1 + currentValue
            /**If decimalFlag == 1, it means one of the operand contain decimal value so the result should not be formated
             * **/
            if (decimalFlag == 1) {
                result_display.setText(result.toString())
                Toast.makeText(this, "Yeah it contain", Toast.LENGTH_LONG).show()
            }
            /**if decimalFlag == 0, it means none of the operand contain decimal value
             * therefore the result should be formated
             * **/
            else {
                val formatNum = DecimalFormat("###")
                result_display.setText(formatNum.format(result).toString())
                Toast.makeText(this, "No it didint contain", Toast.LENGTH_LONG).show()
            }

            equalFlag = 1
        }
        if (symbol == "-") {
            result = operand1 - currentValue
            if (decimalFlag == 1) {
                result_display.setText(result.toString())
                Toast.makeText(this, "Yeah it contain", Toast.LENGTH_LONG).show()
            } else {
                val formatNum = DecimalFormat("###")
                result_display.setText(formatNum.format(result).toString())

            }
            equalFlag = 1
        }
        if (symbol == "*") {
            result = operand1 * currentValue
            if (decimalFlag == 1) {
                result_display.setText(result.toString())
                Toast.makeText(this, "Yeah it contain", Toast.LENGTH_LONG).show()
            } else {
                val formatNum = DecimalFormat("###")
                result_display.setText(formatNum.format(result).toString())
            }
            equalFlag = 1
        }
        if (symbol == "/") {
            result = operand1 / currentValue
            result_display.setText(result.toString())
            equalFlag = 1
        }
        if (symbol == "√") {
            result = sqrt(currentValue)
            result_display.setText(result.toString())
            equalFlag = 1
        }

        if (symbol == "%") {
            result = currentValue / 100
            result_display.setText(result.toString())
            equalFlag = 1
        }
        //If equals button is clicked ,it set the numberDigitFlag to 1
        numberDigitFlag = 1
    }

    //To reset the whole operands and flag
    private fun clearAll() {
        operand1 = 0.0
        symbol = ""
        minusFlag = 0
        multFlag = 0
        equalFlag = 0
        numberDigitFlag = 0
        decimalFlag = 0
        display_text_field.setText("")
        result_display.setText("")
        all_operation_display.setText("")

    }

    //
    private fun controlNumDel() {
        /** if numberDigitFlag == 0 it means no value has been calculated THEN append the value to display text
         *
         * NOTE: only the equal button set the numberDigitFlag back to 0
         * **/
        if (numberDigitFlag == 0) {
            display_text_field.append(btnValue)
        } else {
            /**First clear and append the value of the button clicked
             * Note all the buttons call this function
             * i.e if button is clicked after a finished calculations(i.e immediatley after = button) it will first clear before it start
             * **/
            clearAll()
            display_text_field.append(btnValue)
        }
        //Append all in the operation
        all_operation_display.append(btnValue)
    }
}

