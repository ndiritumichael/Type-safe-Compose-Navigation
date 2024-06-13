package com.example.typesafecomposenavigation.data

import com.example.typesafecomposenavigation.model.DifficultyLevel
import com.example.typesafecomposenavigation.model.Ingredient
import com.example.typesafecomposenavigation.model.RecipeModel
import com.example.typesafecomposenavigation.model.RecipeType


internal val lunchRecipes = listOf(
    RecipeModel(
        id = 1,
        name = "Nyama Choma (Grilled Meat)",
        mealType = RecipeType.Lunch,
        difficultyLevel = DifficultyLevel.Intermediate,
        ingredients = listOf(
            Ingredient("Beef cubes", "500g"),
            Ingredient("Salt", "1 tsp"),
            Ingredient("Black pepper", "1 tsp"),
            Ingredient("Vegetable oil", "2 tbsp"),
            Ingredient("Onions", "1 large, sliced")
        ),
        steps = listOf(
            "Marinate beef cubes with salt and black pepper.",
            "Skewer the beef cubes and grill until well-cooked.",
            "Sauté onions in vegetable oil and serve with grilled meat."
        ),
        servings = 4
    ),
    RecipeModel(
        id = 27,
        name = "Matoke Stew",
        mealType = RecipeType.Lunch,
        difficultyLevel = DifficultyLevel.Advanced,
        ingredients = listOf(
            Ingredient("Matoke (green bananas)", "6 peeled and sliced"),
            Ingredient("Beef stew meat", "300 grams"),
            Ingredient("Onion", "1 medium, chopped"),
            Ingredient("Tomatoes", "2 medium, diced"),
            Ingredient("Garlic", "2 cloves, minced"),
            Ingredient("Ginger", "1 teaspoon, grated"),
            Ingredient("Vegetable oil", "2 tablespoons"),
            Ingredient("Curry powder", "1 teaspoon"),
            Ingredient("Turmeric powder", "0.5 teaspoon"),
            Ingredient("Salt", "1 teaspoon"),
            Ingredient("Fresh cilantro", "2 tablespoons, chopped, for garnish")
        ),
        steps = listOf(
            "In a large pot, heat vegetable oil and brown the beef stew meat.",
            "Add chopped onions and sauté until translucent.",
            "Stir in minced garlic and grated ginger, cook for an additional minute.",
            "Add diced tomatoes and cook until they break down into a thick sauce.",
            "Sprinkle curry powder, turmeric powder, and salt. Mix well.",
            "Add sliced matoke (green bananas) and stir to coat with the sauce.",
            "Pour enough water to cover the matoke and bring the stew to a simmer.",
            "Cover the pot and simmer on low heat for 20-25 minutes or until matoke is tender.",
            "Garnish with chopped cilantro before serving."
        ),
        servings = 6
    ),
    RecipeModel(
        id = 30,
        name = "Mashed Potatoes and Meat",
        mealType = RecipeType.Lunch,
        difficultyLevel = DifficultyLevel.Intermediate,
        ingredients = listOf(
            Ingredient("Potatoes", "5 large, peeled and diced"),
            Ingredient("Beef stew meat", "600g, cooked and shredded"),
            Ingredient("Milk", "1 cup"),
            Ingredient("Butter", "4 tablespoons"),
            Ingredient("Salt", "1.5 teaspoons"),
            Ingredient("Black pepper", "1/2 teaspoon"),
            Ingredient("Garlic powder", "1/2 teaspoon"),
            Ingredient("Cheddar cheese", "1 cup, shredded"),
            Ingredient("Green onions", "2 tablespoons, chopped, for garnish")
        ),
        steps = listOf(
            "Boil potatoes until fork-tender. Drain and mash with butter and milk until smooth.",
            "Season mashed potatoes with salt, black pepper, and garlic powder. Mix well.",
            "In a separate bowl, mix shredded beef with a portion of mashed potatoes.",
            "Layer mashed potatoes in a baking dish, top with the meat-potato mixture, and sprinkle cheddar cheese on top.",
            "Bake in the oven until the cheese is melted and bubbly.",
            "Garnish with chopped green onions and serve hot.",
            "Enjoy your Mashed Potatoes and Meat!"
        ),
        servings = 8
    ),

    RecipeModel(
        id = 2,
        name = "Sukuma Wiki and Ugali",
        mealType = RecipeType.Lunch,
        difficultyLevel = DifficultyLevel.Beginner,
        ingredients = listOf(
            Ingredient("Collard greens (sukuma wiki)", "500g"),
            Ingredient("Maize flour", "2 cups"),
            Ingredient("Water", "4 cups"),
            Ingredient("Salt", "1 tsp"),
            Ingredient("Onions", "1 medium, chopped"),
            Ingredient("Tomatoes", "2 medium, diced")
        ),
        steps = listOf(
            "Sauté onions in a pot until golden brown.",
            "Add tomatoes and cook until soft.",
            "Add collard greens and cook until wilted. Serve with ugali."
        ),
        servings = 4
    ),

    RecipeModel(
        id = 4, name = "Ugali with Liver Stew",
        mealType = RecipeType.Lunch,
        difficultyLevel = DifficultyLevel.Intermediate,
        ingredients = listOf(
            Ingredient("Ugali flour", "2 cups"),
            Ingredient("Water", "4 cups"),
            Ingredient("Beef liver", "500g, sliced"),
            Ingredient("Onions", "2 medium, finely chopped"),
            Ingredient("Tomatoes", "3 medium, diced"),
            Ingredient("Vegetable oil", "3 tbsp"),
            Ingredient("Garlic", "3 cloves, minced"),
            Ingredient("Ginger", "1 tsp, grated"),
            Ingredient("Curry powder", "1 tbsp"),
            Ingredient("Salt", "1 tsp"),
            Ingredient("Cilantro", "2 tbsp, chopped, for garnish")
        ),
        steps = listOf(
            "Prepare ugali by boiling ugali flour in water until it forms a stiff consistency.",
            "In a pan, heat vegetable oil. Sauté chopped onions until golden brown.",
            "Add minced garlic and grated ginger. Cook for an additional minute.",
            "Add diced tomatoes, curry powder, and salt. Cook until tomatoes break down into a thick sauce.",
            "Add sliced beef liver and cook until browned and cooked through.",
            "Serve hot liver stew over ugali, garnished with chopped cilantro.",
            "Enjoy your Ugali with Liver Stew!"
        ),
        servings = 5
    ),

    )


internal val breakfastRecipes = listOf(
    RecipeModel(
        id = 29,
        name = "Kenyan Tea and Mandazi",
        mealType = RecipeType.Breakfast,
        difficultyLevel = DifficultyLevel.Beginner,
        ingredients = listOf(
            Ingredient("Black tea leaves", "2.0 tsp"),
            Ingredient("Water", "200.0 ml"),
            Ingredient("Sugar", "1.0 tbsp"),
            Ingredient("Mandazi mix", "300.0 g"),
            Ingredient("Coconut milk", "100.0 ml"),
            Ingredient("Cooking oil", "50.0 ml")
        ),
        steps = listOf(
            "Brew strong black tea and add sugar.",
            "Mix mandazi mix, coconut milk, and let it rise.",
            "Fry mandazi in hot oil until golden brown."
        ),
        servings = 2
    ),

    RecipeModel(
        id = 22,
        name = "Uji (Kenyan Porridge)",
        mealType = RecipeType.Breakfast,
        difficultyLevel = DifficultyLevel.Beginner,
        ingredients = listOf(
            Ingredient("Millet flour", "100.0 grams"),
            Ingredient("Water", "500.0 ml"),
            Ingredient("Sugar", "2.0 tbsp"),
            Ingredient("Salt", "1.0 pinch")
        ),
        steps = listOf(
            "Mix millet flour with water to form a smooth paste.",
            "Boil water and slowly add the millet mixture while stirring.",
            "Simmer until the porridge thickens. Add sugar and salt to taste."
        ),
        servings = 2
    ),

    RecipeModel(
        id = 33,
        name = "Chapati and Vegetable Curry",
        mealType = RecipeType.Breakfast,
        difficultyLevel = DifficultyLevel.Intermediate,
        ingredients = listOf(
            Ingredient("Chapati flour", "300.0 g"),
            Ingredient("Water", "150.0 ml"),
            Ingredient("Vegetable curry mix", "400.0 g"),
            Ingredient("Onions", "1.0 medium, chopped"),
            Ingredient("Tomatoes", "2.0 medium, diced"),
            Ingredient("Cooking oil", "2.0 tbsp")
        ),
        steps = listOf(
            "Knead chapati flour with water to form a soft dough.",
            "Divide the dough into small balls and roll them into chapatis.",
            "Cook chapatis on a hot griddle. Serve with vegetable curry."
        ),
        servings = 2
    ),

    RecipeModel(
        id = 41,
        name = "Oatmeal with Berries",
        mealType = RecipeType.Breakfast,
        difficultyLevel = DifficultyLevel.Beginner,
        ingredients = listOf(
            Ingredient("Rolled oats", "1.0 cup"),
            Ingredient("Milk", "1.5 cups"),
            Ingredient("Berries (strawberries, blueberries)", "1.0 cup, mixed"),
            Ingredient("Honey", "2.0 tbsp"),
            Ingredient("Chia seeds", "1.0 tbsp")
        ),
        steps = listOf(
            "In a saucepan, combine rolled oats and milk. Bring to a simmer.",
            "Cook, stirring occasionally, until the oats are tender and the mixture has thickened.",
            "Remove from heat and let it cool slightly.",
            "Top the oatmeal with mixed berries, honey, and chia seeds.",
            "Serve warm and enjoy your nutritious oatmeal!"
        ),
        servings = 3
    ),

    RecipeModel(
        id = 51,
        name = "Avocado Toast with Poached Eggs",
        mealType = RecipeType.Breakfast,
        difficultyLevel = DifficultyLevel.Intermediate,
        ingredients = listOf(
            Ingredient("Whole-grain bread slices", "2.0"),
            Ingredient("Avocado", "1.0, mashed"),
            Ingredient("Eggs", "2.0, poached"),
            Ingredient("Salt", "1.0 pinch"),
            Ingredient("Black pepper", "to taste"),
            Ingredient("Red pepper flakes", "optional, for garnish")
        ),
        steps = listOf(
            "Toast the whole-grain bread slices to your liking.",
            "Spread mashed avocado evenly over each toast.",
            "Top with poached eggs. Season with salt and black pepper.",
            "Garnish with red pepper flakes if desired.",
            "Serve immediately and savor the creamy avocado toast with poached eggs!"
        ),
        servings = 2
    )
)


internal val supperRecipes = listOf(
    RecipeModel(
        id = 20,
        name = "Ugali with Sukuma Wiki and Nyama Choma",
        mealType = RecipeType.Supper,
        difficultyLevel = DifficultyLevel.Advanced,
        ingredients = listOf(
            Ingredient("Maize flour", "2 cups"),
            Ingredient("Collard greens", "1 bunch"),
            Ingredient("Grilled meat", "400 grams")
        ),
        steps = listOf(
            "Prepare ugali by boiling maize flour in water until it forms a stiff consistency.",
            "Sauté finely chopped sukuma wiki until wilted.",
            "Grill meat until well-cooked.",
            "Serve hot ugali with sukuma wiki and nyama choma."
        ),
        servings = 7
    ),
    RecipeModel(
        id = 21,
        name = "Coconut Rice and Beans",
        mealType = RecipeType.Supper,
        difficultyLevel = DifficultyLevel.Intermediate,
        ingredients = listOf(
            Ingredient("Rice", "2 cups"),
            Ingredient("Black beans", "1 cup, cooked"),
            Ingredient("Coconut milk", "1.5 cups"),
            Ingredient("Onion", "1 medium, chopped"),
            Ingredient("Garlic", "2 cloves, minced"),
            Ingredient("Vegetable oil", "2 tablespoons"),
            Ingredient("Salt", "1 teaspoon"),
            Ingredient("Black pepper", "0.5 teaspoon"),
            Ingredient("Fresh cilantro", "2 tablespoons, chopped, for garnish")
        ),
        steps = listOf(
            "Rinse the rice under cold water until the water runs clear.",
            "In a large pot, heat vegetable oil and sauté chopped onions until translucent.",
            "Add minced garlic and cook for an additional minute.",
            "Stir in rice and cook for 2-3 minutes until lightly toasted.",
            "Pour in coconut milk and bring to a simmer.",
            "Add cooked black beans, salt, and black pepper. Stir well.",
            "Cover the pot and simmer on low heat for 15-20 minutes or until rice is cooked and liquid is absorbed.",
            "Fluff the rice with a fork, garnish with chopped cilantro, and serve hot."
        ),
        servings = 4
    ),


    RecipeModel(
        id = 36,
        name = "Baked Chicken with Rosemary",
        mealType = RecipeType.Supper,
        difficultyLevel = DifficultyLevel.Advanced,
        ingredients = listOf(
            Ingredient("Chicken thighs", "4 pieces"),
            Ingredient("Fresh rosemary", "2 tablespoons, chopped"),
            Ingredient("Garlic", "4 cloves, minced"),
            Ingredient("Lemon juice", "2 tablespoons"),
            Ingredient("Olive oil", "3 tablespoons"),
            Ingredient("Salt", "1 teaspoon"),
            Ingredient("Black pepper", "1/2 teaspoon")
        ),
        steps = listOf(
            "Preheat the oven to 190°C.",
            "Rub chicken thighs with olive oil, minced garlic, chopped rosemary, salt, and black pepper.",
            "Place the chicken in a baking dish and drizzle with lemon juice.",
            "Bake for 35-40 minutes or until the chicken is golden and cooked through.",
            "Serve with your favorite roasted vegetables."
        ),
        servings = 4
    ),
    RecipeModel(
        id = 79,
        name = "Githeri with Potatoes",
        mealType = RecipeType.Supper,
        difficultyLevel = DifficultyLevel.Beginner,
        ingredients = listOf(
            Ingredient("Githeri mix", "2 cups"),
            Ingredient("Water", "4 cups"),
            Ingredient("Potatoes", "4 medium, peeled and diced"),
            Ingredient("Onions", "1 large, chopped"),
            Ingredient("Tomatoes", "2 medium, diced"),
            Ingredient("Vegetable oil", "3 tablespoons"),
            Ingredient("Salt", "1 teaspoon"),
            Ingredient("Cumin powder", "1/2 teaspoon"),
            Ingredient("Coriander powder", "1/2 teaspoon"),
            Ingredient("Turmeric powder", "1/4 teaspoon"),
            Ingredient("Cilantro", "2 tablespoons, chopped, for garnish")
        ),
        steps = listOf(
            "Boil githeri mix in water until tender. Drain and set aside.",
            "In a pan, heat vegetable oil. Sauté chopped onions until golden brown.",
            "Add diced potatoes, tomatoes, and spices (salt, cumin, coriander, turmeric). Cook until potatoes are tender.",
            "Mix cooked githeri with the potato mixture. Cook for an additional 5 minutes.",
            "Garnish with chopped cilantro and serve hot.",
            "Enjoy your Githeri with Potatoes!"
        ),
        servings = 6
    ),

    RecipeModel(
        id = 3,
        name = "Fish Stew with Coconut Rice",
        mealType = RecipeType.Supper,
        difficultyLevel = DifficultyLevel.Advanced,
        ingredients = listOf(
            Ingredient("Fish fillets", "600g"),
            Ingredient("Coconut milk", "400ml"),
            Ingredient("Tomato paste", "2 tbsp"),
            Ingredient("Onions", "2 medium, chopped"),
            Ingredient("Garlic", "3 cloves, minced"),
            Ingredient("Ginger", "1 tsp, minced"),
            Ingredient("Vegetable oil", "3 tbsp")
        ),
        steps = listOf(
            "Sauté onions, garlic, and ginger in vegetable oil until fragrant.",
            "Add tomato paste and cook for a few minutes.",
            "Add coconut milk and fish fillets. Simmer until fish is cooked. Serve with coconut rice."
        ),
        servings = 4
    ),

    )


internal val snackRecipes = listOf(
    RecipeModel(
        id = 57,
        name = "Boiled Eggs",
        mealType = RecipeType.Snack,
        difficultyLevel = DifficultyLevel.Beginner,
        ingredients = listOf(
            Ingredient("eggs", "6.0 cups")
        ),
        steps = listOf(
            "Place eggs in a saucepan and cover with water.",
            "Bring water to a boil, then immediately remove from heat and cover.",
            "Let eggs sit for 10 minutes for soft-boiled, 12 minutes for medium-boiled, or 15 minutes for hard-boiled.",
            "Run cold water over the eggs to stop the cooking process.",
            "Peel and enjoy."
        ),
        servings = 6
    ),
    RecipeModel(
        id = 39,
        name = "Spicy Garlic  Popcorns",
        mealType = RecipeType.Snack,
        difficultyLevel = DifficultyLevel.Beginner,
        ingredients = listOf(
            Ingredient("Popcorn kernels", "1.0 cup, unpopped"),
            Ingredient("Butter", "3.0 tablespoons, melted"),
            Ingredient("Garlic powder", "1.0 teaspoon"),

            Ingredient("Paprika", "0.5 teaspoon"),
            Ingredient("Cayenne pepper", "0.25 teaspoon (adjust to taste for spiciness)"),
            Ingredient("Salt", "0.5 teaspoon"),
            Ingredient("Fresh parsley", "2.0 tablespoons, chopped, for garnish")
        ),
        steps = listOf(
            "Pop the popcorn kernels using your preferred method and place them in a large bowl.",
            "In a small bowl, mix melted butter, garlic powder, grated Parmesan cheese, paprika, cayenne pepper, and salt.",
            "Drizzle the butter mixture over the popped popcorn and toss gently to coat evenly.",
            "Sprinkle chopped fresh parsley on top for added freshness.",
            "Serve immediately and enjoy your Spicy Garlic Parmesan Popcorns!"
        ),
        servings = 4
    ),
    RecipeModel(
        id = 10,
        name = "Spiced Boiled Maize",
        mealType = RecipeType.Snack,
        difficultyLevel = DifficultyLevel.Beginner,
        ingredients = listOf(
            Ingredient("Fresh maize on the cob", "4.0"),
            Ingredient("Water", "4.0 cups"),
            Ingredient("Salt", "1.0 tablespoon"),
            Ingredient("Butter", "2.0 tablespoons, melted"),
            Ingredient("Lemon wedges", "1.0"),
            Ingredient("Chili powder", "0.5 teaspoon"),
            Ingredient("Cilantro", "2.0 tablespoons, chopped, for garnish")
        ),
        steps = listOf(
            "Peel and clean the fresh maize on the cob.",
            "In a large pot, bring water to a boil and add salt.",
            "Add the maize to the boiling water and cook for 15-20 minutes or until the maize is tender.",
            "Drain the boiled maize and transfer to a serving plate.",
            "Brush melted butter over the boiled maize for flavor.",
            "Sprinkle chili powder over the maize for a hint of spice.",
            "Garnish with chopped cilantro and serve hot with lemon wedges on the side.",
            "Enjoy your Spiced Boiled Maize as a delicious and healthy snack!"
        ),
        servings = 4
    ),
    RecipeModel(
        id = 11,
        name = "Fruit Salad with Yogurt Dip",
        mealType = RecipeType.Snack,
        difficultyLevel = DifficultyLevel.Beginner,
        ingredients = listOf(
            Ingredient("Mixed fresh fruits", "1.0 cup"),
            Ingredient("Yogurt", "0.5 cup"),
            Ingredient("Honey", "1.0 tablespoon"),
            Ingredient("Lemon juice", "0.5 tablespoon"),
            Ingredient("Mint leaves", "2.0 tablespoons, chopped, for garnish")
        ),
        steps = listOf(
            "Wash and cut the fresh fruits into bite-sized pieces.",
            "In a small bowl, whisk together the yogurt, honey, and lemon juice.",
            "Transfer the fruit salad to a serving bowl and drizzle with the yogurt dip.",
            "Garnish with chopped mint leaves and serve chilled."
        ),
        servings = 2
    ),
    RecipeModel(
        id = 12,
        name = "Roasted Chickpeas",
        mealType = RecipeType.Snack,
        difficultyLevel = DifficultyLevel.Beginner,
        ingredients = listOf(
            Ingredient("Chickpeas", "1.0 cup, dried"),
            Ingredient("Olive oil", "2.0 tablespoons"),
            Ingredient("Salt", "0.5 teaspoon"),
            Ingredient("Paprika", "0.5 teaspoon"),
            Ingredient("Garlic powder", "0.25 teaspoon")
        ),
        steps = listOf(
            "Preheat the oven to 400 degrees Fahrenheit (200 degrees Celsius).",
            "Rinse the chickpeas and drain well.",
            "In a large bowl, combine the chickpeas, olive oil, salt, paprika, and garlic powder.",
            "Toss to coat the chickpeas evenly.",
            "Spread the chickpeas in a single layer on a baking sheet.",
            "Roast in the preheated oven for 20-25 minutes, or until golden brown and crispy.",
            "Let cool slightly before serving."
        ),
        servings = 4
    ),
    RecipeModel(
        id = 13,
        name = "Peanut Butter and Banana Smoothie",
        mealType = RecipeType.Snack,
        difficultyLevel = DifficultyLevel.Beginner,
        ingredients = listOf(
            Ingredient("Frozen banana", "1.0"),
            Ingredient("Peanut butter", "2.0 tablespoons"),
            Ingredient("Milk", "1.0 cup"),
            Ingredient("Honey", "1.0 tablespoon"),
            Ingredient("Vanilla extract", "0.5 teaspoon")
        ),
        steps = listOf(
            "In a blender, combine the frozen banana, peanut butter, milk, honey, and vanilla extract.",
            "Blend until smooth and creamy.",
            "Pour into a glass and enjoy!"
        ),
        servings = 1
    )


)

val SAMPLERECIPES: List<RecipeModel> =
    lunchRecipes + breakfastRecipes + snackRecipes + supperRecipes


