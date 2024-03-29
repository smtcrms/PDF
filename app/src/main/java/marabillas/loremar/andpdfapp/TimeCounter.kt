package marabillas.loremar.andpdfapp

import java.util.concurrent.TimeUnit

class TimeCounter {
    companion object {
        private var t0 = System.nanoTime()

        fun reset() {
            t0 = System.nanoTime()
        }

        fun getTimeElapsed(): Long {
            val t1 = System.nanoTime()
            val dt = t1 - t0
            return TimeUnit.NANOSECONDS.toMillis(dt)
        }
    }
}