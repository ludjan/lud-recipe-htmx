package com.ludjan.ludrecipehtmx.html

import com.ludjan.ludrecipehtmx.html.LElement

class LText(val text: String): LElement("no-div", null, null) {
    override fun render(): String = text
}