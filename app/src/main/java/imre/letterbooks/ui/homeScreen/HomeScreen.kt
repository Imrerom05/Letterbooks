package imre.letterbooks.ui.homeScreen

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import imre.letterbooks.NavBar

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
) {
    Scaffold(bottomBar = { NavBar(navController = navController) }) {
        it.calculateBottomPadding()
    }
}
