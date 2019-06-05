package domain.repo

import domain.model.ChatEvent

interface EventRepository {
    suspend fun getChatEvents(): List<ChatEvent>
}