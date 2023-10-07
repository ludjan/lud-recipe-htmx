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
        recipeEntity: RecipeEntity
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
                recipeForm("hx-post" to "${RecipeController.EDIT_PATH}/${recipeEntity.id}", recipeEntity)
            )
        )

    fun recipeForm(
        hxMethod: Pair<String, String>,
        recipeEntity: RecipeEntity? = null
    ): LElement =
        LDiv(
            LForm(
                mapOf(
                    hxMethod,
                    "hx-params" to "*",
                    "hx-swap" to "innerHTML",
                    "hx-target" to "#main-content",
                    "id" to "edit-form"
                ),
                listOf(
                    LDivRow(
                        listOf(
                            LLabel(for_attribute = "name", content = "Name"),
                            LText("<br/>"),
                            LInput(type = "text", name = "name", id = "name", value= recipeEntity?.name ?: ""),
                            LText("<br/>"),
                        )
                    ),
                    LDivRow(
                        recipeEntity
                            ?.steps
                            ?.mapIndexed { i, s -> row(i, s) }
                            ?.plus(buttonToAddRowWithIndex(recipeEntity.steps.size))
                            ?: firstRowAndAddButton()
                    ),
                    LDivRow(
                        LButtonSubmit("Submit")
                    )
                )
            )
        ).also { println ("Returning ${it.render()}") }

    fun row(index: Int, step: Step?): LElement =
        LDivRow(
            listOf(
                LLabel(for_attribute = "step-${index+1}", content = "Step ${index + 1}"),
                LText("<br/>"),
                LInput(
                    type = "text",
                    name = "step-${index+1}",
                    id = "step-${index+1}",
                    value= step?.description),
                LText("<br/>"),
            )
        )

    fun firstRowAndAddButton(): List<LElement> =
        listOf(
            row(0, null),
            buttonToAddRowWithIndex(1)
    )

    fun buttonToAddRowWithIndex(index: Int): LElement =
        LButton(
            mapOf(
                "id" to "add-row-button",
                "hx-get" to "/recipe/add-row/$index",
                "hx-swap" to "outerHTML",
                "hx-target" to "#add-row-button"
            ),
            LText("Add row")
        )

//    fun buttonToAddRowWithIndex(index: Int): List<LElement> =
//        listOf(
//            LButton(
//                mapOf(
//                    "id" to "add-row-button",
//                    "hx-get" to "/recipe/add-row/$index",
//                    "hx-swap" to "beforebegin",
//                    "hx-target" to "#add-row-button"
//                ),
//                LText("Add row")
//            )
//        )


    fun stepRow(index: Int, description: String? = "") =
        LDivRow(
            listOf(
                LLabel(for_attribute = "step-${index+1}", content = "Step ${index + 1}"),
                LText("<br/>"),
                LInput(
                    type = "text",
                    name = "step-${index+1}",
                    id = "step-${index+1}",
                    value= description),
                LText("<br/>"),
            ).plus(buttonToAddRowWithIndex(index + 1))
        )
}