import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


/**
 * Represents an item in the bottom navigation bar.
 *
 * Each item has:
 * - a navigation [route]
 * - a string for the label
 * - an [icon] to display
 */
sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {

    /**
     * Bottom navigation item for the Home screen.
     */
    data object Home : BottomNavItem(
        route = "homeScreen",
        label = "Home",
        icon = Icons.Default.Home
    )


    /**
     * Bottom navigation item for the Profile screen.
     */
    data object Profile : BottomNavItem(
        route = "profileScreen",
        label = "Profile",
        icon = Icons.Default.Person
    )


    /**
     * Bottom navigation item for the Explor Screen.
     */
    data object Explor : BottomNavItem(
        route = "exploreScreen",
        label = "Explore",
        icon = Icons.Default.Search
    )


    /**
     * Bottom navigation item for the settings.
     */
    data object Settings : BottomNavItem(
        route = "settingsScreen",
        label = "Settings",
        icon = Icons.Default.Settings
    )
}


/**
 * List of all items shown in the bottom navigation bar.
 */
val bottomNavItems = listOf(
    BottomNavItem.Explor,
    BottomNavItem.Home,
    BottomNavItem.Profile
)

/**
 * Composable that renders the bottom navigation bar and handles navigation
 * between the main screens.
 *
 * @param navController The [NavController] used to navigate between screens.
 */
@Composable
fun NavBar(navController: NavController) {
    // Observe the current back stack entry to determine which route is active.
    val currentRoute = navController
        .currentBackStackEntryAsState()
        .value
        ?.destination
        ?.route

    NavigationBar {
        bottomNavItems.forEach { item ->
            val label = item.label

            NavigationBarItem(
                // Mark the item as selected if the current route starts with this item's route.
                selected = currentRoute?.startsWith(item.route) == true,
                onClick = {
                    // Only navigate if we are not already on this item's route.
                    if (currentRoute?.startsWith(item.route) == false) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination on the back stack.
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = label
                    )
                },
                label = {
                    Text(text = label)
                }
            )
        }
    }
}
