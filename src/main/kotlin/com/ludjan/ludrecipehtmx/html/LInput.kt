package com.ludjan.ludrecipehtmx.html

class LInput(type: String, name: String, id:String, value: String?):
    LElement(
        "input",
        mapOf(
            "type" to type,
            "name" to name,
            "id" to id,
            "value" to (value ?: ""),
        ),
        null) {
}