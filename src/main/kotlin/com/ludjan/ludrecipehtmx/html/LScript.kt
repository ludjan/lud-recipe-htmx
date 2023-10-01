package com.ludjan.ludrecipehtmx.html

class LScript(attributes: Map<String, String>?, contentList: List<LElement>?
): LElement("script", attributes, contentList) {

    constructor(content: LElement): this(null, listOf(content))
    constructor(contentList: List<LElement>): this(null, contentList)
    constructor(attributes: Map<String, String>): this(attributes, null)
    constructor(src: String): this(mapOf("src" to src))
}