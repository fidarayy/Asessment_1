package com.mufidahrahman3138.assesment1mobpro.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mufidahrahman3138.assesment1mobpro.R
import com.mufidahrahman3138.assesment1mobpro.navigation.Screen
import com.mufidahrahman3138.assesment1mobpro.ui.theme.Assesment1MobproTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar( // Menggunakan CenterAlignedTopAppBar agar title di tengah
                title = {
                    Text(
                        text = "Laundry",
                        style = MaterialTheme.typography.headlineMedium // Membuat teks lebih besar
                    )
                },
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
    var jumlahSeprai by remember { mutableStateOf("") }
    var jumlahSelimut by remember { mutableStateOf("") }
    var hargaTotal by remember { mutableStateOf(0) }

    val hargaPerKg = 5000
    val hargaSeprai = 13000
    val hargaSelimut = 20000


    val imageList = listOf(
        R.drawable.harga_baju,
        R.drawable.harga_selimut,
        R.drawable.harga_seprai
    )
    var currentImageIndex by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = imageList[currentImageIndex]),
                contentDescription = "Laundry Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            // Tombol Geser Kiri/Kanan
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            ) {
                Button(onClick = {
                    if (currentImageIndex > 0) currentImageIndex--
                }) {
                    Text("⬅")
                }

                Button(onClick = {
                    if (currentImageIndex < imageList.size - 1) currentImageIndex++
                }) {
                    Text("➡")
                }
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Masukkan jumlah cucian per lembar:")

            OutlinedTextField(
                value = jumlahSeprai,
                onValueChange = { jumlahSeprai = it },
                label = { Text("Jumlah Seprai") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = jumlahSelimut,
                onValueChange = { jumlahSelimut = it },
                label = { Text("Jumlah Selimut") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
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

            Button(
                onClick = {
                    val beratCuci = berat.toIntOrNull() ?: 0
                    val sprei = jumlahSeprai.toIntOrNull() ?: 0
                    val selimut = jumlahSelimut.toIntOrNull() ?: 0

                    hargaTotal = (beratCuci * hargaPerKg) +
                            (sprei * hargaSeprai) +
                            (selimut * hargaSelimut)
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Hitung Harga")
            }

            if (hargaTotal > 0) {
                Text(
                    "Total Harga: Rp $hargaTotal",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
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
