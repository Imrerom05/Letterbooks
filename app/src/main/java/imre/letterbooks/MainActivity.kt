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
import com.google.firebase.auth.FirebaseAuth
import imre.letterbooks.ui.exploreScreen.ExploreScreen
import imre.letterbooks.ui.homeScreen.HomeScreen
import imre.letterbooks.ui.theme.LetterbooksTheme
import imre.letterbooks.ui.loginScreen.LoginScreen
import imre.letterbooks.ui.profileScreen.ProfileScreen
import imre.letterbooks.ui.registerScreen.RegisterScreen


class MainActivity : ComponentActivity() {
    override fun onStart() {
        super.onStart()

        val user = FirebaseAuth.getInstance().currentUser
        println("user $user")

        if (user != null) {
            setContent {
                LetterbooksTheme {
                    Navigation()
                }
            }
        } else {
            setContent {
                LetterbooksTheme {
                    Navigation("loginScreen")
                }
            }
        }
    }
}


@Composable
fun Navigation(startDestination: String = "homeScreen") {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = "loginScreen") {
            LoginScreen(navController = navController)
        }

        composable(route = "registerScreen") {
            RegisterScreen(navController = navController)
        }

        composable(route = "homeScreen") {
            HomeScreen(navController = navController)
        }

        composable(route = "profileScreen") {
            ProfileScreen(navController = navController)
        }

        composable(route = "exploreScreen") {
            ExploreScreen(navController = navController)
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



