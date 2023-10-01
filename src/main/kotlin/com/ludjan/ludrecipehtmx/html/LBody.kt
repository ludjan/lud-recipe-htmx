package com.ludjan.ludrecipehtmx.html

class LBody(attributes: Map<String,String>?, contentList: List<LElement>?): LElement("body", null, contentList) {
    constructor(content: LElement): this(null, listOf(content))
    constructor(contentList: List<LElement>?): this(null, contentList)
}