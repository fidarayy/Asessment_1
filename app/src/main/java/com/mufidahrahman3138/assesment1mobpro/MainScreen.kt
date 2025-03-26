package com.mufidahrahman3138.assesment1mobpro.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mufidahrahman3138.assesment1mobpro.navigation.Screen
import com.mufidahrahman3138.assesment1mobpro.ui.theme.Assesment1MobproTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Hitung Laundry") },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.About.route) }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = "Tentang Aplikasi"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LaundryScreenContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun LaundryScreenContent(modifier: Modifier = Modifier) {
    var berat by remember { mutableStateOf("") }
    var hargaTotal by remember { mutableStateOf(0) }
    val hargaPerKg = 5000

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Masukkan berat cucian (kg):")
        OutlinedTextField(
            value = berat,
            onValueChange = { berat = it },
            label = { Text("Berat (kg)") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            hargaTotal = if (berat.isNotEmpty()) berat.toInt() * hargaPerKg else 0
        }) {
            Text("Hitung Harga")
        }
        if (hargaTotal > 0) {
            Text("Total Harga: Rp $hargaTotal", style = MaterialTheme.typography.headlineSmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Assesment1MobproTheme {
        MainScreen(rememberNavController())
    }
}
