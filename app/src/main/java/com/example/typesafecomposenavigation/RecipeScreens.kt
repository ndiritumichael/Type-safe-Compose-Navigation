package com.example.typesafecomposenavigation

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
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
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
sealed class RecipeScreens {

    @Serializable
    data object Recipes : RecipeScreens()

    @Serializable
    data object Favorites : RecipeScreens()

    @Serializable
    data class RecipeDetails(val recipeId: Int) : RecipeScreens()

    @Serializable
    data object Category : RecipeScreens()

    @Serializable
    data class CategoryRecipes(val type: RecipeType) : RecipeScreens()

}

enum class BottomNavigationItems(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: RecipeScreens
) {


    Recipes(
        label = "Recipes",
        selectedIcon = Icons.Filled.Restaurant,
        unselectedIcon = Icons.Outlined.Restaurant,
        route = RecipeScreens.Recipes
    ),
    Favorites(
        label = "Favorites",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.Favorite,
        route = RecipeScreens.Favorites
    ),
    Category(
        label = "Category",
        selectedIcon = Icons.Filled.GridView,
        unselectedIcon = Icons.Outlined.GridView,
        route = RecipeScreens.Category
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