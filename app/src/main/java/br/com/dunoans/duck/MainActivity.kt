package br.com.dunoans.duck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.login.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)


        botao_login.setOnClickListener {onClickLogin()}
    }

    fun onClickLogin() {
        val valorUsuario = campo_usuario.text.toString()
        val valorSenha = campo_senha.text.toString()

        if (valorUsuario == "aluno" && valorSenha == "impacta") {
            val intent = Intent(this, TelaInicialActivity::class.java)
            startActivity(intent)
        }
        else {
            Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show()
        }

        /*
        val intent = Intent(this, TelaInicialActivity::class.java)
        startActivity(intent)*/
    }
}
