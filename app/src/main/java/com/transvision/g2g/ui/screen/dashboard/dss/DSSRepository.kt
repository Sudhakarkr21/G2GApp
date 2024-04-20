package com.transvision.g2g.ui.screen.dashboard.dss

import com.transvision.g2g.features.data.data_source.ApiService
import retrofit2.Response

class DSSRepository(val apiService: ApiService) {

    suspend fun getDSSData(monthYear : String) : Response<DSSModel>{
        return apiService.getDSSData("DSS_ACCOUNT",monthYear,"Tvd1234!")
    }
}