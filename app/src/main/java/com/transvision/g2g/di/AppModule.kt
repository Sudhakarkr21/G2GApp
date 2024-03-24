package com.transvision.g2g.di

import com.transvision.g2g.di.loginrepository.LoginRepository
import com.transvision.g2g.features.data.data_source.ApiService
import com.transvision.g2g.ui.screen.dashboard.misdashboard.MisRepository
import com.transvision.g2g.ui.screen.dashboard.misdashboard.accident.AccidentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl(): String = "https://kptclg2g.in/api/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideLoginRepository(apiService: ApiService) = LoginRepository(apiService)

    @Provides
    @Singleton
    fun provideMISRepository(apiService: ApiService) = MisRepository(apiService)

    @Provides
    @Singleton
    fun provideMISAccidentRepo(apiService: ApiService) = AccidentRepository(apiService)
}