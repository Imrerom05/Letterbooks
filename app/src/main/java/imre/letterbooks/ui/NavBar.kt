import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
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
     * Bottom navigation item for the Book Screen.
     */
    data object Book : BottomNavItem(
        route = "bookScreen",
        label = "Books",
        icon = Icons.Default.AutoStories
    )


}


/**
 * List of all items shown in the bottom navigation bar.
 */
val bottomNavItems = listOf(
    BottomNavItem.Book,
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

    val currentRoute = navController
        .currentBackStackEntryAsState()
        .value
        ?.destination
        ?.route

    Surface(
        color = Color(0xFF121826),
        tonalElevation = 8.dp,
        shadowElevation = 12.dp,
    ) {

        NavigationBar(
            containerColor = Color.Transparent
        ) {

            bottomNavItems.forEach { item ->

                val selected = currentRoute == item.route

                NavigationBarItem(
                    selected = selected,

                    onClick = {
                        if (!selected) {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },

                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF4C7DFF),
                        selectedTextColor = Color(0xFF4C7DFF),

                        indicatorColor = Color(0xFF4C7DFF)
                            .copy(alpha = 0.15f),

                        unselectedIconColor = Color(0xFF7A859A),
                        unselectedTextColor = Color(0xFF7A859A)
                    ),

                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label
                        )
                    },

                    label = {
                        Text(item.label)
                    }
                )
            }
        }
    }
}