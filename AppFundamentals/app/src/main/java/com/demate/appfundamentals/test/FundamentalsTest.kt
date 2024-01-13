package com.demate.appfundamentals

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class FundamentalsTest {

    @Test
    fun `prints greeting with Kotlin`() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        main()

        assertEquals("Hello, Kotlin!!!\n", outContent.toString())
    }

    @Test
    fun `prints greeting with different name`() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        var name = "Java"
        println("Hello, $name!!!")

        assertEquals("Hello, Java!!!\n", outContent.toString())
    }
}