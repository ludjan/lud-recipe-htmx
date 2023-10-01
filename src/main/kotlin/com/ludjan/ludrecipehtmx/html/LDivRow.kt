package com.ludjan.ludrecipehtmx.html

class LDivRow(attributes: Map<String, String>?, content: List<LElement>?
): LDiv(attributes?.plus("class" to "row"), content) {
    constructor(content: LElement?): this(null, content?.let { listOf(it) })
    constructor(attributes: Map<String, String>?, content: LElement): this(attributes, listOf(content))
    constructor(contentList: List<LElement>?): this(null, contentList)
}