package br.com.dunoans.duck

import androidx.room.Room

object DatabaseManager {

    private var dbinstance: DuckDatabase

    init {
        var context = DuckApplication.getInstance().applicationContext

        dbinstance = Room.databaseBuilder(
            context,
            DuckDatabase::class.java,
            "duck.sqlite"
        ).build()
    }

    fun getProdutoDAO(): ProdutoDAO {
        return dbinstance.produtoDao()
    }
}