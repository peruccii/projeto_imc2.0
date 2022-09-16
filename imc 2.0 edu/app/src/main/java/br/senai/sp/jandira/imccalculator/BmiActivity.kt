package br.senai.sp.jandira.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.senai.sp.jandira.imccalculator.databinding.ActivityBmiBinding
import br.senai.sp.jandira.imccalculator.databinding.ActivityMainBinding
import br.senai.sp.jandira.imccalculator.model.User
import kotlin.math.pow

class BmiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBmiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        loadProfile()

        binding.calculateButton.setOnClickListener {
            bmiCalculate()
        }
    }

    private fun loadProfile() {
        val dados = getSharedPreferences("dados", MODE_PRIVATE)

        binding.textViewEmail.text = dados.getString("email", "")
        binding.textViewHeight.text = "${resources.getText(R.string.height)} ${dados.getFloat("height", 0.0f)}"
        binding.textViewWeight.text = "${resources.getText(R.string.weight)} ${dados.getFloat("weight", 0.0f)}"
        binding.textViewUsername.text = dados.getString("name", "")
    }

    private fun bmiCalculate() {
        val openResult = Intent(this, ResultActivity::class.java)
        val dados = getSharedPreferences("dados", MODE_PRIVATE)
        val user = User()
        val editor = dados.edit()

        //Enviar dados de uma activity para outra
        if(binding.weightInput.text.isEmpty()){
            val weight = dados.getFloat("weight", 0.0f)
            openResult.putExtra("weight", weight.toDouble())
        } else{
            user.weight = binding.weightInput.text.toString().toDouble()
            editor.putFloat("weight", user.weight.toFloat())
            openResult.putExtra("weight", binding.weightInput.text.toString().toDouble())
        }

        if (binding.heightInput.text.isEmpty()){
            val height = dados.getFloat("height", 0.0f)
            openResult.putExtra("height", height.toDouble())
        } else{
            user.height = binding.heightInput.text.toString().toDouble()
            editor.putFloat("height", user.height.toFloat())
            openResult.putExtra("height", binding.heightInput.text.toString().toDouble())
        }

        editor.commit()
        startActivity(openResult)
    }
}