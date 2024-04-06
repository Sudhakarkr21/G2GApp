package com.transvision.g2g.ui.screen.dashboard.vendor

import com.transvision.g2g.features.data.data_source.ApiService
import retrofit2.http.Query

class VendorRepository(val apiService: ApiService) {

    suspend fun getVendorDetails() = apiService.getVendorDetails("db_vendor","Tvd1234!")
}