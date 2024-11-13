package com.sample.chaitanyasampleapp.presentation.ui
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.sample.chaitanyasampleapp.data.model.Article
import com.sample.chaitanyasampleapp.presentation.AppBar
/** Prepare the compose List Details screen */
@Composable
fun ListDetailScreen(selectedModel: Article, navController: NavHostController) {
    Scaffold(topBar = {
        AppBar(
            title = "List details",
            icon = Icons.Default.ArrowBack
        ) {
            navController.navigateUp()
        }
    })  { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start

            ) {
                selectedModel.urlToImage?.let { imageUrl ->
                    Image(
                        painter = rememberAsyncImagePainter(imageUrl),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = selectedModel.title ?: "No title", fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start))
                Text(text = "By ${selectedModel.author}", color = Color.Gray,
                    modifier = Modifier.align(Alignment.Start))
               //Spacer(modifier = Modifier.height(8.dp))
                Text(text = selectedModel.description ?: "No description",
                    modifier = Modifier.align(Alignment.Start))
            }
        }

    }
}
