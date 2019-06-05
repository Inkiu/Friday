package data.repo

import data.api.EventApi
import domain.model.ChatEvent
import domain.repo.EventRepository

class EventRepositoryImpl(
    private val eventApi: EventApi
) : EventRepository {

    override suspend fun getChatEvents(): List<ChatEvent> {
        return eventApi.getEventsAsync().await().events
            .filter { it.isChat() }
            .mapNotNull { it.chatEvent }
            .map { ChatEvent(it.teamIndex, it.roomIndex, it.userIndex, it.chatIndex, it.roomName) }
    }

}