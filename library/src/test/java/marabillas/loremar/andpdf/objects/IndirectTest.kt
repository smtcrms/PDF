package marabillas.loremar.andpdf.objects

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.io.RandomAccessFile

class IndirectTest {
    @Test
    fun testIndirect() {
        val path = javaClass.classLoader.getResource("samplepdf1.4.pdf").path
        val file = RandomAccessFile(path, "r")
        val obj = Indirect(file, 99032L)
        val content = obj.extractContent()
        assertThat(obj.obj, `is`(3))
        assertThat(obj.gen, `is`(0))
        val expected = "<</Type /Font " +
                "/Subtype /Type0 " +
                "/BaseFont /ArialMT " +
                "/Encoding /Identity-H " +
                "/DescendantFonts [18 0 R] " +
                "/ToUnicode 19 0 R>>"
        assertThat(content, `is`(expected))
    }
}