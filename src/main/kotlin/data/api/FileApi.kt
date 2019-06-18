package data.api

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FileApi {
    @GET("v3/file/{team}/{id}")
    fun getFile(
        @Path("team") teamIndex: Long,
        @Path("id") fileId: String,
        @Query("msg") msgNumber: Long? = null,
        @Query("feed") feedNumber: Long? = null,
        @Query("reply") repNumber: Long? = null
    ): Deferred<ResponseBody>
}