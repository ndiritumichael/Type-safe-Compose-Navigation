package com.example.typesafecomposenavigation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


enum class RecipeType {
    Breakfast,
    Lunch,
    Supper,
    Snack
}


enum class DifficultyLevel {
    Beginner,
    Intermediate,
    Advanced
}
