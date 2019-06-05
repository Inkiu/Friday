package presentation.bot

import domain.model.ChatContent
import domain.model.ChatEvent
import domain.model.PostChat

class Friday(
    private val postChat: PostChat
) {
    fun onChatEvent(chat: ChatContent) {
        println("${this}" + chat)
    }
}