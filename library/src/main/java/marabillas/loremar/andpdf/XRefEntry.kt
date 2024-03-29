package marabillas.loremar.andpdf

internal data class XRefEntry(
    var obj: Int,                       // Object number
    var pos: Long = 0,                  // Byte offset of the object in the file
    var gen: Int = 65535,               // Generation number
    var inUse: Boolean = true,          // In use? or Free?
    var compressed: Boolean = false,    // Is it compressed in an object stream
    var objStm: Int = -1,               // Object number of object stream if compressed
    var index: Int = -1,                // Object's index in the object stream if compressed
    var nullObj: Boolean = false        // Is it a reference to a null object?
) {
    override fun toString(): String {
        return "XRefEntry(obj:$obj pos:$pos gen:$gen inUse:$inUse compressed:$compressed objStm:$objStm index:$index" +
                " nullObj:$nullObj)"
    }
}