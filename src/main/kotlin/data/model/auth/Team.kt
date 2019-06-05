package data.model.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Team(
    @Json(name = "team_index") val teamIndex: Long,
    @Json(name = "user_count") val userCount: Int,
    @Json(name = "user_idx") val userIndex: Long,
    @Json(name = "user_disk_size") val userDiskSize: Int,
    @Json(name = "upload_limit") val uploadLimit: Int,
    @Json(name = "license_type") val licenseType: String,
    @Json(name = "start_date") val startDate: String,
    @Json(name = "end_date") val endDate: String
)