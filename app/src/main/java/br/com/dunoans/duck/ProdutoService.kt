package br.com.dunoans.duck

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.net.URL

object ProdutoService {

    var host = "https://wesleypestana.pythonanywhere.com"
    var TAG = "WS_DUCKApp"

    fun getProdutos(context: Context): List<Produto> {

        val produtos = mutableListOf<Produto>()

        var url = "$host/produtos"
        var json = HttpHelper.get(url)

        Log.d(TAG, json)

        return parseJson(json)
    }

    fun save(produto: Produto) {
        HttpHelper.post("$host/produtos", GsonBuilder().create().toJson(produto))
    }

    inline fun <reified T> parseJson(json: String): T {
        val type = object: TypeToken<T>(){}.type

        return Gson().fromJson<T>(json, type)
    }
}