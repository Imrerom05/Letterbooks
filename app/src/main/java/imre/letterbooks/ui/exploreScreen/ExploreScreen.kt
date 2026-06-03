package imre.letterbooks.ui.exploreScreen

import NavBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import imre.letterbooks.ui.homeScreen.ExploreViewModel

@Composable
fun ExploreScreen(
    navController: NavController,
    viewModel: ExploreViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsState()


    Scaffold(
        bottomBar = {
            NavBar(navController = navController)
        },
        containerColor = Color.Transparent
    ) { paddingValues ->

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            // Background
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF0B0F17),
                                Color(0xFF0E1420),
                                Color(0xFF0A0C12)
                            )
                        )
                    )
            )

            // Blue glow
            Box(
                modifier = Modifier
                    .size(240.dp)
                    .offset((-80).dp, (-60).dp)
                    .background(
                        Color(0xFF4C7DFF).copy(alpha = 0.12f),
                        CircleShape
                    )
                    .blur(60.dp)
            )

            // Purple glow
            Box(
                modifier = Modifier
                    .size(220.dp)
                    .align(Alignment.BottomEnd)
                    .offset(60.dp, 80.dp)
                    .background(
                        Color(0xFFB04CFF).copy(alpha = 0.10f),
                        CircleShape
                    )
                    .blur(70.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }

                // Header
                item {

                    Text(
                        text = "Explore",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "Discover books and authors",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // Search
                item {
                    OutlinedTextField(
                        value = uiState.value.query,
                        onValueChange = {
                            viewModel.updateQuery(it)

                            if (uiState.value.query.length >= 2) {
                                viewModel.search(it)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text("Search books, authors...")
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = "Search field"
                            )
                        },
                        singleLine = true,
                        textStyle = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                }

                // Book items
                items(uiState.value.books) { book ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
//                            navController.navigate(
//                                "bookDetails/${book.id}"
//                            )
                        }
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = book.volumeInfo.title,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary
                            )

                            Text(
                                text = book.volumeInfo.authors.joinToString(),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )

                        }
                    }
                }
            }
        }
    }
}