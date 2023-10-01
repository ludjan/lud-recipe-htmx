package com.ludjan.ludrecipehtmx.recipe

import org.springframework.util.MultiValueMap

class UpdateRecipeRequest(
    val id: Int,
    val name: String,
    val steps: List<String>
) {
    companion object {
        fun fromIdAndMultiValueMap(id: Int, multiMap: MultiValueMap<String, String>): UpdateRecipeRequest {
            val name = multiMap["name"]?.first() ?: throw RuntimeException("Bad")
            val steps: List<String> = findFirst10Steps(multiMap)


            return UpdateRecipeRequest(id, name, steps)
        }

        fun findFirst10Steps(multiMap: MultiValueMap<String, String>): List<String> =
            (1..10).mapNotNull { i ->
                multiMap["step-$i"]?.first()
            }

    }
}