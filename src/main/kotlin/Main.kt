import app.App
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    App().start()

    runBlocking { // TODO - remove
        delay(Long.MAX_VALUE)
    }
}