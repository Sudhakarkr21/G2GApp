package com.transvision.g2g.ui.screen.dashboard.openaccess

import com.google.gson.annotations.SerializedName


data class OpenAccessState(
    var loader : Boolean = false,
    val openAccessModel: OpenAccessModel = OpenAccessModel()
)
data class OpenAccessModel (

    @SerializedName("SchedulePie"  ) var SchedulePie  : ArrayList<SchedulePie>  = arrayListOf(),
    @SerializedName("ScheduleData" ) var ScheduleData : ArrayList<ScheduleData> = arrayListOf(),
    @SerializedName("MeterPie"     ) var MeterPie     : ArrayList<MeterPie>     = arrayListOf(),
    @SerializedName("MeterData"    ) var MeterData    : ArrayList<MeterData>    = arrayListOf(),
    @SerializedName("IexPie"       ) var IexPie       : ArrayList<IexPie>       = arrayListOf(),
    @SerializedName("IexData"      ) var IexData      : ArrayList<String>       = arrayListOf(),
    @SerializedName("IexPiec"      ) var IexPiec      : ArrayList<IexPiec>      = arrayListOf()

)
data class SchedulePie (
    @SerializedName("type"   ) var type   : String? = null,
    @SerializedName("counts" ) var counts : Int?    = null
)

data class ScheduleData (
    @SerializedName("cmp_id"   ) var cmpId   : String? = null,
    @SerializedName("cmp_name" ) var cmpName : String? = null,
    @SerializedName("TYPE"     ) var TYPE    : String? = null
)

data class MeterPie (
    @SerializedName("type"   ) var type   : String? = null,
    @SerializedName("counts" ) var counts : Int?    = null
)

data class MeterData (

    @SerializedName("cmp_id"   ) var cmpId   : String? = null,
    @SerializedName("cmp_name" ) var cmpName : String? = null,
    @SerializedName("TYPE"     ) var TYPE    : String? = null

)
data class IexPie (
    @SerializedName("type"   ) var type   : String? = null,
    @SerializedName("counts" ) var counts : Int?    = null
)
data class IexPiec (
    @SerializedName("type"   ) var type   : String? = null,
    @SerializedName("counts" ) var counts : Int?    = null
)