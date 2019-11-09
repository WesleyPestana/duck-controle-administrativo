package br.com.dunoans.duck

import android.content.SharedPreferences

object Prefs {

    private fun prefs(): SharedPreferences {
        val context = DuckApplication.getInstance().applicationContext

        return context.getSharedPreferences("Duck", 0)
    }

    fun setBoolean(flag: String, valor: Boolean) {
        prefs().edit().putBoolean(flag, valor).apply()
    }

    fun getBoolean(flag: String): Boolean {
        return prefs().getBoolean(flag, false)
    }

    fun setString(flag: String, valor: String) {
        prefs().edit().putString(flag, valor).apply()
    }

    fun getString(flag: String): String? {
        return prefs().getString(flag, "")
    }
}