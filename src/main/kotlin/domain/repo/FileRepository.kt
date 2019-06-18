package domain.repo

import java.io.File

interface FileRepository {
    suspend fun registerFile(teamIndex: Long, fileId: String, msgId: Long, fileName: String, key: String)
    suspend fun getFile(key: String): File?
}