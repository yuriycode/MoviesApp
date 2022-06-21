package com.yuriycode.moviesapp.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.yuriycode.moviesapp.MainViewModel

@Composable
fun DetailsScreen(navController:NavController, viewModel:MainViewModel, itemId: String) {
    Text("Details Screen: item id ${itemId}")


}