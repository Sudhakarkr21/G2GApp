package com.transvision.g2g.ui.screen.dashboard.misdashboard

import com.transvision.g2g.features.data.data_source.ApiService
import com.transvision.g2g.utils.Constants

class MisRepository(private val apiService: ApiService) {

    suspend fun getSessionData() = apiService.sessionMis(Constants.MISG2G,Constants.APIPassword)

    suspend fun getMISDashBoardData(financialYear : String, sessionID : String)
        = apiService.getMisDashBoardData(Constants.MISG2G,financialYear,sessionID,Constants.APIPassword)
}