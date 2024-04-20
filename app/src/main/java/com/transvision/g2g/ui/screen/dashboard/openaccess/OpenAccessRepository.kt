package com.transvision.g2g.ui.screen.dashboard.openaccess

import android.util.Log
import com.transvision.g2g.features.data.data_source.ApiService
import retrofit2.Response
import kotlin.math.log

class OpenAccessRepository(val apiService: ApiService) {

    val TAG = "OpenAccessRepository"
    suspend fun getOpenAccessData(monthYear : String) : Response<OpenAccessModel>{
        val parts = monthYear.split("-")
        val monthWise = "${parts[1]}-${parts[0]}"
       val monthDate = "$monthWise-01,$monthWise-28"
        Log.d(TAG, "getOpenAccessData: monthDate $monthDate")
        return apiService.getOpenAccessData("dbo_openaccess2",monthDate,"Tvd1234!")
    }
}