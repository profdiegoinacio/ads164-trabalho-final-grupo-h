package com.example.myapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapp.viewmodel.ProblemaViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ConfirmationScreen(
    viewModel: ProblemaViewModel = viewModel(),
    navController: NavController
) {
    val ultimoProblema by viewModel.ultimoProblemaReportado.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Seu reporte está em análise!",
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))

            ultimoProblema?.let { problema ->
                Text("Gravidade: ${problema.gravidade}")
                Text("Descrição: ${problema.descricao}")
                Text("Localização: ${problema.latitude}, ${problema.longitude}")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = {
                navController.navigate("mainScreen") {
                    popUpTo("mainScreen") { inclusive = true } 
                }
            }) {
                Text("Voltar para o Início")
            }
        }
    }
}