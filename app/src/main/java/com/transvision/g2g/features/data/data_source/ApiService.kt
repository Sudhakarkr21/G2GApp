package com.transvision.g2g.features.data.data_source

import com.transvision.g2g.features.data.model.LoginResponse
import com.transvision.g2g.ui.screen.dashboard.RTI.RTIModel
import com.transvision.g2g.ui.screen.dashboard.dss.DSSModel
import com.transvision.g2g.ui.screen.dashboard.misdashboard.MISDashBoardModel
import com.transvision.g2g.ui.screen.dashboard.misdashboard.SessionModel
import com.transvision.g2g.ui.screen.dashboard.misdashboard.accident.AccidentModel
import com.transvision.g2g.ui.screen.dashboard.openaccess.OpenAccessModel
import com.transvision.g2g.ui.screen.dashboard.rcdashboard.RCModel
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.RNDModel
import com.transvision.g2g.ui.screen.dashboard.vendor.VendorModel
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

    @GET("RD/GetMonthWiseDetails")
    suspend fun getRNDData(
        @Query("DB_Name") DB_Name : String,
        @Query("param") param : String,
        @Query("APIPassword") APIPassword : String
    ) : Response<RNDModel>
    @GET("RD/GetYearWiseDetails")
    suspend fun getRNDDataYear(
        @Query("DB_Name") DB_Name : String,
        @Query("param") param : String,
        @Query("APIPassword") APIPassword : String
    ) : Response<RNDModel>
    @GET("RD/GetQuaterWiseDetails")
    suspend fun getRNDDataQuarter(
        @Query("DB_Name") DB_Name : String,
        @Query("param") param : String,
        @Query("Quater") Quater : String,
        @Query("APIPassword") APIPassword : String
    ) : Response<RNDModel>

    @GET("Vendor/GetDetails")
    suspend fun getVendorDetails(
        @Query("DB_Name") DB_Name : String,
        @Query("APIPassword") APIPassword : String
    ) : Response<VendorModel>

    @GET("RTI/RT_DashboardData")
    suspend fun getRTIData(
        @Query("DB_Name") DB_Name : String,
        @Query("paramss") paramss : String,
        @Query("APIPassword") APIPassword : String
    ) : Response<RTIModel>

    @GET("OA_DashBoard/OA_DashboardData")
    suspend fun getOpenAccessData(
        @Query("DB_Name") DB_Name : String,
        @Query("paramss") paramss : String,
        @Query("APIPassword") APIPassword : String
    ) : Response<OpenAccessModel>

    @GET("DSS_Account/DSS_AccountData")
    suspend fun getDSSData(
        @Query("DB_Name") DB_Name : String,
        @Query("paramss") paramss : String,
        @Query("APIPassword") APIPassword : String
    ) : Response<DSSModel>

    @GET("RC/GetDetails")
    suspend fun getRCData(
        @Query("DB_Name") DB_Name : String,
        @Query("Params") paramss : String,
        @Query("APIPassword") APIPassword : String
    ) : Response<RCModel>
}