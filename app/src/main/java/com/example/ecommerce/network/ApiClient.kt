package com.example.ecommerce.network

import android.content.Context
import com.example.ecommerce.utils.Constants

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class OpenAIClient {
    private val mediaType: MediaType = "application/json; charset=utf-8".toMediaTypeOrNull()!!
    private val clientGpt: OkHttpClient
    private var requestTime = 0.0
    private var readTimeout: Long = 0
    private var writeTimeout: Long = 0
    private var connectTimeout: Long = 0
    private var maxTokens = 0.0
    private var temperature = 0.0
    private var apiKey: String? = null
    private var apiUrl: String? = null
    private var model: String? = null
    private var prompt: String? = null
    private var isMaxTokensEnabled = false



    init {
        clientGpt = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .writeTimeout(writeTimeout, TimeUnit.SECONDS)
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val start = System.nanoTime()
                val request = chain.request()
                val response = chain.proceed(request)
                val end = System.nanoTime()
                val duration = end - start
                val millis = duration / 1e6
                requestTime = millis
                response
            })
            .build()
        setTimeout(25000)
    }


    fun setReadTimeout(readTimeout: Long) {
        this.readTimeout = readTimeout
    }

    fun setWriteTimeout(writeTimeout: Long) {
        this.writeTimeout = writeTimeout
    }

    fun setConnectTimeout(connectTimeout: Long) {
        this.connectTimeout = connectTimeout
    }


    fun setTimeout(timeout: Long) {
        setReadTimeout(timeout)
        setWriteTimeout(timeout)
        setConnectTimeout(timeout)
    }

    fun setApiUrl(apiUrl: String?) {
        this.apiUrl = apiUrl
    }

    fun setApiKey(apiKey: String?) {
        this.apiKey = apiKey
    }

    fun setModel(model: String?) {
        this.model = model
    }

    fun getModel(): String? {
        return model
    }

    fun setPrompt(prompt: String?) {
        this.prompt = prompt
    }

    fun getPrompt(): String? {
        return prompt
    }

    fun setMaxTokens(maxTokens: Double) {
        this.maxTokens = maxTokens
    }

    fun setMaxTokensEnabled(isMaxTokensEnabled: Boolean) {
        this.isMaxTokensEnabled = isMaxTokensEnabled
    }

    fun setTemperature(temperature: Double) {
        this.temperature = temperature
    }

    @Throws(JSONException::class)
    fun getSettings(): JSONObject {
        val json = JSONObject()
            val jsonArray = JSONArray()
            val jsonMessageObj = JSONObject()
            jsonMessageObj.put("role", "user")
            jsonMessageObj.put("content", prompt)
            jsonArray.put(jsonMessageObj)
            json.put("model", model)
            json.put("messages", jsonArray)
            json.put("temperature", temperature)
            if (isMaxTokensEnabled) json.put("max_tokens", maxTokens)

        return json
    }

    @Throws(JSONException::class)
    fun generateResponse(callback: Callback) {
        val json = getSettings()
        val body = json.toString().toRequestBody(mediaType)
        val request = Request.Builder()
            .url(apiUrl!!)
            .header("Authorization", "Bearer $apiKey")
            .header("Content-Type", "application/json")
            .post(body)
            .build()
        clientGpt.newCall(request).enqueue(callback)
    }


    @Throws(JSONException::class)
    fun getResponse(responseBody: String?): String? {
        println(responseBody)
        val jsonObject = JSONObject(responseBody)
        var resultText: String? = null

            val jsonArray = jsonObject.getJSONArray("choices")
            val jsonMessageObj = jsonArray.getJSONObject(0).getJSONObject("message")
            resultText = jsonMessageObj.getString("content")
            resultText.trim { it <= ' ' }
        println("Chỗ này được 2")
        return resultText
    }

    companion object {
        const val GPT_3_5_TURBO = Constants.MODEL
    }
}

fun client2(): OpenAIClient {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openai.com/v1/chat/completions")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    return retrofit.create(OpenAIClient::class.java)
}
/*clientGpt = OkHttpClient.Builder()
.connectTimeout(connectTimeout, TimeUnit.SECONDS)
.readTimeout(readTimeout, TimeUnit.SECONDS)
.writeTimeout(writeTimeout, TimeUnit.SECONDS)
.addInterceptor(Interceptor { chain: Interceptor.Chain ->
    val start = System.nanoTime()
    val request = chain.request()
    val response = chain.proceed(request)
    val end = System.nanoTime()
    val duration = end - start
    val millis = duration / 1e6
    requestTime = millis
    response
})
.build()
setTimeout(25000)*/

