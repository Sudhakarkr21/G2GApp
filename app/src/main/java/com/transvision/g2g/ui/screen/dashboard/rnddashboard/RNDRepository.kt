package com.transvision.g2g.ui.screen.dashboard.rnddashboard

import android.util.Log
import com.transvision.g2g.features.data.data_source.ApiService
import retrofit2.Response

class RNDRepository(private val apiService: ApiService) {

    val TAG = "RNDRepository"
    suspend fun getRDData(rnduiState: RNDUIState) : Response<RNDModel>{

        val monthConvsertion = if (rnduiState.customDate == "Month Wise"){
            val parts = rnduiState.monthYear.split("-")
            val monthWise = "${parts[1]}-${parts[0]}"
            "$monthWise-01,$monthWise-28,0"
        }else {
            "${rnduiState.monthYear},0"
        }

        Log.d(TAG, "getRDData: monthConvsertion $monthConvsertion")
        return when (rnduiState.customDate) {
            "Quarterly Wise" -> {
                val regex = Regex("""\((Q\d)\)""")
                val matchResult = regex.find(rnduiState.quarter)
                val quarter = matchResult?.let {
                    it.groupValues[1]
                }
                Log.d(TAG, "getRDData: quarter ${quarter}")
                apiService.getRNDDataQuarter("RT_RND_G2G",monthConvsertion,quarter!!,"Tvd1234!")

            }
            "Month Wise" -> apiService.getRNDData("RT_RND_G2G",monthConvsertion,"Tvd1234!")
            else -> apiService.getRNDDataYear("RT_RND_G2G",monthConvsertion,"Tvd1234!")
        }

    }
}