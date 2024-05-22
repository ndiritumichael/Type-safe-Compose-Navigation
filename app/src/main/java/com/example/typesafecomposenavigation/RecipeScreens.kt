package com.example.typesafecomposenavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Cookie
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
sealed class RecipeScreens {
    @Serializable
    data object Recipes : RecipeScreens()

    @Serializable
    data object Favorites : RecipeScreens()

    @Serializable
    data class RecipeDetails(val name: String) : RecipeScreens()

    @Serializable
    data object Profile : RecipeScreens()

}

enum class BottomNavigationItems(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: RecipeScreens
) {


    Recipes(
        label = "Recipes",
        selectedIcon = Icons.Filled.Cookie,
        unselectedIcon = Icons.Outlined.Cookie,
        route = RecipeScreens.Recipes
    ),
    Favorites(
        label = "Favorites",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.Favorite,
        route = RecipeScreens.Favorites
    ),
    Profile(
        label = "Profile",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        route = RecipeScreens.Profile
    )
}