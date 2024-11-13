package com.sample.chaitanyasampleapp.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.sample.chaitanyasampleapp.R
import com.sample.chaitanyasampleapp.data.model.Article
import com.sample.chaitanyasampleapp.presentation.AppBar
import com.sample.chaitanyasampleapp.presentation.viewmodel.MainViewModel

@Composable
fun ListScreen(viewModel: MainViewModel = hiltViewModel(), navController: NavHostController) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        if (state.articles.isEmpty()) viewModel.fetchListData()
    }
    Scaffold(topBar = {
        AppBar(
            title = "List Data",
            icon = Icons.Default.Home
        ) { }
    }) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                when {
                    state.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    state.error != null -> Text(
                        text = state.error!!,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )

                    else -> {
                        ListScreenData(articles = state.articles, navController,viewModel)
                    }
                }
            }
        }
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreenData(
    articles: List<Article>,
    navController: NavHostController,
    viewModel: MainViewModel
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        items(articles) { article ->
            ListItem(article = article,
                onClick = {
                    viewModel.setSelectedData(article)
                    navController.navigate("detail")
                })
        }
    }
}


@Composable
fun ListItem(article: Article, onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .background(Color.White)
                    .clickable { onClick() }
                , horizontalArrangement = Arrangement.Center
            ) {
                SetImage(article)
                Column(modifier = Modifier.padding(5.dp).weight(1f)) {
                    Text(
                        text = article.title ?: "No title",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = article.description ?: "No description",
                        modifier = Modifier.padding(5.dp),
                        color = Color.Gray
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.forward_arrow),
                    contentDescription = "Forward",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(24.dp),
                )


            }
        }
    }
}

@Composable
private fun SetImage(article: Article) {
    article.urlToImage?.let { imageUrl ->
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .placeholder(R.drawable.image_error)
                .error(R.drawable.image_error)
                .build()
        )

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(4.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(10))
        )


    }
}






