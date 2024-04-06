package com.transvision.g2g.ui.screen.dashboard.rnddashboard

import com.google.gson.annotations.SerializedName

data class RNDState(
    var rndModel: RNDModel = RNDModel()
)


data class RNDUIState(
    var customDate : String = "",
    var monthYear : String = "",
    var quarter : String = "",
    var zone : String = ""
)
data class RNDModel (

    @SerializedName("Oil"                      ) var Oil                      : String?                             = null,
    @SerializedName("Tandelta"                 ) var Tandelta                 : String?                             = null,
    @SerializedName("OilTestGraph"             ) var OilTestGraph             : ArrayList<OilTestGraph>             = arrayListOf(),
    @SerializedName("OilTesthandsontable"      ) var OilTesthandsontable      : ArrayList<OilTesthandsontable>      = arrayListOf(),
    @SerializedName("TandeltaTestGraph"        ) var TandeltaTestGraph        : ArrayList<TandeltaTestGraph>        = arrayListOf(),
    @SerializedName("TandeltaTesthandsonTable" ) var TandeltaTesthandsonTable : ArrayList<TandeltaTesthandsonTable> = arrayListOf()

)

data class OilTestGraph (

    @SerializedName("TYPE"  ) var TYPE  : String? = null,
    @SerializedName("COUNT" ) var COUNT : String? = null

)

data class OilTesthandsontable (

    @SerializedName("Sl_No"               ) var SlNo               : String? = null,
    @SerializedName("Zone_Name"           ) var ZoneName           : String? = null,
    @SerializedName("Circle_Name"         ) var CircleName         : String? = null,
    @SerializedName("Division_Name"       ) var DivisionName       : String? = null,
    @SerializedName("Station_Name"        ) var StationName        : String? = null,
    @SerializedName("TFSLNO"              ) var TFSLNO             : String? = null,
    @SerializedName("Date"                ) var Date               : String? = null,
    @SerializedName("Equipment_Type"      ) var EquipmentType      : String? = null,
    @SerializedName("TFName"              ) var TFName             : String? = null,
    @SerializedName("Oil_Type"            ) var OilType            : String? = null,
    @SerializedName("Sub_oil_Type"        ) var SubOilType         : String? = null,
    @SerializedName("OLTCFlag"            ) var OLTCFlag           : String? = null,
    @SerializedName("Flag"                ) var Flag               : String? = null,
    @SerializedName("Appearance"          ) var Appearance         : String? = null,
    @SerializedName("Acidity"             ) var Acidity            : String? = null,
    @SerializedName("Dielectric"          ) var Dielectric         : String? = null,
    @SerializedName("Kinematic_Viscocity" ) var KinematicViscocity : String? = null,
    @SerializedName("Flash_Point"         ) var FlashPoint         : String? = null,
    @SerializedName("Interfacial_Tension" ) var InterfacialTension : String? = null,
    @SerializedName("Breakdown_Voltage"   ) var BreakdownVoltage   : String? = null,
    @SerializedName("Breakdown_Voltage_B" ) var BreakdownVoltageB  : String? = null,
    @SerializedName("Water_Content"       ) var WaterContent       : String? = null,
    @SerializedName("Resistivity"         ) var Resistivity        : String? = null,
    @SerializedName("Density"             ) var Density            : String? = null

)

data class TandeltaTestGraph (

    @SerializedName("TYPE"  ) var TYPE  : String? = null,
    @SerializedName("COUNT" ) var COUNT : String? = null

)
data class TandeltaTesthandsonTable (

    @SerializedName("Sl_No"           ) var SlNo           : String? = null,
    @SerializedName("Equipment_Type"  ) var EquipmentType  : String? = null,
    @SerializedName("Equipment_Count" ) var EquipmentCount : String? = null

)