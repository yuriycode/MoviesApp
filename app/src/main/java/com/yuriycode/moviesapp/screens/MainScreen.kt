package com.yuriycode.moviesapp.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.yuriycode.moviesapp.MainViewModel
import com.yuriycode.moviesapp.data.models.Movies
import com.yuriycode.moviesapp.navigation.Screens
import com.yuriycode.moviesapp.utils.Constants

@Composable
fun MainScreen(navController:NavController, viewModel: MainViewModel) {
    val allMovies = viewModel.allMovies.observeAsState(listOf()).value
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(20.dp)
        ){
            items(allMovies.take(300)) { item ->
                MovieItem(item = item, navController = navController)
            }
        }
    }
}

@Composable
fun MovieItem(item: Movies, navController:NavController) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(top = 8.dp)
            .clickable {
                navController.navigate(Screens.Details.route + "/${item.id}")
            }
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ){
            Image(
                painter = rememberImagePainter(item.image.medium),
                contentDescription =null,
                modifier = Modifier.size(120.dp)
            )
            Column {
                Text(
                    text = item.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            Row {
                 Text(
                        text = " Рейтинг:  ",
                        fontWeight = FontWeight.Bold
                    )
                     Text(text = item.rating.average.toString())
                }
            Row {
                    Text(
                        text = " Жанр:  ",
                        fontWeight = FontWeight.Bold
                    )
                    item.genres.take(2).forEach { Text(text = " $it ") }
                }
            Row {
                    Text(
                        text = " Премьера:  ",
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = item.premiered)
                }

            }
        }
    }
}