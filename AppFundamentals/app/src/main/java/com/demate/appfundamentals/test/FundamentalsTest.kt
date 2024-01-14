
import com.demate.appfundamentals.main
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class FundamentalsTest {

    @Test
    fun `main prints correct name and age`() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        main()

        val expectedOutput = "My name is João and I'm 20 years old\n"

        assertEquals(expectedOutput, outContent.toString())
    }
}
