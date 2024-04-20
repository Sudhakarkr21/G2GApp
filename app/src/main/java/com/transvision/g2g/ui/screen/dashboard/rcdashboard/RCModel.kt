package com.transvision.g2g.ui.screen.dashboard.rcdashboard

import com.google.gson.annotations.SerializedName

data class RCModelState(
    var isLoading : Boolean = false,
    var rcModel: RCModel = RCModel()
)

data class RCModel (

    @SerializedName("Crossing"               ) var Crossing               : String?                           = null,
    @SerializedName("Addtional_Crossing"     ) var AddtionalCrossing      : String?                           = null,
    @SerializedName("GraphCrossing"          ) var GraphCrossing          : ArrayList<GraphCrossing>          = arrayListOf(),
    @SerializedName("GraphAddtionalCrossing" ) var GraphAddtionalCrossing : ArrayList<GraphAddtionalCrossing> = arrayListOf(),
    @SerializedName("CrossingDatadetails"    ) var CrossingDatadetails    : ArrayList<CrossingDatadetails>    = arrayListOf(),
    @SerializedName("AddCrossingDatadetails" ) var AddCrossingDatadetails : ArrayList<AddCrossingDatadetails> = arrayListOf()

)


data class GraphCrossing (

    @SerializedName("TYPE"  ) var TYPE  : String? = null,
    @SerializedName("COUNT" ) var COUNT : String? = null

)


data class GraphAddtionalCrossing (

    @SerializedName("TYPE"  ) var TYPE  : String? = null,
    @SerializedName("COUNT" ) var COUNT : String? = null

)
data class CrossingDatadetails (

    @SerializedName("CROSSING_LISTS" ) var CROSSINGLISTS : String? = null,
    @SerializedName("TOTAL_WORKCODE" ) var TOTALWORKCODE : String? = null,
    @SerializedName("COMPLETED"      ) var COMPLETED     : String? = null,
    @SerializedName("PENDING"        ) var PENDING       : String? = null

)

data class AddCrossingDatadetails (

    @SerializedName("ADDTIONALCROSSING_LISTS" ) var ADDTIONALCROSSINGLISTS : String? = null,
    @SerializedName("TOTAL_WORKCODE"          ) var TOTALWORKCODE          : String? = null,
    @SerializedName("COMPLETED"               ) var COMPLETED              : String? = null,
    @SerializedName("PENDING"                 ) var PENDING                : String? = null

)
