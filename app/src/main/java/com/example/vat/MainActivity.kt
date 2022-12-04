package com.example.vat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.example.vat.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root //reference layout file
        setContentView(view)  //link layout

        binding.button.setOnClickListener { calculateVat()
        }//end setOnClickListener

    }//end onCreate

    private fun calculateVat() {

        //EditText values
        val stringInTextField = binding.editTxtCost.text.toString()
        val cost = stringInTextField.toDouble()

        //RadioGroup(selecting ID)
        val selectedId = binding.radioGroup.checkedRadioButtonId
        //Selecting ID for get value
        val vatPercentage = when(selectedId){
            R.id.tenOption -> 0.10
            R.id.fifteenOption -> 0.15
            else -> 0.20
        }

        //calculate vat and total
        var vat = vatPercentage * cost
        var total = vat + cost

        //Switch
        val roundUp = binding.Switch.isChecked
        //Switch (true or false)
        if(roundUp){
            total = kotlin.math.ceil(total)
        }

        //format
        val formattedVat = NumberFormat.getCurrencyInstance().format(total) //ربط المجموع مع العملة
        binding.result.text = getString(R.string.Vat_Amount, formattedVat)
    }
}