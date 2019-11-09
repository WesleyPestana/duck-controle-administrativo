package br.com.dunoans.duck

import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.net.URL

object ProdutoService {

    var host = "https://wesleypestana.pythonanywhere.com"
    var TAG = "WS_DUCKApp"

    fun getProdutos (context: Context): List<Produto> {
        var produtos = ArrayList<Produto>()
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/produtos"
            val json = HttpHelper.get(url)
            produtos = parserJson(json)
            // salvar offline
            for (d in produtos) {
                saveOffline(d)
            }
            return produtos
        } else {
            val dao = DatabaseManager.getProdutoDAO()
            val produtos = dao.findAll()
            return produtos
        }

    }

    fun getProduto (context: Context, id: Long): Produto? {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/produtos/${id}"
            val json = HttpHelper.get(url)
            val produto = parserJson<Produto>(json)

            return produto
        } else {
            val dao = DatabaseManager.getProdutoDAO()
            val produto = dao.getById(id)
            return produto
        }

    }

    fun save(produto: Produto): Response {
        val json = HttpHelper.post("$host/produtos", produto.toJson())
        return parserJson(json)
    }

    fun saveOffline(produto: Produto) : Boolean {
        val dao = DatabaseManager.getProdutoDAO()

        if (! existeProduto(produto)) {
            dao.insert(produto)
        }

        return true

    }

    fun existeProduto(produto: Produto): Boolean {
        val dao = DatabaseManager.getProdutoDAO()
        return dao.getById(produto.id) != null
    }

    fun delete(produto: Produto): Response {
        if (AndroidUtils.isInternetDisponivel(DuckApplication.getInstance().applicationContext)) {
            val url = "$host/produtos/${produto.id}"
            val json = HttpHelper.delete(url)

            return parserJson(json)
        } else {
            val dao = DatabaseManager.getProdutoDAO()
            dao.delete(produto)
            return Response(status = "OK", msg = "Dados salvos localmente")
        }

    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object: TypeToken<T>(){}.type

        return Gson().fromJson<T>(json, type)
    }
}