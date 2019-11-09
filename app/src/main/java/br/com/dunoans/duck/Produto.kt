package br.com.dunoans.duck

import java.io.Serializable

class Produto: Serializable{

    var id: Long = 0
    var nome: String = ""
    var quantidade: Int = 0
    var valor: Double = 0.00
    var descricao: String = ""
    var foto: String = ""

    override fun toString(): String {
        return "Produto(nome='$nome')"
    }
}