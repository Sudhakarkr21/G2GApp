package com.transvision.g2g.features.data.data_source

import com.transvision.g2g.features.data.model.LoginResponse
import com.transvision.g2g.ui.screen.dashboard.misdashboard.MISDashBoardModel
import com.transvision.g2g.ui.screen.dashboard.misdashboard.SessionModel
import com.transvision.g2g.ui.screen.dashboard.misdashboard.accident.AccidentModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("Login/GetLoginDetails")
    suspend fun login(
        @Query("DB_Name") DB_Name: String,
        @Query("UserName") UserName: String,
        @Query("UserPassword") UserPassword: String,
        @Query("APIPassword") APIPassword: String
    ): Response<List<LoginResponse>>

    @GET("Mis/Getdata_Session")
    suspend fun sessionMis(
        @Query("DBName") DB_Name: String,
        @Query("ApiPassword") APIPassword: String
    ): Response<List<SessionModel>>

    @GET("Mis/Getdata_Questions")
    suspend fun getMisDashBoardData(
        @Query("DBName") DBName : String,
        @Query("Financial_Year") Financial_Year : String,
        @Query("Session_ID") Session_ID : String,
        @Query("ApiPassword") ApiPassword : String
    ) : Response<MISDashBoardModel>


    @GET("Mis/Getdata_Accidents")
    suspend fun getAccidentDashBoard(
        @Query("DBName") DBName : String,
        @Query("Month_year") Month_year : String,
        @Query("ApiPassword") ApiPassword : String
    ) : Response<AccidentModel>
}