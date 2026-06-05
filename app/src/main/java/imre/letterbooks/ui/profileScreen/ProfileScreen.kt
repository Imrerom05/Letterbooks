package imre.letterbooks.ui.profileScreen

import NavBar
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
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
import coil.compose.AsyncImage
import imre.letterbooks.data.modul.BookSugestion

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = viewModel()
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
            // 🌑 Background gradient
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

            // 🔵 Blue glow
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

            // 🟣 Purple glow
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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(24.dp))

                // 👤 Avatar
                Surface(
                    modifier = Modifier.size(110.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = uiState.user?.username
                                ?.firstOrNull()
                                ?.uppercase()
                                ?: "",
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = uiState.user?.username ?: "No Connection",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(20.dp))

                FavoriteBooksSection(
                    bookSugestions = uiState.favoriteBookSugestions,
                    onBookClick = {
                        // TODO Navigate to book details
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        // TODO Edit profile
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Edit Profile")
                }

                OutlinedButton(
                    onClick = {
                        viewModel.logout()
                        navController.navigate("loginScreen") {
                            popUpTo("profileScreen") {
                                inclusive = true
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Logout")
                }
            }
        }
    }
}


@Composable
fun BookHolder(
    bookSugestion: BookSugestion?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .aspectRatio(0.7f)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
        )
    ) {

        if (bookSugestion != null) {

            val imageUrl = bookSugestion.coverUrl
                ?.replace("http://", "https://")

            AsyncImage(
                model = imageUrl,
                contentDescription = bookSugestion.title,
                modifier = Modifier.fillMaxSize()
            )

        } else {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add favorite book",
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}


@Composable
fun FavoriteBooksSection(
    bookSugestions: List<BookSugestion?>,
    onBookClick: (BookSugestion?) -> Unit
) {

    Column {
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            repeat(4) { index ->

                BookHolder(
                    bookSugestion = bookSugestions.getOrNull(index),
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onBookClick(bookSugestions.getOrNull(index))
                    }
                )
            }
        }
    }
}