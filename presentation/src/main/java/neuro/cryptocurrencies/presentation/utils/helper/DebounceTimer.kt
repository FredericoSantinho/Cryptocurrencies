package neuro.cryptocurrencies.presentation.utils.helper

import timber.log.Timber
import java.util.Timer
import java.util.TimerTask

class DebounceTimer(
    private var timerFirst: Timer = Timer(),
    private var timerLast: Timer = Timer()
) {

    companion object {
        const val DEFAULT_DELAY = 750L
    }

    private var canRun = true

    fun debounceRunFirst(milWait: Long = DEFAULT_DELAY, block: () -> Unit) {
        if(canRun) {
            canRun = false
            block.invoke()
            timerFirst.cancel()
            timerFirst = Timer()
            timerFirst.schedule(object : TimerTask() {
                override fun run() {
                    canRun = true
                }
            }, milWait)
        } else {
            Timber.w("Block was debounced!")
        }
    }

    fun debounceRunLast(milWait: Long = DEFAULT_DELAY, block: () -> Unit) {
        timerLast.cancel()
        timerLast = Timer()
        timerLast.schedule(object : TimerTask() {
            override fun run() {
                block.invoke()
            }
        }, milWait)
    }

    fun destroy() {
        timerLast.cancel()
        timerFirst.cancel()
    }
}
