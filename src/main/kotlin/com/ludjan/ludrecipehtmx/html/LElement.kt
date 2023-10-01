package com.ludjan.ludrecipehtmx.html

open class LElement(val tag: String, val attributes: Map<String, String>?, val content: List<LElement>?) {
    open fun render(): String {
        var attr = ""
        attributes?.forEach { (k, v) ->
            attr += """ $k="$v""""
        }

        var body = ""
        content?.forEach {
            body += it.render()
        }
        return "<$tag${attr}>$body</$tag>"
    }
}