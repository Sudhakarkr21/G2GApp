package com.transvision.g2g.di.loginrepository

import com.transvision.g2g.features.data.data_source.ApiService
import com.transvision.g2g.utils.Constants
import javax.inject.Inject

class LoginRepository (
    val apiService: ApiService
) {

    suspend fun loginResponse(userName : String , password : String) =
        apiService.login(Constants.DB_Name,userName,password,Constants.APIPassword)
}