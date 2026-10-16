package com.alex.testes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    TelaContador(modifier = Modifier.padding(padding))
                }
            }
        }
    }
}

@Composable
fun TelaContador(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val repositorio = remember { ContadorRepositorio(context) }
    val valor by repositorio.valor.collectAsState(initial = 0)
    val scope = rememberCoroutineScope()
    val contador = Contador(valor)
    val salvar: (Contador) -> Unit = { novo -> scope.launch { repositorio.salvar(novo.valor) } }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Contador", style = MaterialTheme.typography.headlineMedium)
        Text(
            text = contador.valor.toString(),
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(vertical = 24.dp),
        )
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedButton(onClick = { salvar(contador.decrementar()) }) { Text("−") }
            Button(onClick = { salvar(contador.incrementar()) }) { Text("+") }
        }
        OutlinedButton(
            onClick = { salvar(contador.zerar()) },
            modifier = Modifier.padding(top = 12.dp),
        ) { Text("Zerar") }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaContadorPreview() {
    MaterialTheme { TelaContador() }
}
