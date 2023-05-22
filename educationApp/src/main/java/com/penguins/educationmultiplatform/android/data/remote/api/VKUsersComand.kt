package com.penguins.educationmultiplatform.android.data.remote.api

import com.penguins.educationmultiplatform.android.data.remote.dto.profile.VKProfile
import com.penguins.educationmultiplatform.android.data.remote.dto.vk.VKPhotoResponse
import com.vk.api.sdk.VKApiJSONResponseParser
import com.vk.api.sdk.VKApiManager
import com.vk.api.sdk.VKMethodCall
import com.vk.api.sdk.exceptions.VKApiIllegalResponseException
import com.vk.api.sdk.internal.ApiCommand
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.json.JSONException
import org.json.JSONObject
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class VKUsersCommand(private val uids: IntArray = intArrayOf()) :
    ApiCommand<Triple<String?, String?, String?>>() {
    override fun onExecute(manager: VKApiManager): Triple<String, String, String> {


        val call = VKMethodCall.Builder()
            .method("photos.get")
            .args("album_id", "profile")
            .args("rev", "1")
            .args("count", "1")
            .version(manager.config.version)
            .build()
        val callProfile = VKMethodCall.Builder()
            .method("account.getProfileInfo")
            .version(manager.config.version)
            .build()

        val url =
            manager.execute(call, ResponseApiParser()).response.items.first().sizes.last().url
        val profile = manager.execute(callProfile, ResponseProfileParser()).response
        val age = OffsetDateTime.now().year - LocalDate.parse(
            profile.bdate,
            DateTimeFormatter.ofPattern("dd.MM.yyyy")
        ).year
        return Triple(url, profile.first_name, age.toString())

    }

    companion object {
        const val CHUNK_LIMIT = 900
    }

    private class ResponseApiParser : VKApiJSONResponseParser<VKPhotoResponse> {
        override fun parse(responseJson: JSONObject): VKPhotoResponse {
            try {
                val json = Json {
                    ignoreUnknownKeys = true
                }
                val obj = json.decodeFromString<VKPhotoResponse>(responseJson.toString())
                return obj
            } catch (ex: JSONException) {
                throw VKApiIllegalResponseException(ex)
            }
        }
    }

    private class ResponseProfileParser : VKApiJSONResponseParser<VKProfile> {
        override fun parse(responseJson: JSONObject): VKProfile {
            try {
                val json = Json {
                    ignoreUnknownKeys = true
                }
                val obj = json.decodeFromString<VKProfile>(responseJson.toString())
                return obj
            } catch (ex: JSONException) {
                throw VKApiIllegalResponseException(ex)
            }
        }
    }

}