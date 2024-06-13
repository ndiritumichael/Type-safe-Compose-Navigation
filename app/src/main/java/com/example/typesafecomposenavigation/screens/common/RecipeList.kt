package com.example.typesafecomposenavigation.screens.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.typesafecomposenavigation.data.RecipeRepository
import com.example.typesafecomposenavigation.data.SAMPLERECIPES
import com.example.typesafecomposenavigation.model.RecipeModel


@Composable
fun RecipeListScreen(
    modifier: Modifier = Modifier,
    recipes: List<RecipeModel>,
    onRecipeClicked: (id: Int) -> Unit
) {

    LazyColumn(modifier = modifier) {
        items(recipes, key = {
            it.id

        }) { recipe ->
            RecipeListItem(recipe, onClick = { onRecipeClicked(recipe.id) })

        }
    }

}


@Composable
fun RecipeListItem(recipe: RecipeModel, onClick: () -> Unit) {

    OutlinedCard(
        onClick = onClick,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(bottom = 8.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(imageVector = Icons.Filled.Kitchen, contentDescription = "Kitchen")

            Column {

                Text(recipe.name, fontWeight = FontWeight.Bold)
                Text(text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                        append("Category")
                        append(" : ")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(recipe.mealType.name)
                    }

                })

                Text(text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                        append("Difficulty")
                        append("  : ")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(recipe.difficultyLevel.name)
                    }

                })

            }
        }


    }


}

@Preview
@Composable
fun PreviewRecipeListitem() {
    val item = SAMPLERECIPES.random()
    Surface {


        RecipeListItem(recipe = item) {

        }


    }
}


@Preview
@Composable
fun PreviewRecipeListScreen() {
    RecipeListScreen(Modifier, RecipeRepository.getAllRecipes()) {

    }
}