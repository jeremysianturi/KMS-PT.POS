package com.pos.lms.kms_pt_pos_indonesia.di

import android.content.Context
import com.pos.lms.kms_pt_pos_indonesia.BuildConfig
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.network.ApiService
import com.pos.lms.kms_pt_pos_indonesia.utils.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Muhammad Zaim Milzam on 19/03/21.
 * linkedin : Muhammad Zaim Milzam
 */

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    fun provideInterceptor(@ApplicationContext context: Context): Interceptor {
        return NetworkConnectionInterceptor(context)
    }

    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return if (BuildConfig.DEBUG) {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(interceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
        } else {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE))
                .addInterceptor(interceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

        }
    }

    @Provides
    fun provideApiService(client: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }

//    @Provides
//    fun provideApiLogin(): ApiLogin {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BuildConfig.API_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        return retrofit.create(ApiLogin::class.java)
//    }

//    @Provides
//    fun provideApiServiceITMS(client: OkHttpClient): ApiITMS {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BuildConfig.API_URL_ITMS)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//        return retrofit.create(ApiITMS::class.java)
//    }

}