package domain.repo

import domain.model.event.Event

interface EventRepository {
    suspend fun getEvents(): List<Event>
}