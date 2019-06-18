package presentation.bot

import domain.model.ChatContent
import domain.model.ChatEvent
import domain.model.PostChat
import domain.usecase.RegisterFile
import kotlinx.coroutines.*
import java.io.File
import kotlin.coroutines.CoroutineContext

class Friday(
    private val postChat: PostChat,
    private val registerFile: RegisterFile,
    private val teamIndex: Long,
    private val roomIndex: Long
) : CoroutineScope {

    sealed class State {
        object IDLE: State()
        class REGISTER(val key: String): State()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + Job() + CoroutineExceptionHandler { _, e -> e.printStackTrace() }

    private var state : State = State.IDLE

    fun onChatEvent(chat: ChatContent) {
        when {
            chat.chatContent.startsWith("@등록 ") -> {
                launch { enterRegisterState(chat.chatContent.substringAfter("@등록 ")) }
            }
            state is State.REGISTER -> {
                launch { getFile(chat) }
            }
        }
    }

    private suspend fun enterRegisterState(key: String) {
        if (key.isEmpty()) { return }
        state = State.REGISTER(key)
        postChat("파일을 업로드 해주세요.")
    }


    private suspend fun postChat(content: String) {
        postChat.get(PostChat.Param(teamIndex, roomIndex, content))
    }

    private suspend fun getFile(chat: ChatContent) {
        val key = (state as? State.REGISTER)?.key
        when {
            chat.chatFile == null -> {
                postChat("파일이 없습니다.")
            }
            key != null -> {
                registerFile(
                    RegisterFile.Param(
                        chat.teamIndex,
                        chat.chatFile.id,
                        chat.chatIndex,
                        chat.chatFile.name,
                        (state as State.REGISTER).key
                    )
                )
                postChat("등록 완료")
            }
            else -> {
                /* no-op */
            }
        }
    }

    private suspend fun postFile(filePath: String) {

    }
}