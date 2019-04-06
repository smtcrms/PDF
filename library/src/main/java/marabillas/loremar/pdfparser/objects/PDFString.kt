package marabillas.loremar.pdfparser.objects

import marabillas.loremar.pdfparser.filters.DecoderFactory
import java.math.BigInteger

internal class PDFString(private var string: String) : Any(), PDFObject {
    val original = string

    var value: String = ""
        get() {
            when {
                string.startsWith("(") && string.endsWith(")") -> {
                    var s = string.substringAfter("(").substringBeforeLast(")")

                    // Convert octal character codes to their proper character representations
                    "\\\\\\d{1,3}"
                        .toRegex()
                        .findAll(field)
                        .associateBy( // Create a map where key=character code and value=character representation of code
                            { it.value },
                            {
                                // Get the bytes equivalent of given octal characters and convert to string.
                                val b = BigInteger(it.value.substringAfter("\\"), 8)
                                String(b.toByteArray())
                            })
                        .forEach {
                            // Replace all occurrences of character code with its character representation
                            s = s.replace(it.key, it.value)
                        }
                    value = s
                }
                string.startsWith("<") && string.endsWith(">") -> {
                    string = string.substringAfter("<").substringBeforeLast(">")
                    if (string.length % 2 != 0) string += "0"

                    val decoder = DecoderFactory().getDecoder("ASCIIHexDecode")
                    val bytes = decoder.decode(string.toByteArray())
                    value = String(bytes, Charsets.UTF_16)
                }
                else -> throw IllegalArgumentException("A PDF string object should either be enclosed in () or <>.")
            }
            return field
        }
        private set

    override fun toString(): String {
        return value
    }

    override operator fun equals(other: Any?): Boolean {
        return value == other
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    operator fun plus(other: String): String {
        return value + other
    }
}

internal fun String.toPDFString(): PDFString {
    return PDFString(this)
}