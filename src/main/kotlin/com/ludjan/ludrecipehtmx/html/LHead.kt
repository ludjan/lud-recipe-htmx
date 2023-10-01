package com.ludjan.ludrecipehtmx.html

import com.ludjan.ludrecipehtmx.html.LElement

class LHead(contentList: List<LElement>?): LElement("head", null, contentList) {
    constructor(content: LElement): this(listOf(content))
}