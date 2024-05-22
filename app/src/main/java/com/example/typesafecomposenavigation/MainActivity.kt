package com.example.typesafecomposenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.typesafecomposenavigation.ui.theme.TypeSafeComposeNavigationTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {

            val navController = rememberNavController()
            TypeSafeComposeNavigationTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                    RecipeBottomBar(navController = navController)
                }) { innerPadding ->
                   AppNavigation(navController = navController , startDestination = RecipeScreens.Profile, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TypeSafeComposeNavigationTheme {
        Greeting("Android")
    }
}


@Composable
fun ProgressBar(modifier: Modifier) {

    var state by remember {
        mutableFloatStateOf(0f)
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){





    }


    LaunchedEffect(key1 = true) {
        while(state<1.1f){
            if(state >1f){
                state = 0f
            }

            delay(1000)
            state += 0.1f
        }


    }



}