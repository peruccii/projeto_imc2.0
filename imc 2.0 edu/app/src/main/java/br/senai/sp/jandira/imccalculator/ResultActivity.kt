package br.senai.sp.jandira.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.senai.sp.jandira.imccalculator.databinding.ActivityBmiBinding
import br.senai.sp.jandira.imccalculator.databinding.ActivityResultBinding
import br.senai.sp.jandira.imccalculator.utils.getBmi
import br.senai.sp.jandira.imccalculator.utils.getStatusBmi

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        //Recuperar os valores que estão na Intent
        val weight = intent.getDoubleExtra("weight", 0.0)
        val height = intent.getDoubleExtra("height", 0.0)
        val bmi = getBmi(weight, height)

        binding.textViewResult.text = String.format("%.2f", bmi)
        binding.textViewStatus.text = getStatusBmi(bmi, this)

    }
}