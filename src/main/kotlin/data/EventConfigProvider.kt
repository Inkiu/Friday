package data

import data.model.EventConfig

class EventConfigProvider {
    private var eventConfig: EventConfig? = null

    fun registerConfig(config: EventConfig) {
        synchronized(this) {
            this.eventConfig = config
        }
    }

    fun getEventIdelTime(): Int? {
        return eventConfig?.requestIdleTime
    }

    fun getEventWaitTime(): Int? {
        return eventConfig?.responseTimeout
    }

}