package com.example.myapp.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import com.example.myapp.viewmodel.ProblemaViewModel

@SuppressLint("MissingPermission")
@Composable
fun ThirdScreen(navController: NavController, severity: String, viewModel: ProblemaViewModel) {
    val context = LocalContext.current
    var marker by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    var description by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        Configuration.getInstance().load(
            context,
            context.getSharedPreferences("osm_prefs", Context.MODE_PRIVATE)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0F2F7))
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TitleSection()
        MapSection { latitude, longitude ->
            marker = Pair(latitude, longitude)
        }
        DescriptionSection(description) { description = it }
        ButtonSection(navController, marker, description, severity, viewModel, context)
    }
}

@Composable
fun TitleSection() {
    Text(
        text = "Marque a localização do problema",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF0D47A1),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, top = 50.dp)
    )
}

@Composable
fun MapSection(onLocationSelected: (Double, Double) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
    ) {
        AndroidView(factory = { context ->
            MapView(context).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                setMultiTouchControls(true)
                controller.setZoom(18.5) //
                controller.setCenter(GeoPoint(-28.2628, -52.4064)) // Passo Fundo - RS

                setOnTouchListener { _, _ ->
                    val geo = projection.fromPixels(width / 2, height / 2) as GeoPoint
                    overlays.clear()
                    val newMarker = Marker(this)
                    newMarker.position = geo
                    newMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                    newMarker.title = "Local do problema"
                    overlays.add(newMarker)
                    onLocationSelected(geo.latitude, geo.longitude)
                    invalidate()
                    false
                }
            }
        })
    }
}

@Composable
fun DescriptionSection(description: String, onDescriptionChanged: (String) -> Unit) {
    OutlinedTextField(
        value = description,
        onValueChange = onDescriptionChanged,
        label = { Text("Descrição do problema") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF1976D2),
            unfocusedBorderColor = Color.Gray,
            disabledBorderColor = Color.LightGray
        )
    )
}

@Composable
fun ButtonSection(
    navController: NavController,
    marker: Pair<Double, Double>?,
    description: String,
    severity: String,
    viewModel: ProblemaViewModel,
    context: Context
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF64B5F6)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(0.4f)
        ) {
            Text("Voltar", color = Color.Black)
        }
        Button(
            onClick = {
                if (marker != null && description.isNotBlank()) {
                    viewModel.addProblema(
                        latitude = marker.first,
                        longitude = marker.second,
                        descricao = description,
                        gravidade = severity
                    )
                    navController.navigate("confirmationScreen")
                } else {
                    Toast.makeText(context, "Preencha todos os dados", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(0.4f)
        ) {
            Text("Salvar", color = Color.Black)
        }
    }
}