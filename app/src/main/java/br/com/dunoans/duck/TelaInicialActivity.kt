package br.com.dunoans.duck

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var produtos = listOf<Produto>()

    private var REQUEST_CADASTRO = 1
    private var REQUEST_REMOVE = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        val args: Bundle? = intent.extras

        val nome = args?.getString("nome")

        val numero = intent.getIntExtra("nome", 0)

        //Toast.makeText(context, "Parâmetro: $nome", Toast.LENGTH_LONG).show()
        //Toast.makeText(context, "Numero: $numero", Toast.LENGTH_LONG).show()


        // Colocar a toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Produtos"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()

        // Configurar cardview
        recyclerProdutos?.layoutManager = LinearLayoutManager(context)
        recyclerProdutos?.itemAnimator = DefaultItemAnimator()
        recyclerProdutos?.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        // recuperar os produtos
        taskProdutos()
    }

    fun taskProdutos() {
        // chamada para a API
        Thread {
            this.produtos = ProdutoService.getProdutos(context)

            // atualizar lista
            runOnUiThread {
                recyclerProdutos?.adapter = ProdutoAdapter(produtos) {onClickProduto(it)}
            }

        }.start()
    }

    fun onClickProduto(produto: Produto) {
        Toast.makeText(context, "Clicou produto ${produto.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, ProdutoActivity::class.java)
        intent.putExtra("produto", produto)
        startActivityForResult(intent, REQUEST_REMOVE)
    }

    private fun configuraMenuLateral() {
        var toogle = ActionBarDrawerToggle(this, layoutMenuLateral, toolbar, R.string.open, R.string.close)

        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()

        menu_lateral.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                // ação enquanto está digitando
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // ação  quando terminou de buscar e enviou
                return false
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        if (id == R.id.action_buscar) {
            Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_config) {
            onClickConfig()
        } else if (id == R.id.action_sair) {
            onClickSair()
        } else if (id == R.id.action_adicionar) {
            novoProduto()
        } else if (id == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
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
        val intent = Intent(this, TelaInicialActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun novoProduto() {
        val intent = Intent(this, NovoProdutoActivity::class.java)
        startActivityForResult(intent, REQUEST_CADASTRO)
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

    // esperar o retorno do cadastro da disciplina
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CADASTRO || requestCode == REQUEST_REMOVE ) {
            // atualizar lista de produtos
            taskProdutos()
        }
    }

}