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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ExploreScreen(
    navController: NavController
) {

    val genres = listOf(
        "Fantasy",
        "Sci-Fi",
        "Romance",
        "Mystery",
        "History",
        "Adventure"
    )

    val trendingBooks = listOf(
        "The Silent Pages",
        "Beyond the Horizon",
        "Letters from Tomorrow",
        "The Forgotten Realm"
    )

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
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text("Search books, authors...")
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = null
                            )
                        },
                        singleLine = true
                    )
                }

                // Genres
                item {

                    Text(
                        text = "Genres",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        items(genres) { genre ->

                            AssistChip(
                                onClick = { },
                                label = {
                                    Text(genre)
                                }
                            )
                        }
                    }
                }

                // Featured book
                item {

                    Text(
                        text = "Featured",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.extraLarge,
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF121826)
                                .copy(alpha = 0.92f)
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(20.dp)
                        ) {

                            Text(
                                text = "Book of the Week",
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.labelLarge
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "The Silent Pages",
                                style = MaterialTheme.typography.headlineSmall
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "A mysterious story hidden between forgotten letters.",
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                // Trending section
                item {
                    Text(
                        text = "Trending Books",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                items(trendingBooks) { book ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF121826)
                                .copy(alpha = 0.92f)
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(18.dp)
                        ) {

                            Text(
                                text = book,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "Popular among Letterbooks readers",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(90.dp))
                }
            }
        }
    }
}