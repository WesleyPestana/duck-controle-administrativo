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

        var lembrar = Prefs.getBoolean("checkLembrar")

        if (lembrar) {
            var lembrarNome = Prefs.getString("lembrarNome")
            var lembrarSenha = Prefs.getString("lembrarSenha")

            campo_usuario.setText(lembrarNome)
            campo_senha.setText(lembrarSenha)
            check_login.isChecked = lembrar
        }
    }

    fun onClickLogin() {
        val valorUsuario = campo_usuario.text.toString()
        val valorSenha = campo_senha.text.toString()

        Prefs.setBoolean("checkLembrar", check_login.isChecked)

        if (check_login.isChecked) {
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
