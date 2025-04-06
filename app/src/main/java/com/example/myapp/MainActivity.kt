package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapp.ui.screens.ConfirmationScreen
import com.example.myapp.ui.screens.MainScreen
import com.example.myapp.ui.screens.SecondScreen
import com.example.myapp.ui.screens.ThirdScreen
import com.example.myapp.viewmodel.ProblemaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val viewModel: ProblemaViewModel = viewModel()

    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") { MainScreen(navController) }
        composable("secondScreen") { SecondScreen(navController) }
        composable("mapScreen/{severity}") { backStackEntry ->
            val severity = backStackEntry.arguments?.getString("severity") ?: ""
            ThirdScreen(navController, severity, viewModel)
        }
        composable("confirmationScreen") {
            ConfirmationScreen(viewModel)
        }
    }
}