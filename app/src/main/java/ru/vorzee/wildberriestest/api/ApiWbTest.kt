package ru.vorzee.wildberriestest.api

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.util.concurrent.TimeUnit

object ApiWbTest {
    private const val apiUrl =
        "https://vmeste.wildberries.ru/api/avia-service/twirp/aviaapijsonrpcv1.WebAviaService/GetCheap"
    private val client = OkHttpClient.Builder()
        .callTimeout(10, TimeUnit.SECONDS)
        .build()

    @Throws(IOException::class)
    fun getListData(startLocationCode: String = "LED"): String {
        val startLocationCodeRequest = "{\"startLocationCode\":\"$startLocationCode\"}"
        val body = startLocationCodeRequest.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val request = Request.Builder()
            .url(apiUrl)
            .header("Accept", "application/json, text/plain, /")
            .header("Content-Type", "application/json")
            .post(body)
            .build()

        val response = client.newCall(request).execute()
        return response.body?.string() ?: ""
    }
}