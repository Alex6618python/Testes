package com.alex.testes

import org.junit.Assert.assertEquals
import org.junit.Test

class ContadorTest {
    @Test
    fun incrementarSomaUm() {
        assertEquals(1, Contador().incrementar().valor)
    }

    @Test
    fun decrementarNaoFicaNegativo() {
        assertEquals(0, Contador(0).decrementar().valor)
        assertEquals(2, Contador(3).decrementar().valor)
    }

    @Test
    fun zerarVoltaParaZero() {
        assertEquals(0, Contador(42).zerar().valor)
    }
}
