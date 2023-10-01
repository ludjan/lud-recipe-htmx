package com.ludjan.ludrecipehtmx.html

import com.ludjan.ludrecipehtmx.html.LElement

class LForm(attributes: Map<String, String>?, contentList: List<LElement>?):
    LElement("form", attributes, contentList) {
    constructor(content: LElement): this(null, listOf(content))
    constructor(contentList: List<LElement>?): this(null, contentList)
}