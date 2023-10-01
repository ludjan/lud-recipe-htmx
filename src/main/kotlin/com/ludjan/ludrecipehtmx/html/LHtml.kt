package com.ludjan.ludrecipehtmx.html

import com.ludjan.ludrecipehtmx.html.LBody
import com.ludjan.ludrecipehtmx.html.LElement
import com.ludjan.ludrecipehtmx.html.LHead

class LHtml(head: LHead, body: LBody): LElement("html", null, listOf(head, body)) {
}