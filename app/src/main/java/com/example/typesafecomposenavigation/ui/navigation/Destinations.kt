package com.example.typesafecomposenavigation.ui.navigation

import android.os.Build
import android.os.Bundle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import com.example.typesafecomposenavigation.model.RecipeType
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


sealed class RecipeDestinations {

    @Serializable
    data object Recipes : RecipeDestinations()

    @Serializable
    data object Favorites : RecipeDestinations()

    @Serializable
    data class RecipeDetails(val recipeId: Int) : RecipeDestinations()

    @Serializable
    data object Category : RecipeDestinations()

    @Serializable
    data class CategoryRecipes(val type: RecipeType) : RecipeDestinations()

}

enum class TopLevelDestinations(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: RecipeDestinations
) {


    Recipes(
        label = "Recipes",
        selectedIcon = Icons.Filled.Restaurant,
        unselectedIcon = Icons.Outlined.Restaurant,
        route = RecipeDestinations.Recipes
    ),
    Favorites(
        label = "Favorites",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.Favorite,
        route = RecipeDestinations.Favorites
    ),
    Category(
        label = "Category",
        selectedIcon = Icons.Filled.GridView,
        unselectedIcon = Icons.Outlined.GridView,
        route = RecipeDestinations.Category
    )
}

val CategoryNavigationType = object : NavType<RecipeType>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): RecipeType? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, RecipeType::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }

    }

    override fun parseValue(value: String): RecipeType {
        return Json.decodeFromString<RecipeType>(value)
    }

    override fun serializeAsValue(value: RecipeType): String {
        return Json.encodeToString(value)
    }

    override fun put(bundle: Bundle, key: String, value: RecipeType) {
        bundle.putParcelable(key, value)
    }


}