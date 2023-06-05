package com.techworkscc.di

import com.techworkscc.BuildConfig
import com.techworkscc.data.news.NewsRepository
import com.techworkscc.data.news.NewsService
import com.techworkscc.domain.NewsUseCase
import com.techworkscc.presentation.NewsViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val API_URL = "https://newsapi.org/v2/"
val appModule = module {
    viewModelOf(::NewsViewModel)
    factory { NewsUseCase(get()) }
    factory { NewsRepository(get()) }
}

val networkModule = module {
    factory { provideOkHttpClient() }
    factory { provideNewsService(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().build()
}

fun provideNewsService(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)
