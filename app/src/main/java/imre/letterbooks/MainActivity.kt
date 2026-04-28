package imre.letterbooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import imre.letterbooks.ui.theme.LetterbooksTheme
import imre.letterbooks.ui.loginScreen.LoginScreen
import imre.letterbooks.ui.registerScreen.RegisterScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LetterbooksTheme {
                Navigation()
            }
        }
    }
}


@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "loginScreen") {

        composable(route = "loginScreen") {
            LoginScreen(navController = navController)
        }

        composable(route = "registerScreen") {
            RegisterScreen(navController = navController)
        }

//        composable(
//            route = "pointScreen/{lat}/{lon}",
//            arguments = listOf(
//                navArgument("lat") { type = NavType.StringType },
//                navArgument("lon") { type = NavType.StringType }
//            )
//        ) { backStackEntry ->
//            val lat = backStackEntry.arguments?.getString("lat")?.toDouble() ?: 0.0
//            val lon = backStackEntry.arguments?.getString("lon")?.toDouble() ?: 0.0
//            PointScreen(lat = lat, lon = lon, navController = navController)
//        }
    }
}



