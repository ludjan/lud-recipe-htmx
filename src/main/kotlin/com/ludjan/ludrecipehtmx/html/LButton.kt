package com.ludjan.ludrecipehtmx.html

open class LButton(attributes: Map<String, String>?, contentList: List<LElement>?
): LElement("button", attributes, contentList) {
    constructor(content: LElement): this(null, listOf(content))
    constructor(attributes: Map<String, String>?): this(attributes, null)
    constructor(attributes: Map<String, String>?, content: LElement): this(attributes, listOf(content))
}