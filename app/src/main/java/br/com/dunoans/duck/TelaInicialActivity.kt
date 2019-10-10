package br.com.dunoans.duck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_tela_inicial.*

class TelaInicialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        supportActionBar?.title = "Início"

        botao_despesas.setOnClickListener{onClickDespesas()}
        botao_fornecedores.setOnClickListener{onClickFornecedores()}
        botao_clientes.setOnClickListener{onClickClientes()}
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        if (id == R.id.action_adicionar) {
            onClickDespesas()
        } else if (id == R.id.action_config) {
            onClickConfig()
        } else if (id == R.id.action_sair) {
            finish()
        }

        return true
    }

    fun onClickDespesas() {
        val intent = Intent(this, DespesaActivity::class.java)
        startActivity(intent)
    }

    fun onClickFornecedores() {
        val intent = Intent(this, FornecedorActivity::class.java)
        startActivity(intent)
    }

    fun onClickClientes() {
        val intent = Intent(this, ClienteActivity::class.java)
        startActivity(intent)
    }

    fun onClickConfig() {
        val intent = Intent(this, ConfigActivity::class.java)
        startActivity(intent)
    }
}
