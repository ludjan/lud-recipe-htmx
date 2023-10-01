package com.ludjan.ludrecipehtmx.html

open class LDiv(attributes: Map<String, String>?, contentList: List<LElement>?): LElement("div", attributes, contentList) {
    constructor(content: LElement): this(null, listOf(content))
    constructor(contentList: List<LElement>): this(null, contentList)
    constructor(attributes: Map<String, String>?, content: LElement): this(attributes, listOf(content))
}