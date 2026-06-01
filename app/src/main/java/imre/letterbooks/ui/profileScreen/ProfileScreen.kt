package imre.letterbooks.ui.profileScreen

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import imre.letterbooks.NavBar

@Composable
fun ProfileScreen (navController: NavController) {
    Scaffold (bottomBar = { NavBar(navController = navController) }) {
        it.calculateBottomPadding()
    }
}