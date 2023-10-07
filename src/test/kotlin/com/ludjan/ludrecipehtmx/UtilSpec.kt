package com.ludjan.ludrecipehtmx

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.Random

@SpringBootTest
class UtilSpec {
    @Test
    fun test() {
        (1..100).forEach { _ ->
            (1..10)
                .random()
                .also { println("Random from range is $it") }
                .multipleOf(2)
                .also {
                    if (it) println("It is divisible by 2")
                    else println("Not divisible by two")
                }
        }
    }
}

fun IntRange.random(): Int =
    Random()
        .nextInt(this.last)
        .let { this.elementAt(it) }

fun Int.multipleOf(product: Int) = product % this == 0