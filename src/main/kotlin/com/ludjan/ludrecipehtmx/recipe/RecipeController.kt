package com.ludjan.ludrecipehtmx.recipe

import com.ludjan.ludrecipehtmx.RendererService
import com.ludjan.ludrecipehtmx.html.*
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/recipe")
class RecipeController(
    val rendererService: RendererService,
    val recipeService: RecipeService
) {

    @GetMapping
    fun getAddRecipe() =
        ResponseEntity.ok(
            LHtml(
                LHead(LScript("https://unpkg.com/htmx.org@1.9.6")),
                LBody(
                    RecipeComponents.addForm()
                )
            ).render()
        )

    @GetMapping("/form")
    fun getAddForm() =
        ResponseEntity.ok(RecipeComponents.addForm().render())

    @PostMapping(CREATE)
    fun createRecipe(
        @RequestBody(required = true) body: String
    ): ResponseEntity<String> {
        val params = body.split("&")
        println("Add-recipe called, $params")
        val name = params.find { it.startsWith("name") }?.split("=")?.last()
        println(name)
        name?.let { RecipeEntity(recipeService.nextAvailableId(), it, listOf()) }?.let { recipeService.addRecipe(it) }

        return ResponseEntity.ok(LDiv(rendererService.recipeSection()).render())
    }

    @PostMapping("/$EDIT/{recipeId}")
    fun updateRecipe(
        @PathVariable("recipeId") recipeId: Int,
        @RequestBody requestBody: MultiValueMap<String, String>,
    ): ResponseEntity<*> {
        UpdateRecipeRequest
            .fromIdAndMultiValueMap(recipeId, requestBody)
            .let {
                println("About to update name for $recipeId to $it")
                recipeService.updateRecipe(it)
            }
        return ResponseEntity.ok(LDiv(rendererService.recipeSection()).render())
    }

    @GetMapping("$EDIT/{recipeId}")
    fun getPopulatedRecipeForm(
        @PathVariable("recipeId") recipeId: Int
    ) = recipeService.getRecipes()
            .find { it.id == recipeId }
            ?.let { recipe: RecipeEntity ->
                println("Found recipe which has name ${recipe.name}")
                ResponseEntity.ok(RecipeComponents.editForm(recipe).render())
            }


    companion object {
        private const val BASE = "/recipe"
        const val CREATE = "create"
        const val CREATE_PATH = "$BASE/$CREATE"
        const val EDIT = "edit"
        const val EDIT_PATH = "$BASE/$EDIT"
    }
}