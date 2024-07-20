package com.transvision.g2g.ui.screen.dashboard.rtdashboard
import com.google.gson.annotations.SerializedName

data class RTModelState(
    var isLoading : Boolean = false,
    var rtModel: RTModel = RTModel(),
    var rtView: RTView = RTView.NONE
)

data class RTModel (

    @SerializedName("FailureTransformers"        ) var FailureTransformers        : String?                               = null,
    @SerializedName("ConditionMonitoring"        ) var ConditionMonitoring        : String?                               = null,
    @SerializedName("FailureCapacitors"          ) var FailureCapacitors          : String?                               = null,
    @SerializedName("TotalFeeders"               ) var TotalFeeders               : String?                               = null,
    @SerializedName("ExistingFeeders"            ) var ExistingFeeders            : String?                               = null,
    @SerializedName("MeteredFeeders"             ) var MeteredFeeders             : String?                               = null,
    @SerializedName("FWGP"                       ) var FWGP                       : String?                               = null,
    @SerializedName("FAGPScraping"               ) var FAGPScraping               : String?                               = null,
    @SerializedName("FAGPRepair"                 ) var FAGPRepair                 : String?                               = null,
    @SerializedName("FAGPTender"                 ) var FAGPTender                 : String?                               = null,
    @SerializedName("FWGPTableData"              ) var FWGPTableData              : ArrayList<FWGPTableData>              = arrayListOf(),
    @SerializedName("FAGPScrapingTableData"      ) var FAGPScrapingTableData      : ArrayList<FAGPScrapingTableData>      = arrayListOf(),
    @SerializedName("FAGPRepairTableData"        ) var FAGPRepairTableData        : ArrayList<FAGPRepairTableData>        = arrayListOf(),
    @SerializedName("FAGPTenderTableData"        ) var FAGPTenderTableData        : ArrayList<FAGPTenderTableData>        = arrayListOf(),
    @SerializedName("ConditionMonitoringData"    ) var ConditionMonitoringData    : ArrayList<ConditionMonitoringData>    = arrayListOf(),
    @SerializedName("FailureCapacitorsTableData" ) var FailureCapacitorsTableData : ArrayList<FailureCapacitorsTableData> = arrayListOf()

)

data class FWGPTableData (

    @SerializedName("SlNo"           ) var SlNo          : String? = null,
    @SerializedName("Zone"           ) var Zone          : String? = null,
    @SerializedName("Circle"         ) var Circle        : String? = null,
    @SerializedName("Division"       ) var Division      : String? = null,
    @SerializedName("STATION"        ) var STATION       : String? = null,
    @SerializedName("DATE"           ) var DATE          : String? = null,
    @SerializedName("TF_NAME"        ) var TFNAME        : String? = null,
    @SerializedName("REASON_FAILURE" ) var REASONFAILURE : String? = null

)

data class FAGPScrapingTableData (

    @SerializedName("SlNo"           ) var SlNo          : String? = null,
    @SerializedName("Zone"           ) var Zone          : String? = null,
    @SerializedName("Circle"         ) var Circle        : String? = null,
    @SerializedName("Division"       ) var Division      : String? = null,
    @SerializedName("STATION"        ) var STATION       : String? = null,
    @SerializedName("DATE"           ) var DATE          : String? = null,
    @SerializedName("TF_NAME"        ) var TFNAME        : String? = null,
    @SerializedName("REASON_FAILURE" ) var REASONFAILURE : String? = null

)

data class FAGPTenderTableData (

    @SerializedName("SlNo"           ) var SlNo          : String? = null,
    @SerializedName("Zone"           ) var Zone          : String? = null,
    @SerializedName("Circle"         ) var Circle        : String? = null,
    @SerializedName("Division"       ) var Division      : String? = null,
    @SerializedName("STATION"        ) var STATION       : String? = null,
    @SerializedName("DATE"           ) var DATE          : String? = null,
    @SerializedName("TF_NAME"        ) var TFNAME        : String? = null,
    @SerializedName("REASON_FAILURE" ) var REASONFAILURE : String? = null

)

data class ConditionMonitoringData (

    @SerializedName("SlNo"     ) var SlNo     : String? = null,
    @SerializedName("Zone"     ) var Zone     : String? = null,
    @SerializedName("Circle"   ) var Circle   : String? = null,
    @SerializedName("Division" ) var Division : String? = null,
    @SerializedName("STATION"  ) var STATION  : String? = null,
    @SerializedName("DATE"     ) var DATE     : String? = null,
    @SerializedName("TF_NAME"  ) var TFNAME   : String? = null,
    @SerializedName("REMARK"   ) var REMARK   : String? = null

)

data class FailureCapacitorsTableData (

    @SerializedName("SlNo"             ) var SlNo           : String? = null,
    @SerializedName("Zone"             ) var Zone           : String? = null,
    @SerializedName("Circle"           ) var Circle         : String? = null,
    @SerializedName("Division"         ) var Division       : String? = null,
    @SerializedName("STATION"          ) var STATION        : String? = null,
    @SerializedName("MAKE"             ) var MAKE           : String? = null,
    @SerializedName("CAPACITORS_BANK"  ) var CAPACITORSBANK : String? = null,
    @SerializedName("NO_OF_CELLS"      ) var NOOFCELLS      : String? = null,
    @SerializedName("CAPACITY_OF_CELL" ) var CAPACITYOFCELL : String? = null,
    @SerializedName("VOLTAGE_OF_CELL"  ) var VOLTAGEOFCELL  : String? = null,
    @SerializedName("FULL_OR_PARTIAL"  ) var FULLORPARTIAL  : String? = null

)

data class FAGPRepairTableData (

    @SerializedName("SlNo"           ) var SlNo          : String? = null,
    @SerializedName("Zone"           ) var Zone          : String? = null,
    @SerializedName("Circle"         ) var Circle        : String? = null,
    @SerializedName("Division"       ) var Division      : String? = null,
    @SerializedName("STATION"        ) var STATION       : String? = null,
    @SerializedName("DATE"           ) var DATE          : String? = null,
    @SerializedName("TF_NAME"        ) var TFNAME        : String? = null,
    @SerializedName("REASON_FAILURE" ) var REASONFAILURE : String? = null

)

