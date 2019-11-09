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

        campo_usuario.setText(Prefs.getString("lembrarNome"))
        campo_senha.setText(Prefs.getString("lembrarSenha"))
        check_login.isChecked = Prefs.getBoolean("checkLembrar")
    }

    fun onClickLogin() {
        val valorUsuario = campo_usuario.text.toString()
        val valorSenha = campo_senha.text.toString()

        val valorCheck = check_login.isChecked
        Prefs.setBoolean("checkLembrar", valorCheck)

        if (valorCheck) {
            Prefs.setString("lembrarNome", valorUsuario)
            Prefs.setString("lembrarSenha", valorSenha)
        } else {
            Prefs.setString("lembrarNome", "")
            Prefs.setString("lembrarSenha", "")
        }

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
