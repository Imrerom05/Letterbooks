package imre.letterbooks.ui.bookScreen

import ExploreViewModel
import NavBar
import SearchFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import imre.letterbooks.data.modul.Author
import imre.letterbooks.data.modul.Series
import imre.letterbooks.data.modul.Work

@Composable
fun ExploreScreen(
    navController: NavController,
    viewModel: ExploreViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

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
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }

                // Search bar
                item {

                    OutlinedTextField(
                        value = uiState.searchQuery,
                        onValueChange = viewModel::onSearchQueryChanged,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search"
                            )
                        },
                        placeholder = {
                            Text("Search books, authors or series...")
                        },
                        shape = RoundedCornerShape(16.dp)
                    )
                }

                // Filters
                item {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        FilterChip(
                            selected = uiState.selectedFilter == SearchFilter.ALL,
                            onClick = {
                                viewModel.selectFilter(SearchFilter.ALL)
                            },
                            label = {
                                Text("All")
                            }
                        )

                        FilterChip(
                            selected = uiState.selectedFilter == SearchFilter.WORKS,
                            onClick = {
                                viewModel.selectFilter(SearchFilter.WORKS)
                            },
                            label = {
                                Text("Books")
                            }
                        )

                        FilterChip(
                            selected = uiState.selectedFilter == SearchFilter.AUTHORS,
                            onClick = {
                                viewModel.selectFilter(SearchFilter.AUTHORS)
                            },
                            label = {
                                Text("Authors")
                            }
                        )

                        FilterChip(
                            selected = uiState.selectedFilter == SearchFilter.SERIES,
                            onClick = {
                                viewModel.selectFilter(SearchFilter.SERIES)
                            },
                            label = {
                                Text("Series")
                            }
                        )
                    }
                }

                // Loading
                if (uiState.isLoading) {

                    item {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(30.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                // Error
                uiState.errorMessage?.let { error ->

                    item {

                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

                // Results
                if (!uiState.isLoading) {

                    if (
                        uiState.works.isEmpty() &&
                        uiState.authors.isEmpty() &&
                        uiState.series.isEmpty() &&
                        uiState.searchQuery.isNotBlank()
                    ) {

                        item {

                            Text(
                                text = "No results found",
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }

                    // Books
                    if (
                        uiState.selectedFilter == SearchFilter.ALL ||
                        uiState.selectedFilter == SearchFilter.WORKS
                    ) {

                        if (uiState.works.isNotEmpty()) {

                            item {
                                Text(
                                    text = "Books",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }

                            items(uiState.works) { work ->

                                WorkSearchItem(work)
                            }
                        }
                    }

                    // Authors
                    if (
                        uiState.selectedFilter == SearchFilter.ALL ||
                        uiState.selectedFilter == SearchFilter.AUTHORS
                    ) {

                        if (uiState.authors.isNotEmpty()) {

                            item {
                                Text(
                                    text = "Authors",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }

                            items(uiState.authors) { author ->

                                AuthorSearchItem(author)
                            }
                        }
                    }

                    // Series
                    if (
                        uiState.selectedFilter == SearchFilter.ALL ||
                        uiState.selectedFilter == SearchFilter.SERIES
                    ) {

                        if (uiState.series.isNotEmpty()) {

                            item {
                                Text(
                                    text = "Series",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }

                            items(uiState.series) { series ->

                                SeriesSearchItem(series)
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}



@Composable
fun WorkSearchItem(
    work: Work
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White.copy(alpha = 0.05f),
                RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {

        Text(
            text = work.title,
            style = MaterialTheme.typography.titleMedium
        )

        work.originalYear?.let {
            Text(
                text = it.toString(),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun AuthorSearchItem(
    author: Author
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White.copy(alpha = 0.05f),
                RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {

        Text(
            text = author.name,
            style = MaterialTheme.typography.titleMedium
        )

        author.birth?.let {
            Text(
                text = "Born $it",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}


@Composable
fun SeriesSearchItem(
    series: Series
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White.copy(alpha = 0.05f),
                RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {

        Text(
            text = series.name,
            style = MaterialTheme.typography.titleMedium
        )

        series.description?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2
            )
        }
    }
}