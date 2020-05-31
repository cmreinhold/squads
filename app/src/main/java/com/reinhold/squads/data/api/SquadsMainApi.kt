package com.reinhold.squads.data.api

import com.google.gson.GsonBuilder
import com.reinhold.squads.BuildConfig
import com.reinhold.squads.core.logger.obtainLogger
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


object SquadsMainApi {

    private val logger = obtainLogger("SquadsMainApi")

    private const val ROOT_URL = "gateway.marvel.com"
    private const val BASE_URL = "http://$ROOT_URL/v1/public/"

    private const val TIMESTAMP = "ts"
    private const val API_KEY = "apikey"
    private const val HASH = "hash"
    private const val ORDER = "orderBy"
    private const val ORDER_BY_NAME = "name"

    private val hashKey by lazy {
        (timeStamp + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY).toMD5()
    }

    private val timeStamp by lazy {
        System.currentTimeMillis().toString()
    }

    private var gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create()

    private val loggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) Level.BASIC else Level.NONE
        }
    }

    private val securityInterceptor by lazy {
        Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Accept", "*/*")
                .addHeader("Accept-Language", "en")
                .addHeader("Content-type", "application/json")
                .build()
            chain.proceed(request)
        }
    }

    private val urlInterceptor by lazy {
        Interceptor { chain ->
            var request: Request = chain.request()

            val url: HttpUrl = request.url.newBuilder()
                .apply {
                    addQueryParameter(TIMESTAMP, timeStamp)
                    addQueryParameter(API_KEY, BuildConfig.PUBLIC_KEY)
                    addQueryParameter(HASH, hashKey)
                    addQueryParameter(ORDER, ORDER_BY_NAME)
                }
                .build()
            request = request.newBuilder()
                .url(url)
                .build()

            logger.debug("New url is ${request.url}")
            chain.proceed(request)
        }
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder().apply {
            readTimeout(60, TimeUnit.SECONDS)
            connectTimeout(60, TimeUnit.SECONDS)

            addInterceptor(loggingInterceptor)
            addInterceptor(urlInterceptor)
            addInterceptor(securityInterceptor)
        }.build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun createSquadsApiService(): SquadsApi = retrofit.create(SquadsApi::class.java)
}

interface SquadsApi {

    @GET("characters")
    suspend fun getSquads(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): DataWrapper<CharactersDto>


    @GET("comics/{comicId}")
    suspend fun getComic(
        @Path("comicId") comicId: String
    ): DataWrapper<ResourceDto>
}
