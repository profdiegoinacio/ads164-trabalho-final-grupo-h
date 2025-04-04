package com.example.myapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapp.components.SeverityButton

class SecondViewModel : ViewModel() {
    fun navigateToMap(navController: NavController, severity: String) {
        navController.navigate("mapScreen/$severity")
    }
}

@Composable
fun SecondScreen(navController: NavController, viewModel: SecondViewModel = viewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE1F5FE))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Selecione a gravidade do problema", fontSize = 22.sp, color = Color.Black)

            Spacer(modifier = Modifier.height(32.dp))

            SeverityButton("Problema Grave", Color.Red) {
                viewModel.navigateToMap(
                    navController,
                    "grave"
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            SeverityButton("Problema Intermediário", Color(0xFFFFC107)) {
                viewModel.navigateToMap(
                    navController,
                    "intermediario"
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            SeverityButton("Problema Simples", Color(0xFF388E3C)) {
                viewModel.navigateToMap(
                    navController,
                    "simples"
                )
            }
        }
    }
}