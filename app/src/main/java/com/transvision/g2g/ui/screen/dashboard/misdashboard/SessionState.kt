package com.transvision.g2g.ui.screen.dashboard.misdashboard

import com.google.gson.annotations.SerializedName

data class SessionState (
    val isLoading : Boolean = false,
    val listSession: List<SessionModel> = emptyList()
)

data class MisDashBoardState(
    val isLoading : Boolean = false,
    val misDashBoardModel: MISDashBoardModel = MISDashBoardModel()
)

data class SessionModel (
    @SerializedName("SESSIONS_ID"   ) var SESSIONSID   : Int?    = null,
    @SerializedName("SESSIONS_NAME" ) var SESSIONSNAME : String? = null
)

data class MISUIEvent(
    var financialYear : String = "",
    var Session : SessionModel = SessionModel()
)

data class PieChartData(
    var browserName: String?,
    var value: Float?
)