package com.ludjan.ludrecipehtmx.recipe

import org.springframework.stereotype.Service

@Service
class RecipeService(
    val recipeRepository: RecipeRepository
) {
    fun getRecipes(): List<RecipeEntity> = recipeRepository.getRecipes()

    fun addRecipe(recipe: RecipeEntity): Unit = recipeRepository.addRecipe(recipe)

    fun nextAvailableId(): Int =
        recipeRepository.getRecipes().maxOf { it.id } + 1

    fun updateRecipe(updateRecipeRequest: UpdateRecipeRequest): Unit {
        recipeRepository.updateRecipe(updateRecipeRequest)
    }
}