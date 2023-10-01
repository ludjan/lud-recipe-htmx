package com.ludjan.ludrecipehtmx.html

class LLabel(for_attribute: String, content: String):
    LElement("label", mapOf("for" to for_attribute), listOf(LText(content)))