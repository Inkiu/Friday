package domain.usecase

import domain.repo.FileRepository
import javax.inject.Inject

class RegisterFile @Inject constructor(
    private val fileRepository: FileRepository
) {
    suspend operator fun invoke(param: Param) {
        fileRepository.registerFile(
            param.teamIndex,
            param.fileId,
            param.messageId,
            param.fileName,
            param.key
        )
    }

    data class Param(
        val teamIndex: Long,
        val fileId: String,
        val messageId: Long,
        val fileName: String,
        val key: String
    )
}