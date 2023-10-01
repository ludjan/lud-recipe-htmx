package com.ludjan.ludrecipehtmx

import com.ludjan.ludrecipehtmx.recipe.RecipeController
import com.ludjan.ludrecipehtmx.recipe.RecipeService
import com.ludjan.ludrecipehtmx.html.*
import org.springframework.stereotype.Service

@Service
class RendererService (
    val recipeService: RecipeService
) {
    fun recipeSection(): List<LElement> =
        listOf(
            LDiv(
                mapOf("id" to "recipe-section"),
                listOf(
                    LDivRow(
                        LDiv(
                            mapOf("class" to "text-center"),
                            LText("<h2>Recipe list</h2>")
                        )
                    ),
                    LDivRow(
                        LDiv(
                            mapOf("class" to "offset-sm-2 col-sm-8"),
                            recipeList()
                        )
                    ),
                    LDiv(
                        mapOf(
                            "class" to "row d-flex justify-content-center"
                        ),
                        LButton(
                            mapOf(
                                "hx-get" to "/recipe/form",
                                "hx-swap" to "innerHTML",
                                "hx-target" to "#main-content"),
                            LText("Add recipe")
                        )
                    ),
                )
            )
        )

    fun recipeList(): List<LElement> =
        recipeService
            .getRecipes()
            .map { recipe ->
                LDiv(
                    mapOf(
                        "style" to "margin-bottom:5px;padding:5px;border: 1px solid black;",
                        "hx-get" to "${RecipeController.EDIT_PATH}/${recipe.id}",
                        "hx-swap" to "innerHTML",
                        "hx-target" to "#main-content"
                    ),
                    listOf(
                        LDiv(LText("Name: ${recipe.name}")),
                        LDiv(LText("Id: ${recipe.id}")),
                        LDiv(null,
                            recipe.steps.map { step ->
                                LDiv(null, LText("Step: ${step.description}"))
                            }
                        ),
                    )
                )
            }
}