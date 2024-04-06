package com.transvision.g2g.ui.screen.dashboard.RTI

import com.google.gson.annotations.SerializedName

data class RTIState(
    val rtiModel: RTIModel = RTIModel()
)

data class RTIUIState(
    var customDate : String = "",
    var monthYear : String = ""
)
data class RTIModel (

    @SerializedName("piosli"             ) var piosli             : ArrayList<Piosli>             = arrayListOf(),
    @SerializedName("Faa"                ) var Faa                : ArrayList<Faa>                = arrayListOf(),
    @SerializedName("applicationcount"   ) var applicationcount   : ArrayList<Applicationcount>   = arrayListOf(),
    @SerializedName("Applicationdetails" ) var Applicationdetails : ArrayList<Applicationdetails> = arrayListOf()

)


data class Piosli (

    @SerializedName("noofapplication" ) var noofapplication : String? = null,
    @SerializedName("Online"          ) var Online          : String? = null,
    @SerializedName("Offline"         ) var Offline         : String? = null,
    @SerializedName("Accepted"        ) var Accepted        : String? = null,
    @SerializedName("Rejected"        ) var Rejected        : String? = null,
    @SerializedName("Pending"         ) var Pending         : String? = null,
    @SerializedName("BPL"             ) var BPL             : String? = null,
    @SerializedName("WOMEN"           ) var WOMEN           : String? = null

)
data class Faa (

    @SerializedName("noofapplication" ) var noofapplication : String? = null,
    @SerializedName("Online"          ) var Online          : String? = null,
    @SerializedName("Offline"         ) var Offline         : String? = null,
    @SerializedName("Accepted"        ) var Accepted        : String? = null,
    @SerializedName("Rejected"        ) var Rejected        : String? = null,
    @SerializedName("Pending"         ) var Pending         : String? = null,
    @SerializedName("BPL"             ) var BPL             : String? = null,
    @SerializedName("WOMEN"           ) var WOMEN           : String? = null

)
data class Applicationcount (

    @SerializedName("type"  ) var type  : String? = null,
    @SerializedName("total" ) var total : String? = null

)

data class Applicationdetails (

    @SerializedName("TYPE"      ) var TYPE      : String? = null,
    @SerializedName("PIOSCOUNT" ) var PIOSCOUNT : String? = null,
    @SerializedName("FAACOUNT"  ) var FAACOUNT  : String? = null

)