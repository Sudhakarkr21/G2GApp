package com.transvision.g2g.ui.screen.dashboard.wheelingbanking

import com.transvision.g2g.features.data.data_source.ApiService
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.RNDUIState
import com.transvision.g2g.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WheelingAndBillingRepository(val apiService: ApiService) {

    suspend fun getWheelingData(rnduiState: RNDUIState): Flow<WheelingBankingModel?> = flow {
        val parts = rnduiState.monthYear.split("-")
        val monthWise = (Constants.months.indexOf(parts[0]) + 1).toString().padStart(2,'0') +
                "-${parts[1]}"

        val response = apiService.getWheelingAndBillingData("WheelingBanking",monthWise,"Tvd1234!")
        if (response.isSuccessful)
            emit(response.body())
        else emit(null)
    }.flowOn(Dispatchers.IO).catch {
        emit(null)
    }

}