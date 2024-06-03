package com.example.typesafecomposenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.typesafecomposenavigation.ui.theme.TypeSafeComposeNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            val statusBarHeight = WindowInsets.statusBars

            val navController = rememberNavController()
            TypeSafeComposeNavigationTheme {
                Surface {


                    Scaffold(modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = statusBarHeight
                                .asPaddingValues()
                                .calculateTopPadding()
                        )
                        .consumeWindowInsets(WindowInsets.statusBars)
                        .statusBarsPadding(),
                        bottomBar = {
                            RecipeBottomBar(navController = navController)
                        }) { innerPadding ->
                        AppNavigation(
                            navController = navController,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

