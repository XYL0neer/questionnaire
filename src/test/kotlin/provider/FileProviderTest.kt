package provider

import getLines
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FileProviderTest {

    val sut = FileProvider()

    @Test
    fun `readFile returnsLines`() {
        val result = sut.readFile()

        assertEquals(getLines(), result)
    }
}