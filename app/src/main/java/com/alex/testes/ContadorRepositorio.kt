package com.alex.testes

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "contador")

/** Guarda o valor do contador no aparelho, para ele continuar depois de fechar o app. */
class ContadorRepositorio(context: Context) {
    private val dataStore = context.applicationContext.dataStore

    val valor: Flow<Int> = dataStore.data.map { it[CHAVE_VALOR] ?: 0 }

    suspend fun salvar(valor: Int) {
        dataStore.edit { it[CHAVE_VALOR] = valor }
    }

    private companion object {
        val CHAVE_VALOR = intPreferencesKey("valor")
    }
}
