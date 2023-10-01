package com.ludjan.ludrecipehtmx

import com.ludjan.ludrecipehtmx.html.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.nio.file.FileSystem

@RestController
class RootController(
    val rendererService: RendererService,
) {

    @GetMapping("/")
    fun getRoot() =
        ResponseEntity.ok(
            LHtml(
                LHead(
                    listOf(
                        LText("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\n"),
                        LScript("https://unpkg.com/htmx.org@1.9.6"),
                        LText("""<link rel="stylesheet" href="styles.css">""")
                    )
                ),
                LBody(
                    listOf(
                        LDiv(null, rootHeader()),
                        LDiv(
                            mapOf(
                                "id" to "main-content",
                                "class" to "container",
                            ),
                            rendererService.recipeSection()
                        ),
                    )
                )
            ).render()
        )

    fun rootHeader(): List<LElement> =
        listOf(
            LDiv(
                mapOf(
                    "style" to "text-align:center;margin-bottom:20px;"
                ),
                LText("<h1>Recipes</h1>")
            )
        )

}