package com.example.demo

import com.ludjan.ludrecipehtmx.html.LDiv
import com.ludjan.ludrecipehtmx.html.LText
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LElementSpec {
    @Test
    fun htmlRendering() {
        val targetHtml2 = """
            <div class="row text-center">
            <div>This is the first nested div</div>
            <div>This is the second nested div</div>
            </div>
        """.trimIndent().filter { it != '\n' }
        val targetHtml = ("This is the first nested div".inADiv() + "This is the second nested div".inADiv()).inADiv()
        println(targetHtml2)
        val generatedHtml = LDiv(
            mapOf("class" to "row text-center"),
                listOf(
                    LDiv(LText("This is the first nested div")),
                    LDiv(LText("This is the second nested div"))
                )).render()
        println(generatedHtml)
        assert(targetHtml2 == generatedHtml)
    }

    fun String.inADiv() = "<div>$this</div>"
}