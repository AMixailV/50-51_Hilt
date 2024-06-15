package ru.mixail_akulov.a50_51_hilt.sources.base

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Все необходимое для выполнения HTTP-запросов с помощью клиента Retrofit и для парсинга JSON-сообщений.
 */
@Singleton
class RetrofitConfig @Inject constructor(
    val retrofit: Retrofit,
    val moshi: Moshi
)