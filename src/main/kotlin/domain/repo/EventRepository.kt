package domain.repo

import domain.model.event.Event
import domain.model.event.Events

interface EventRepository {
    suspend fun getEvents(): Events
}