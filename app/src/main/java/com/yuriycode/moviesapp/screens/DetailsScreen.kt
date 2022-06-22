package com.yuriycode.moviesapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.yuriycode.moviesapp.MainViewModel
import com.yuriycode.moviesapp.utils.HtmlText

@Composable
fun DetailsScreen(viewModel: MainViewModel, itemId: String) {
    val currentItem = viewModel.allMovies
        .observeAsState(listOf()).value
        .firstOrNull { it.id == itemId.toInt() }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp, horizontal = 8.dp)
    ) {
        LazyColumn{
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberImagePainter(currentItem?.image?.medium),
                        contentDescription = null,
                        modifier = Modifier.size(512.dp)
                    )
                    Text(
                        text = currentItem?.name ?: "",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 35.sp,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    Row (
                        modifier = Modifier.padding(top = 8.dp)
                    ){
                        Text(
                            text = "Рейтинг: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = currentItem?.rating?.average.toString(),
                            fontSize = 18.sp
                        )
                    }
                    Row(
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text(
                            text = "Жанр: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        currentItem?.genres?.take(2)?.forEach {
                            Text(
                                text = " [$it] ",
                                fontSize = 18.sp
                            )
                        }
                    }
                    HtmlText(
                        html = currentItem?.summary ?: "",
                        modifier = Modifier.padding(top = 15.dp)
                    )
                }
            }
        }
    }
}