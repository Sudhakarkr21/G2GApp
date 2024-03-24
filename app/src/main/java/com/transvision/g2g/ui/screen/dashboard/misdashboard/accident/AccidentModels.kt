package com.transvision.g2g.ui.screen.dashboard.misdashboard.accident

import com.google.gson.annotations.SerializedName


data class AccidentState(
    var isLoading : Boolean  = false,
    var accidentModel: AccidentModel = AccidentModel()
)

data class AccidentModel (

    @SerializedName("Zones_Count"  ) var ZonesCount  : ArrayList<ZonesCount>  = arrayListOf(),
    @SerializedName("Escoms_Count" ) var EscomsCount : ArrayList<EscomsCount> = arrayListOf()

)

data class ZonesHumanDEPTCount (

    @SerializedName("HUMAN_DEPT_FATAL"     ) var HUMANDEPTFATAL    : String? = null,
    @SerializedName("HUMAN_DEPT_NON_FATAL" ) var HUMANDEPTNONFATAL : String? = null

)

data class ZonesHumanNonDEPTCount (

    @SerializedName("HUMAN_Non_DEPT_FATAL"     ) var HUMANNonDEPTFATAL    : String? = null,
    @SerializedName("HUMAN_Non_DEPT_NON_FATAL" ) var HUMANNonDEPTNONFATAL : String? = null

)

data class ZonesHumanCount (

    @SerializedName("HUMAN_DEPT"                 ) var HUMANDEPT              : String?                           = null,
    @SerializedName("HUMAN_NON_DEPT"             ) var HUMANNONDEPT           : String?                           = null,
    @SerializedName("Zones_Human_DEPT_Count"     ) var ZonesHumanDEPTCount    : ArrayList<ZonesHumanDEPTCount>    = arrayListOf(),
    @SerializedName("Zones_Human_Non_DEPT_Count" ) var ZonesHumanNonDEPTCount : ArrayList<ZonesHumanNonDEPTCount> = arrayListOf()

)

data class ZonesCount (

    @SerializedName("ANIMAL_COUNT"      ) var ANIMALCOUNT     : String?                    = null,
    @SerializedName("HUMAN_COUNT"       ) var HUMANCOUNT      : String?                    = null,
    @SerializedName("OTHER_COUNT"       ) var OTHERCOUNT      : String?                    = null,
    @SerializedName("Zones_Human_Count" ) var ZonesHumanCount : ArrayList<ZonesHumanCount> = arrayListOf()

)

data class EscomsHumanDEPTCount (

    @SerializedName("HUMAN_DEPT_FATAL"     ) var HUMANDEPTFATAL    : String? = null,
    @SerializedName("HUMAN_DEPT_NON_FATAL" ) var HUMANDEPTNONFATAL : String? = null

)

data class EscomsHumanNonDEPTCount (

    @SerializedName("HUMAN_Non_DEPT_FATAL"     ) var HUMANNonDEPTFATAL    : String? = null,
    @SerializedName("HUMAN_Non_DEPT_NON_FATAL" ) var HUMANNonDEPTNONFATAL : String? = null

)

data class EscomsHumanCount (

    @SerializedName("HUMAN_DEPT"                  ) var HUMANDEPT               : String?                            = null,
    @SerializedName("HUMAN_NON_DEPT"              ) var HUMANNONDEPT            : String?                            = null,
    @SerializedName("Escoms_Human_DEPT_Count"     ) var EscomsHumanDEPTCount    : ArrayList<EscomsHumanDEPTCount>    = arrayListOf(),
    @SerializedName("Escoms_Human_Non_DEPT_Count" ) var EscomsHumanNonDEPTCount : ArrayList<EscomsHumanNonDEPTCount> = arrayListOf()

)

data class EscomsCount (

    @SerializedName("ANIMAL_COUNT"       ) var ANIMALCOUNT      : String?                     = null,
    @SerializedName("HUMAN_COUNT"        ) var HUMANCOUNT       : String?                     = null,
    @SerializedName("CROP_COUNT"         ) var CROPCOUNT        : String?                     = null,
    @SerializedName("ASSET_LOSS_COUNT"   ) var ASSETLOSSCOUNT   : String?                     = null,
    @SerializedName("OTHER_COUNT"        ) var OTHERCOUNT       : String?                     = null,
    @SerializedName("Escoms_Human_Count" ) var EscomsHumanCount : ArrayList<EscomsHumanCount> = arrayListOf()

)