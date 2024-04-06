package com.transvision.g2g.ui.screen.dashboard.RTI

import com.transvision.g2g.features.data.data_source.ApiService
import com.transvision.g2g.utils.Constants
import retrofit2.Response

class RTIRepository(val apiService: ApiService) {
    suspend fun getRTIData(rtiuiState: RTIUIState) : Response<RTIModel>{
        var monthYear = ""
        if (rtiuiState.customDate == "Month Wise"){
            val split = rtiuiState.monthYear.split("-")

            val twoDigitString2 = "%02d".format(Constants.months.indexOf(split[0]) + 1)

            monthYear = "${twoDigitString2}-${split[1]}"
            return apiService.getRTIData("RTI1","month,$monthYear","Tvd1234!")
        }
        monthYear = rtiuiState.monthYear

        return apiService.getRTIData("RTI1","year,$monthYear","Tvd1234!")
    }
}