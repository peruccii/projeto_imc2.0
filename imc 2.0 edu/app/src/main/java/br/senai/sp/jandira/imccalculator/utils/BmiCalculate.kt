package br.senai.sp.jandira.imccalculator.utils

import android.content.Context
import br.senai.sp.jandira.imccalculator.R
import java.security.AccessControlContext
import kotlin.math.pow

fun getBmi(weight: Double, height: Double): Double{
    return weight / height.pow(2)
}

fun getStatusBmi(bmi: Double, context: Context): String{

    if(bmi <= 18.5){
        return context.getString(R.string.under)
    } else if(bmi > 18.5 && bmi < 25.0){
        return context.getString(R.string.ideal)
    } else if (bmi >= 25.0 && bmi < 30.0){
        return context.getString(R.string.over)
    } else if(bmi >= 30.0 && bmi < 35.0){
        return context.getString(R.string.one)
    } else if(bmi >= 35.0 && bmi < 40.0){
        return context.getString(R.string.two)
    } else{
        return context.getString(R.string.three)
    }
}