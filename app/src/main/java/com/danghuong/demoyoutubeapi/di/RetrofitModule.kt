package com.danghuong.demoyoutubeapi.di

import com.danghuong.demoyoutubeapi.common.Common
import com.danghuong.demoyoutubeapi.remote.YoutubeRetrofit
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    val BASE_URL = "https://youtube.googleapis.com/youtube/v3/"

    @Provides
    @Singleton
    fun getRetrofit(client:OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build() //Doesn't require the adapter
    }

    @Provides
    @Singleton
    fun youtubeRetrofit(retrofit: Retrofit): YoutubeRetrofit {
        return retrofit.create(YoutubeRetrofit::class.java)
    }

    @Provides
    @Singleton
    fun providesOkHttpClientAppVersion(): OkHttpClient {
        val client: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(Common.CONNECT_S, TimeUnit.SECONDS)
            .writeTimeout(Common.WRITE_S, TimeUnit.SECONDS)
            .readTimeout(Common.READ_S, TimeUnit.SECONDS)
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        client.addNetworkInterceptor(interceptor)
        return client.build()
    }






}




