package com.sorokin.gifviewer.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sorokin.gifviewer.data.constant.NetworkConstant
import com.sorokin.gifviewer.data.mapper.GifMapper
import com.sorokin.gifviewer.data.network.GifApi
import com.sorokin.gifviewer.data.repository.GifRepositoryImpl
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import retrofit2.Retrofit
import retrofit2.create

@ExperimentalSerializationApi
fun dataModule() = DI.Module("DataModule") {

    val json = Json { ignoreUnknownKeys = true }
    val contentType = "application/json".toMediaType()


    bindSingleton {
        Retrofit.Builder()
            .baseUrl(NetworkConstant.BASE_URL)
            .client(
                OkHttpClient.Builder().build()
            )
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create<GifApi>()
    }

    bindSingleton {
        GifMapper()
    }

    bindSingleton {
        GifRepositoryImpl(instance(), instance())
    }
}
