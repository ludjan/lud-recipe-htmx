package com.ludjan.ludrecipehtmx.recipe

import com.ludjan.ludrecipehtmx.html.*

object RecipeComponents {

    fun addForm(): LElement =
        LDiv(
            mapOf(
                "id" to "add-recipe-section",
                "class" to "container",
                "style" to "background-color: grey;"
            ),
            listOf(
                LDivRow(
                    LDiv(
                        mapOf("class" to "text-center"),
                        LText("<h2>Add recipe</h2>")
                    )
                ),
                recipeForm("hx-post" to RecipeController.CREATE_PATH)
            )
        )

    fun editForm(
        id: Int,
        name: String
    ): LElement =
        LDiv(
            mapOf(
                "id" to "edit-recipe-section",
                "class" to "container",
                "style" to "background-color: lightgrey;"
            ),
            listOf(
                LDivRow(
                    LDiv(
                        mapOf("class" to "text-center"),
                        LText("<h2>Edit recipe</h2>")
                    )
                ),
                recipeForm("hx-post" to "${RecipeController.EDIT_PATH}/$id", name)
            )
        )

    fun recipeForm(
        hxMethod: Pair<String, String>,
        name: String = "",
        // add more here
    ): LElement =
        LDiv(
            LForm(
                mapOf(
                    hxMethod,
                    "hx-params" to "*",
                    "hx-swap" to "innerHTML",
                    "hx-target" to "#main-content",
                ),
                listOf(
                    LDivRow(
                        listOf(
                            LLabel(for_attribute = "name", content = "Name"),
                            LText("<br/>"),
                            LInput(type = "text", name = "name", id = "name", value= name),
                            LText("<br/>"),
                        )
                    ),
                    LDivRow(
                        LButtonSubmit("Submit")
                    )
                )
            )
        ).also { println ("Returning ${it.render()}") }
}