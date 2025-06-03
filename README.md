

Type Safe Compose Navigation
============================

This is a functional Recipe app built in Kotlin and jetpack compose.

It is implemented to act as guide for for implementing the new Type Safe Compose Navigation api.

The app is update as new versions of navigation library come up.

PRs on improvements and bug fixes are welcome.

## Architecture
The architecture has been simplified to focus on the navigation implementation of the project. 

### Development Environment
Android Studio JellyFish or newer

### Setup

```toml
[versions]
navigationCompose = "2.9.0"
kotlin = "2.1.0"

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

### Deeplinks

To implement Deeplinks and Applinks follow [this guide](https://developer.android.com/training/app-links) from the android developer page.
In our case we want to handle deeplinks for a recipe id and navigate to the recipe details page.
First configure the android manifest and declare your intent filters a deeplink format for uris and applink format for normal links
```xml
 <activity
            android:name=".MainActivity">
 
            <intent-filter android:autoVerify="true">
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />
                <data android:scheme="http"
                    android:host="com.example.typesafecomposenavigation" />
            </intent-filter>

            <intent-filter android:label="@string/app_name">
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />
                <data android:scheme="example"
                    android:host="recipe" />
            </intent-filter>
        </activity>
```

Next in the app navigation file where we define our navhost we will declare the links and add them as entries 
as list.
The composable graph builder extension accepts a list of navdeeplinks which should have the same type as the route destination.
This is what will be used to automatically deserialize the deeplink params into the route arguments

In your NavHost graph, use the `navDeepLink` function within the `deepLinks` parameter of a `composable` destination.
When you define a `navDeepLink`, you provide a `basePath`. The Navigation library then infers the full URI pattern by inspecting the properties of your `@Serializable` route class:
Non-optional properties (like `id`) are treated as path parameters (e.g., `/{id}`). The name of the path parameter placeholder `{id}` is derived from the property name `id`.
Optional properties or those with default values are treated as query parameters (e.g., `?paramName={paramName}`).

 ``` kotlin
    private const val DEEPLINK_BASE_APP_LINK = "http://com.example.typesafecomposenavigation"
    private const val DEEPLINK_BASE_CUSTOM_SCHEME = "example://recipe"

    //... in your NavHost
    composable<RecipeDestinations.RecipeDetails>(
        deepLinks = listOf(
       
            navDeepLink<RecipeDestinations.RecipeDetails>(basePath = "${DEEPLINK_BASE_APP_LINK}/recipe"),
            // This matches: http://com.example.typesafecomposenavigation/recipe/{id}

            navDeepLink<RecipeDestinations.RecipeDetails>(basePath = DEEPLINK_BASE_CUSTOM_SCHEME)
            // This matches: example://recipe/{id}
        )
    ) { backStackEntry ->
        val recipeDetails: RecipeDestinations.RecipeDetails = backStackEntry.toRoute()
        RecipeDetailPage(recipeDetails.recipeId) {
            navController.navigateUp()
        }
    }
```

**Testing Deep Links:**

You can test your deep links using ADB:
  ``` shell
    # For the app link
    adb shell am start -W -a android.intent.action.VIEW -d "http://com.example.typesafecomposenavigation/recipe/123" com.example.typesafecomposenavigation
    ```

    # For the custom scheme
    adb shell am start -W -a android.intent.action.VIEW -d "example://recipe/456" com.example.typesafecomposenavigation
 ```
### Conclusion
if you encounter an issue [file it here](https://issuetracker.google.com/issues/new?component=409828)
Happy Coding , Leave a Star ⭐,remember to keep your types safe and your code right.


