package com.example.typesafecomposenavigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.testing.invoke
import androidx.navigation.toRoute
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.typesafecomposenavigation.ui.navigation.RecipeDestinations
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NavigationTest {


    @Test
    fun testNavigation() {
        val detail = RecipeDestinations.RecipeDetails(2)

        val savedStateHandle = SavedStateHandle.Companion.invoke(detail)

        val navDetails: RecipeDestinations.RecipeDetails = savedStateHandle.toRoute()


        assertEquals(navDetails.recipeId, detail.recipeId)
    }


}