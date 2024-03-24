package com.transvision.g2g.ui.screen.dashboard.misdashboard.accident

import com.transvision.g2g.features.data.data_source.ApiService
import com.transvision.g2g.utils.Constants

class AccidentRepository(private val apiService: ApiService) {

    suspend fun getAccidentInfo(monthYear : String)
            = apiService.getAccidentDashBoard(Constants.MISG2G,monthYear,Constants.APIPassword)
}