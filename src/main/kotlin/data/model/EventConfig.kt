package data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventConfig(
    @Json(name = "version") val version: String,
    @Json(name = "lp_idle_time") val requestIdleTime: Int,
    // long-polling 요청 대기 시간 (이전 요청에 이벤트 없었을 경우 sleep) [단위: 초]
    @Json(name = "lp_wait_timeout") val responseTimeout: Int
    // long-polling 응답 대기 시간 (receive timeout 5초 정도 더 길게 설정) [단위: 초]
)