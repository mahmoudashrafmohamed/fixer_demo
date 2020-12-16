package com.mahmoud_ashraf.currencyconverter.presentation.di

import com.mahmoud_ashraf.currencyconverter.data.remote.FixerApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single { provideInterceptor() }
    single {
        provideOkHttpClient(
            get()
        )
    }
    single {
        provideApi(
            get()
        )
    }
    single {
        provideRetrofit(
            get()
        )
    }
}

fun provideInterceptor(): Interceptor {
    return HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("http://data.fixer.io/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .addInterceptor { chain ->

            val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("access_key","356654b3414775d2aeeaa5c92ab08e1f")
                .build()

            // add api key
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.url(url)

            val response = chain.proceed(requestBuilder.build())
            response
        }
        .build()
}

fun provideApi(retrofit: Retrofit): FixerApi = retrofit.create(FixerApi::class.java)