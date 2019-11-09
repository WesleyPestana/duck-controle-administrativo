package br.com.dunoans.duck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_produto.*
import kotlinx.android.synthetic.main.toolbar.*

class ProdutoActivity : AppCompatActivity() {

    var produto: Produto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto)

        produto = intent.getSerializableExtra("produto") as Produto

        setSupportActionBar(toolbar)

        supportActionBar?.title = produto?.nome
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        nomeProduto.text = produto?.nome
        Picasso.with(this).load(produto?.foto).fit().into(imagemProduto,
            object : com.squareup.picasso.Callback {

                override fun onSuccess() {}

                override fun onError() {}
            })
    }
}
