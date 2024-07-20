package com.transvision.g2g.ui.screen.dashboard.rtdashboard

import android.util.Log
import com.transvision.g2g.features.data.data_source.ApiService
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.RNDModel
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.RNDUIState
import com.transvision.g2g.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class RTRepo(val apiService: ApiService) {
    val TAG = "RTRepo"
    suspend fun getRDData(rnduiState: RNDUIState) : Flow<RTModel?> = flow {

        val monthConvsertion = if (rnduiState.customDate == "Month Wise"){
            val parts = rnduiState.monthYear.split("-")
            val monthWise = (Constants.months.indexOf(parts[0]) + 1).toString().padStart(2,'0') +
                    "-${parts[1]}"
            "$monthWise,${rnduiState.zone}"
        }else {
            rnduiState.monthYear
        }

        val zoneType = when(rnduiState.zone){
            "Bangalore" -> "1"
            "Tumkur" -> "2"
            "Mysore" -> "3"
            "Hassan" -> "4"
            "Bagalokote" -> "5"
            "Kalaburagi" -> "6"
            else -> {"ALL"}
        }
        Log.d(TAG, "getRDData: monthConvsertion $monthConvsertion")
        val apiResponse = when (rnduiState.customDate) {
            "Quarterly Wise" -> {
                val regex = Regex("""\((Q\d)\)""")
                val matchResult = regex.find(rnduiState.quarter)
                val quarter = matchResult?.let {
                    it.groupValues[1]
                }
                Log.d(TAG, "getRDData: quarter ${quarter}")
                apiService.getRTDetails("RT_RND_G2G","$quarter,$monthConvsertion,${zoneType}","Tvd1234!")
            }
            "Month Wise" -> apiService.getRTDetails("RT_RND_G2G","month,$monthConvsertion,${zoneType}","Tvd1234!")
            else -> apiService.getRTDetails("RT_RND_G2G","year,$monthConvsertion,${zoneType}","Tvd1234!")
        }
        Log.d(TAG, "getRDData: $apiResponse")
        if (apiResponse.isSuccessful)
            emit(apiResponse.body())
        else emit(null)

    }.flowOn(Dispatchers.IO).catch {
        emit(null)
    }
}