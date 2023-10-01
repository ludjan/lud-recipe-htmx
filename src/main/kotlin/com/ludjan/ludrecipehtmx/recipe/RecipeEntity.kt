package com.ludjan.ludrecipehtmx.recipe

data class RecipeEntity(val id: Int, val name: String, val steps: List<Step>)

data class Step(val description: String)