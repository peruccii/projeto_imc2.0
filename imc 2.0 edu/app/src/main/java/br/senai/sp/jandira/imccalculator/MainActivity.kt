package br.senai.sp.jandira.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import br.senai.sp.jandira.imccalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.textSignup.setOnClickListener{
            val openSignupActivity = Intent(this, SignupActivity::class.java)
            startActivity(openSignupActivity)
        }

        binding.buttonLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {


        if (validar()){
            val email = binding.editTextEmail.text.toString()
            val pass = binding.editTextPassword.text.toString()

            //abrir o sharedPreferences
            val dados = getSharedPreferences("dados", MODE_PRIVATE)

            val emailSp = dados.getString("email", "E-mail não encontrado")
            val passSp = dados.getString("password", "")

            //Verificar se os dados de autenticação estão corretos
            if (email == emailSp && pass == passSp){
                val openBmi = Intent(this, BmiActivity::class.java)
                startActivity(openBmi)
            } else{
                Toast.makeText(this, "Authentication failed!", Toast.LENGTH_SHORT).show()
            }
        } 

    }

    private fun validar(): Boolean {
        if (binding.editTextEmail.text.isEmpty()){
            binding.editTextEmail.error = "E-mail is required"
            return false
        }

        if (binding.editTextPassword.text.isEmpty()){
            binding.editTextPassword.error = "Password is required"
            return false
        }

        return true
    }
}