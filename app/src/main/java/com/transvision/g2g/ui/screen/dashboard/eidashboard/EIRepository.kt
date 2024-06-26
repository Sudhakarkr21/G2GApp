package com.transvision.g2g.ui.screen.dashboard.eidashboard

import android.util.Log
import com.transvision.g2g.features.data.data_source.ApiService
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.RNDUIState
import com.transvision.g2g.utils.Constants.months
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class EIRepository(val apiService: ApiService) {

    val TAG = "EIRepository"
    suspend fun getEIData1(rnduiState: RNDUIState) : Response<EIModel>{

        val monthConvsertion = if (rnduiState.customDate == "Month Wise"){
            val parts = rnduiState.monthYear.split("-")
            val monthWise = (months.indexOf(parts[0]) + 1).toString().padStart(2,'0') +
                    "-${parts[1]}"
            "month,$monthWise"
        }else {
            "year,${rnduiState.monthYear}"
        }

        Log.d(TAG, "getEIData: monthConvsertion $monthConvsertion")

        return apiService.getEIData(
            "DB_EIG2G",
            monthConvsertion,
            "Tvd1234!"
        )
    }
    suspend fun getEIData(rnduiState: RNDUIState) : Flow<EIModel?> = flow {

        val monthConvsertion = if (rnduiState.customDate == "Month Wise"){
            val parts = rnduiState.monthYear.split("-")
            val monthWise = (months.indexOf(parts[0]) + 1).toString().padStart(2,'0') +
                    "-${parts[1]}"
            "month,$monthWise"
        }else {
            "year,${rnduiState.monthYear}"
        }

        Log.d(TAG, "getEIData: monthConvsertion $monthConvsertion")

        val apiService = apiService.getEIData(
            "DB_EIG2G",
            monthConvsertion,
            "Tvd1234!"
        ).body()

        emit(apiService)
    }.flowOn(Dispatchers.IO).catch {
        emit(null)
    }

}