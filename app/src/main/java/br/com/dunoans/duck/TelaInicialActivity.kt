package br.com.dunoans.duck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Início"

        botao_despesas.setOnClickListener{onClickDespesas()}
        botao_fornecedores.setOnClickListener{onClickFornecedores()}
        botao_produtos.setOnClickListener{onClickProdutos()}

        configuraMenuLateral()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        if (id == R.id.action_adicionar) {
            onClickProdutos()
        } else if (id == R.id.action_config) {
            onClickConfig()
        } else if (id == R.id.action_sair) {
            onClickSair()
        }

        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_despesas -> {
                onClickDespesas()
            }
            R.id.nav_fornecedores -> {
                onClickFornecedores()
            }
            R.id.nav_produtos -> {
                onClickProdutos()
            }
            R.id.nav_sair -> {
                onClickSair()
            }
        }

        layoutMenuLateral.closeDrawer(GravityCompat.START)

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

    fun onClickProdutos() {
        val intent = Intent(this, ProdutoActivity::class.java)
        startActivity(intent)
    }

    fun onClickConfig() {
        val intent = Intent(this, ConfigActivity::class.java)
        startActivity(intent)
    }

    fun onClickSair() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun configuraMenuLateral() {
        var toogle = ActionBarDrawerToggle(this, layoutMenuLateral, toolbar, R.string.open, R.string.close)

        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()

        menu_lateral.setNavigationItemSelectedListener(this)
    }
}
