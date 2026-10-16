package com.alex.testes

/** Lógica do contador, separada da UI para poder ser testada sem Android. */
data class Contador(val valor: Int = 0) {
    fun incrementar() = copy(valor = valor + 1)
    fun decrementar() = copy(valor = (valor - 1).coerceAtLeast(0))
    fun zerar() = copy(valor = 0)
}
