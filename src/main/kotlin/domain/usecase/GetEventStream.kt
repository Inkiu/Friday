package domain.usecase

import domain.model.ChatEvent
import domain.model.User
import domain.repo.AuthRepository
import domain.repo.EventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetEventStream @Inject constructor(
    private val authRepository: AuthRepository,
    private val eventRepository: EventRepository
) {
    @ExperimentalCoroutinesApi
    suspend fun get(scope: CoroutineScope): ReceiveChannel<ChatEvent> {
        val users = authRepository.getMyProfiles()
        return scope.produce {
            while (isActive) {
                val result = runCatching {
                    eventRepository.getChatEvents()
                        .filter {
                            !users.contains(User(it.teamIndex, it.userIndex))
                        }.forEach {
                            send(it)
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