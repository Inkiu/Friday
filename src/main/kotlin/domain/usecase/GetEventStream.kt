package domain.usecase

import domain.model.event.ChatEvent
import domain.repo.EventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.isActive
import javax.inject.Inject

class GetEventStream @Inject constructor(
    private val eventRepository: EventRepository
) {
    @ExperimentalCoroutinesApi
    suspend fun get(scope: CoroutineScope): ReceiveChannel<ChatEvent> {
        return scope.produce {
            while(isActive) {
                val result = runCatching {
                    eventRepository.getEvents().forEach { event ->
                        if (event.isChat()) {
                            println(event)
                            send(event.chatEvent!!)
                        }
                    }
                }
                if (result.isFailure) {
                    println(result.exceptionOrNull())
                    break
                }
            }
        }
    }
}