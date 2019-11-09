package br.com.dunoans.duck

import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.net.URL

object ProdutoService {

    var host = "https://wesleypestana.pythonanywhere.com"
    var TAG = "WS_DUCKApp"

    fun getProdutos(context: Context): List<Produto> {
        /*
        val produtos = mutableListOf<Produto>()

        var url = "$host/produtos"
        var json = HttpHelper.get(url)
        Log.d(TAG, json)

        return parseJson(json)
        */

        return DatabaseManager.getProdutoDAO().findAll()
    }

    fun save(produto: Produto): Response {
        // HttpHelper.post("$host/produtos", GsonBuilder().create().toJson(produto))
        DatabaseManager.getProdutoDAO().insert(produto)

        return Response("OK", "OK")
    }

    fun delete(produto: Produto): Response {
        /*Log.d(TAG, protudo.id.toString())
        val url = "$host/protudos/${produto.id}"
        val json = HttpHelper.delete(url)
        Log.d(TAG, json)
        return parserJson(json)*/

        DatabaseManager.getProdutoDAO().delete(produto)

        return Response("OK", "OK")
    }

    inline fun <reified T> parseJson(json: String): T {
        val type = object: TypeToken<T>(){}.type

        return Gson().fromJson<T>(json, type)
    }
}