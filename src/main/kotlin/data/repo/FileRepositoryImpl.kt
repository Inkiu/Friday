package data.repo

import data.api.FileApi
import di.ImageFolder
import domain.repo.FileRepository
import java.io.*
import javax.inject.Inject

class FileRepositoryImpl(
    private val imageFolderPath: String,
    private val fileApi: FileApi
): FileRepository {

    private val folder = File(imageFolderPath)

    override suspend fun registerFile(teamIndex: Long, fileId: String, msgId: Long, fileName: String, key: String) {
        val responseBody = fileApi.getFile(teamIndex, fileId, msgId).await()
        val inputStream = responseBody.byteStream()
        val outputStream = BufferedOutputStream(FileOutputStream(File(folder, getCacheName(fileName, key))))
        val success = inputStream.writeToOutput(outputStream)
        if (!success) throw IOException("cannot write file")
    }

    override suspend fun getFile(key: String): File? {
        return null
    }

    private fun getCacheName(originName: String, key: String): String { // TODO journal
        return "$key^$originName"
    }

    private fun getFileName(key: String): String? { // TODO journal
        val keyPrefix = "$key^"
        return folder.listFiles().firstOrNull { it.name.startsWith(keyPrefix) }
            ?.name
            ?.substringAfter(keyPrefix)
    }

    private fun InputStream.writeToOutput(outputStream: OutputStream): Boolean {
        try {
            val fileReader = ByteArray(1024)
            var fileSizeSaved = 0
            while(true) {
                val read = read(fileReader)
                if (read == -1) break
                outputStream.write(fileReader, 0, read)
                fileSizeSaved += read
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        } finally {
            close()
            outputStream.close()
        }
        return true
    }
}