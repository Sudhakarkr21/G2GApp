package com.transvision.g2g.ui.screen.dashboard.rcdashboard

import android.util.Log
import com.transvision.g2g.features.data.data_source.ApiService
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.RNDModel
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.RNDUIState
import retrofit2.Response

class RCRepository(private val apiService: ApiService) {

    val TAG = "RNDRepository"

    suspend fun getRCData(rnduiState: RNDUIState) : Response<RCModel> {

        val monthConvsertion = if (rnduiState.customDate == "Month Wise"){
            val parts = rnduiState.monthYear.split("-")
            val monthWise = "${parts[1]}-${parts[0]}"
            "Month,$monthWise"
        }else {
            "Year,${rnduiState.monthYear}"
        }

        Log.d(TAG, "getRDData: monthConvsertion $monthConvsertion")
        return apiService.getRCData("RAILWAYG2G",monthConvsertion,"Tvd1234!")

    }
}