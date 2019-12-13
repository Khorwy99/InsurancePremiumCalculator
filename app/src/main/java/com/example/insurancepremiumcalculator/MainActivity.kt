package com.example.insurancepremiumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    lateinit var myData: PremiumModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        myData = ViewModelProviders.of(this).get(PremiumModel::class.java)

        display()

        calculate.setOnClickListener()
        {
            myData.premiumAmount = getPremium()
            display()
        }

        reset.setOnClickListener()
        {
            SpinnerAge.setSelection(0)
            radioGroupGender.clearCheck()
            smoker.setChecked(false)
            premium_value.setText("RM ")
            myData.premiumAmount = 0.0
        }


    }

    fun display()
    {
        if(myData.premiumAmount !=0.0)
        {
            premium_value.text= myData.premiumAmount.toString()
        }
    }

    fun getPremium() : Double{
        return when(SpinnerAge.selectedItemPosition)
        {
            0 -> 60.00
            1 -> 70.00 +
                    (if(male.isChecked) 50.00 else 0.00)+
                    (if(smoker.isChecked) 100.00 else 0.00)
            2 -> 90.00 +
                    (if(male.isChecked) 100.00 else 0.0) +
                    (if(smoker.isChecked) 150.00 else 0.0)
            3 -> 120.00 +
                    (if(male.isChecked) 150.00 else 0.0) +
                    (if(smoker.isChecked) 200.00 else 0.0)
            4 -> 150.00 +
                    (if(male.isChecked) 200.00 else 0.0) +
                    (if(smoker.isChecked) 250.00 else 0.0)
            else -> 150.00 +
                    (if(male.isChecked) 200.00 else 0.0) +
                    (if(smoker.isChecked) 300.00 else 0.0)

        }
    }
}
