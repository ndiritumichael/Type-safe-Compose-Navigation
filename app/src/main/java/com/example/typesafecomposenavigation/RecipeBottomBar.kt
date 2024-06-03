package com.example.typesafecomposenavigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun RecipeBottomBar(navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination: NavDestination? = navBackStackEntry?.destination

    val showBottomNav = BottomNavigationItems.entries.map { it.route::class }.any { route ->

        currentDestination?.hierarchy?.any {
            it.hasRoute(route)
        } == true
    }


    AnimatedVisibility(visible = showBottomNav) {
        BottomAppBar {

            BottomNavigationItems.entries.map { bottomNavigationItem ->
                val isSeleccted =
                    currentDestination?.hierarchy?.any { it.hasRoute(bottomNavigationItem.route::class) } == true

                if (currentDestination != null) {
                    NavigationBarItem(selected = isSeleccted,
                        onClick = {
                            navController.navigate(bottomNavigationItem.route)
                        },
                        icon = {
                            Icon(
                                imageVector = if (isSeleccted) bottomNavigationItem.selectedIcon else bottomNavigationItem.unselectedIcon,
                                contentDescription = bottomNavigationItem.label
                            )
                        }, alwaysShowLabel = isSeleccted,
                        label = {
                            Text(bottomNavigationItem.label)
                        })
                }
            }

        }


    }


}