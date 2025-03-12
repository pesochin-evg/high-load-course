package ru.quipy.common.utils
import java.util.concurrent.Semaphore

class RequestLimiter(private val maxParallelRequests: Int) {
    private val semaphore = Semaphore(maxParallelRequests)

    fun <T> execute(block: () -> T): T {
        semaphore.acquire()
        try {
            return block()
        } finally {
            semaphore.release()
        }
    }
}