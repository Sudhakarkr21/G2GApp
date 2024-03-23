package com.transvision.g2g.features.data.data_source

import com.transvision.g2g.features.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("Login/GetLoginDetails")
    suspend fun login(@Query("DB_Name") DB_Name : String,
                      @Query("UserName") UserName : String,
                      @Query("UserPassword") UserPassword : String,
                      @Query("APIPassword") APIPassword : String)
            : Response<List<LoginResponse>>
}