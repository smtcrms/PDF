package marabillas.loremar.andpdf.font.encoding

import android.support.v4.util.SparseArrayCompat
import marabillas.loremar.andpdf.utils.exts.set

internal class MacOSRomanEncoding {
    companion object : EncodingSource {
        private val encoding = SparseArrayCompat<String>()

        init {
            MacRomanEncoding.putAllTo(encoding)
            encoding[173] = "notequal"
            encoding[176] = "infinity"
            encoding[178] = "lessequal"
            encoding[179] = "greaterequal"
            encoding[182] = "partialdiff"
            encoding[183] = "summation"
            encoding[184] = "product"
            encoding[185] = "pi"
            encoding[186] = "integral"
            encoding[189] = "Omega"
            encoding[195] = "radical"
            encoding[197] = "approxequal"
            encoding[198] = "Delta"
            encoding[215] = "lozenge"
            encoding[219] = "Euro"
            encoding[240] = "apple"
        }

        override fun putAllTo(target: SparseArrayCompat<String>) {
            target.putAll(encoding)
        }

        operator fun get(code: Int): String? {
            return encoding[code]
        }
    }
}