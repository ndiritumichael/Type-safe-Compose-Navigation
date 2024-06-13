

Type Safe Compose Navigation
============================

This is a functional Recipe app built in Kotlin and jetpack compose.

It is implemented to act as guide for for implementing the new Type Safe Compose Navigation api.

The app is currently in development as the api is in beta, any updates from the Jetpack team will be updated in due time.

PRs on improvements and bug fixes are welcome.

## Architecture
The architecture has been simplified to focus on the navigation implementation of the project. 

### Development Environment
Android Studio JellyFish or newer

### Setup

```toml
[versions]
navigationCompose = "2.8.0-beta02"
kotlin = "1.9.23"

[libraries]
androidx-navigation-compose = { module = "androidx.navigation:navigation-compose", version.ref = "navigationCompose" }

[plugins]
kotlinX-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlin" }
```
in your project build gradle file add kotlinX Serialization plugin
```kotlin
alias(libs.plugins.kotlinX.serialization) apply false
```

in your app build.gradle.kts file
```kotlin
alias(libs.plugins.kotlinX.serialization)


depedencies{
     implementation(libs.androidx.navigation.compose)
     //other deps
}
```

Declare your routes as Serializable Types

```kotlin

sealed class AppDestinations{
@Serializable
data object RecipeList : AppDestinations()

@Serializable data class RecipeDetail(val id : Int) : AppDestinations()
}
```


Declare your navigation graph using the serializable routes as destination type.

To navigate to a new screen,invoke the navcontroller.navigate() and pass the serializable type as the parameter

if you are navigating with arguments, on the destination screen you can invoke backstackentry.toRoute<T>()
 to recreate the object from the route.


```kotlin
    NavHost(
        navController = navController,
        startDestination = RecipeDestinations.Recipes,
        modifier = modifier
    ) {
        composable<RecipeDestinations.Recipes> {
            AllRecipesScreen(RecipeRepository.getAllRecipes()) { recipeId ->
                navController.navigate(RecipeDestinations.RecipeDetails(recipeId)) {
                    launchSingleTop = true
                }
            }

        }
    
        composable<RecipeDestinations.RecipeDetails> { backStackEntry ->
            val recipeDetails: RecipeDestinations.RecipeDetails = backStackEntry.toRoute()

            RecipeDetailPage(recipeDetails.recipeId) {
                navController.navigateUp()
            }

        }
            //more destinations
    
    }

    //In The viewmodel

    class RecipeDetailViewModel(savedStateHandle : SavedStateHandle){
        val recipeDetails: RecipeDestinations.RecipeDetails = savedStateHandle.toRoute()
        ....

    }

```

 The SavedStateHandle also has the extension toRoute() to retrieve the arguments directly into the viewmodel
 ```kotlin
    //In The viewmodel

    class RecipeDetailViewModel(savedStateHandle : SavedStateHandle,repository : RecipeRepository){
        val recipeDetails: RecipeDestinations.RecipeDetails = savedStateHandle.toRoute()
        ....


        fun getRecipeDetails(){
            repository.getRecipeDetails(recipeDetails.id)
            ...

        }

    }

```


### Bottom Bar Integration
Define an enum class that contains declrations and configuration of the bottom bar ,
the only change we do from previous implementation is have the route as an object instead of a String

```kotlin
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

}
```
In your BottomNavigation composable, get the current NavBackStackEntry using the currentBackStackEntryAsState() function. This entry gives you access to the current NavDestination. The selected state of each BottomNavigationItem can then be determined by checking if the current destination or any of its parent destinations have the same route class as the item's route using the NavDestination.hasRoute() function and the item's route::class KClass instance.

```kotlin
@Composable
fun RecipeBottomBar(navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination: NavDestination? = navBackStackEntry?.destination

    val showBottomNav = TopLevelDestinations.entries.map { it.route::class }.any { route ->

        currentDestination?.hierarchy?.any {
            it.hasRoute(route)
        } == true
    }


    AnimatedVisibility(visible = showBottomNav) {
        BottomAppBar {

            TopLevelDestinations.entries.map { bottomNavigationItem ->
                val isSelected =
                    currentDestination?.hierarchy?.any { it.hasRoute(bottomNavigationItem.route::class) } == true

                if (currentDestination != null) {
                    NavigationBarItem(selected = isSelected,
                        onClick = {
                            navController.navigate(bottomNavigationItem.route)
                        },
                        icon = {
                            Icon(
                                imageVector = if (isSelected) bottomNavigationItem.selectedIcon else bottomNavigationItem.unselectedIcon,
                                contentDescription = bottomNavigationItem.label
                            )
                        }, alwaysShowLabel = isSelected,
                        label = {
                            Text(bottomNavigationItem.label)
                        })
                }
            }

        }


    }


}
```

### Passing Custom Types
Even though it is not recomended there might be a need to pass a complex data type as a navigation argument,
There are built-in NavTypes for primitive types, such as int, long, boolean, float, and strings,
 parcelable, and serializable classes (including Enums), as well as arrays of each supported type.

For any other custom types you need to create a custom NavType and pass it to your graph declaration. 
This is to define how the custom type can be encoded and decoded during navigation.
We have custom type of an enum class for different types of recipes 
``` kotlin

enum class RecipeType {
    Breakfast,
    Lunch,
    Supper,
    Snack
}
```
Fortunately enums are supported as a navtype and all we need is pass the type map as an argument of the composable

```kotlin

  composable<RecipeDestinations.CategoryRecipes>(
            typeMap = mapOf(typeOf<RecipeType>() to NavType.EnumType(RecipeType::class.java))
        ) { backStackEntry ->

            val category: RecipeDestinations.CategoryRecipes =
                backStackEntry.toRoute<RecipeDestinations.CategoryRecipes>()

            CategoryRecipesScreen(
                category.type,
               //pass other arguments
            }


        }
```

we will however create our custom Navtype for the RecipeType enum class to demonstrate how it should be done for any complex type.
we'll need to add the parcelize plugin to our app level gradle file it will now look like this
```kotlin
plugins{
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinX.serialization)
    id("kotlin-parcelize")
}
```
Next we will annoate the the Recipetype class with @Parcelize  and Serializable then make it extend Parcelable
```kotlin
@Serializable
@Parcelize
enum class RecipeType : Parcelable {
    Breakfast,
    Lunch,
    Supper,
    Snack
}
```
We create the Navtype for the recipe class following [This Guide](https://developer.android.com/guide/navigation/design/kotlin-dsl#custom-types)
``` kotlin
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
```
Finally we will pass it when creating our graph
```kotlin
 composable<RecipeDestinations.CategoryRecipes>(
            typeMap = mapOf(typeOf<RecipeType>() to CategoryNavigationType)
        ) { backStackEntry ->

            val category: RecipeDestinations.CategoryRecipes =
                backStackEntry.toRoute<RecipeDestinations.CategoryRecipes>()

            CategoryRecipesScreen(
                category.type,
               ...


        }
```

### Conclusion
The api is currently in beta and not much is expected to change in the future if you encounter an issue [file it](https://issuetracker.google.com/issues/new?component=409828)
Happy Coding , Leave a Star ⭐,remember to keep your types safe and your code right.


