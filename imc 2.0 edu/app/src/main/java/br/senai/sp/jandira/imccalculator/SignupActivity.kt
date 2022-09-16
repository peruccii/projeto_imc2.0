package br.senai.sp.jandira.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.senai.sp.jandira.imccalculator.model.User

class SignupActivity : AppCompatActivity() {

    lateinit var editName: EditText
    lateinit var editEmail: EditText
    lateinit var editPassword: EditText
    lateinit var editWeight: EditText
    lateinit var editHeight: EditText
    lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar!!.hide()

        editName = findViewById(R.id.editTextName)
        editEmail = findViewById(R.id.editTextEmail)
        editPassword = findViewById(R.id.editTextPassword)
        editWeight = findViewById(R.id.editTextWeight)
        editHeight = findViewById(R.id.editTextHeight)
        buttonSave = findViewById(R.id.buttonUserCreate)

        buttonSave.setOnClickListener {
            saveUser()
        }
    }

    private fun saveUser() {

        val user = User()
        user.id = 1
        user.name = editName.text.toString()
        user.email = editEmail.text.toString()
        user.height = editHeight.text.toString().toDouble()
        user.weight = editWeight.text.toString().toDouble()
        user.password = editPassword.text.toString()

        val dados = getSharedPreferences("dados", MODE_PRIVATE)

        //Criar um editor para o arquivo
        val editor = dados.edit()

        //Inserir os dados no arquivo
        editor.putInt("id", user.id)
        editor.putString("name", user.name)
        editor.putString("email", user.email)
        editor.putString("password", user.password)
        editor.putFloat("weight", user.weight.toFloat())
        editor.putFloat("height", user.height.toFloat())

        if (editor.commit()){
            Toast.makeText(this, "Usuário gravado com sucesso", Toast.LENGTH_SHORT).show()
            finish() //Fecha a activity e volta para anterior
        } else{
            Toast.makeText(this, "Ocorreu um erro na gravação", Toast.LENGTH_SHORT).show()
        }

    }
}