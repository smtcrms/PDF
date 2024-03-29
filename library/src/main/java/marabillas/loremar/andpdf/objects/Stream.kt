package marabillas.loremar.andpdf.objects

import marabillas.loremar.andpdf.PDFFileReader
import marabillas.loremar.andpdf.filters.DecoderFactory
import java.io.RandomAccessFile

internal open class Stream(file: RandomAccessFile, start: Long) : Indirect(file, start) {
    val dictionary = PDFFileReader(file).getDictionary(start)
    var streamData = byteArrayOf()
        private set

    init {
        var length = dictionary["Length"]
        if (length is Reference) {
            length = length.resolve()
        }
        streamData = ByteArray((length as Numeric).value.toInt())

        file.seek(start)
        var s = ""
        while (!s.endsWith("stream", true))
            s = file.readLine()
        file.readFully(streamData)
    }

    fun decodeEncodedStream(): ByteArray {
        val filterObj = dictionary["Filter"]

        var data = streamData
        when (filterObj) {
            is PDFArray -> for (filterEntry in filterObj) {
                if (filterEntry is Name) {
                    val decoder = DecoderFactory().getDecoder(filterEntry.value, dictionary)
                    data = decoder.decode(data)
                }
            }
            is Name -> {
                val decoder = DecoderFactory().getDecoder(filterObj.value, dictionary)
                data = decoder.decode(data)
            }
        }

        return data
    }

}