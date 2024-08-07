package com.transvision.g2g.di

import com.transvision.g2g.di.loginrepository.LoginRepository
import com.transvision.g2g.features.data.data_source.ApiService
import com.transvision.g2g.ui.screen.dashboard.RTI.RTIRepository
import com.transvision.g2g.ui.screen.dashboard.dss.DSSRepository
import com.transvision.g2g.ui.screen.dashboard.eidashboard.EIRepository
import com.transvision.g2g.ui.screen.dashboard.misdashboard.MisRepository
import com.transvision.g2g.ui.screen.dashboard.misdashboard.accident.AccidentRepository
import com.transvision.g2g.ui.screen.dashboard.openaccess.OpenAccessRepository
import com.transvision.g2g.ui.screen.dashboard.rcdashboard.RCRepository
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.RNDRepository
import com.transvision.g2g.ui.screen.dashboard.rtdashboard.RTRepo
import com.transvision.g2g.ui.screen.dashboard.vendor.VendorRepository
import com.transvision.g2g.ui.screen.dashboard.wheelingbanking.WheelingAndBillingRepository
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

    @Provides
    @Singleton
    fun provideRNDRepo(apiService: ApiService) = RNDRepository(apiService)

    @Provides
    @Singleton
    fun provideVendorRepo(apiService: ApiService) = VendorRepository(apiService)
    @Provides
    @Singleton
    fun provideRTIRepo(apiService: ApiService) = RTIRepository(apiService)
    @Provides
    @Singleton
    fun provideOpenAccessRepo(apiService: ApiService) = OpenAccessRepository(apiService)
    @Provides
    @Singleton
    fun provideDSSRepo(apiService: ApiService) = DSSRepository(apiService)
    @Provides
    @Singleton
    fun provideRCRepo(apiService: ApiService) = RCRepository(apiService)
    @Provides
    @Singleton
    fun provideEIRepo(apiService: ApiService) = EIRepository(apiService)
    @Provides
    @Singleton
    fun provideRTRepo(apiService: ApiService) = RTRepo(apiService)
    @Provides
    @Singleton
    fun provideWheelingAndBankingRepo(apiService: ApiService) = WheelingAndBillingRepository(apiService)
}