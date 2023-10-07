package com.ludjan.ludrecipehtmx.recipe

import com.ludjan.ludrecipehtmx.html.*
import java.util.*

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
                        formTextInput("name", recipeEntity?.name ?: "")
                    ),
                    LDivRow(
                        recipeEntity
                            ?.steps
                            ?.mapIndexed { i, s -> rowForStep(i, s) }
                            ?.plus(buttonToAddRowWithIndex(recipeEntity.steps.size))
                            ?: firstRowAndAddButton()
                    ),
                    LDivRow(
                        LButtonSubmit("Submit")
                    )
                )
            )
        ).also { println("Returning ${it.render()}") }

    fun formTextInput(fieldName: String, value: String) =
        listOf(
            LLabel(for_attribute = fieldName, content = fieldName.capitalizeFirstLetter()),
            LText("<br/>"),
            LInput(type = "text", name = fieldName, id = fieldName, value = value),
            LText("<br/>"),
        )

    fun firstRowAndAddButton(): List<LElement> =
        listOf(
            rowForStep(0, null),
            buttonToAddRowWithIndex(1)
        )

    fun indexRowAndAddButton(index: Int) =
        LDiv(
            listOf(
                rowForStep(index, null)
            ).plus(
                buttonToAddRowWithIndex(index + 1)
            )
        )

    fun rowForStep(index: Int, step: Step?): LElement =
        LDivRow(
            listOf(
                LLabel(for_attribute = "step-${index + 1}", content = "Step ${index + 1}"),
                LText("<br/>"),
                LInput(
                    type = "text",
                    name = "step-${index + 1}",
                    id = "step-${index + 1}",
                    value = step?.description
                ),
                LText("<br/>"),
            )
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

    fun String.capitalizeFirstLetter() =
        this.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault())
            else it.toString()
        }
}