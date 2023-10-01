package com.ludjan.ludrecipehtmx.recipe

import org.springframework.stereotype.Repository

@Repository
class RecipeRepository {

    private var recipes = listOf(
        RecipeEntity(1, "Pancakes",
            listOf(
                Step("Put some flowers in there"),
                Step("Put some milk in there!"),
                Step("Eggs!"),
                Step("Whip it and fry like hell boi")
            )
        ),
        RecipeEntity(2, "Rice",
            listOf(
                Step("Rice"),
                Step("boil!"),
                Step("Boil!")
            )
        )
    )

    fun getRecipes(): List<RecipeEntity> = recipes

    fun addRecipe(recipe: RecipeEntity): Unit {
        recipes = recipes + listOf(recipe)
    }


    fun updateRecipe(recipeId: Int, name: String): Unit {
        recipes = recipes.map { recipe ->
            if (recipe.id == recipeId) {
                RecipeEntity(recipeId, name, recipe.steps)
            } else {
                recipe
            }
        }
    }
}