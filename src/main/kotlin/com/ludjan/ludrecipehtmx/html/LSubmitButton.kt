package com.ludjan.ludrecipehtmx.html

class LButtonSubmit(
    attributes: Map<String, String>?,
    contentList: List<LElement>?
): LButton(
    attributes
        ?.plus("type" to "submit",)
        ?.plus("value" to "submit"),
    contentList) {
    constructor(text: String): this(null, listOf(LText(text)))
}