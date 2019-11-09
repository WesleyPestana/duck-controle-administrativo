package br.com.dunoans.duck

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Produto::class), version = 1)
abstract class DuckDatabase : RoomDatabase() {

    abstract fun produtoDao(): ProdutoDAO
}