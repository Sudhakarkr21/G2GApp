package com.transvision.g2g.ui.screen.dashboard.wheelingbanking

import com.google.gson.annotations.SerializedName


data class WheelingBankingState(
    var isLoading: Boolean = false,
    var wheelingBankingModel: WheelingBankingModel = WheelingBankingModel()
)

data class WheelingBankingModel (

    @SerializedName("WB_Data_old"             ) var WBDataOld             : ArrayList<WBDataOld>             = arrayListOf(),
    @SerializedName("WB_Data_geoa"            ) var WBDataGeoa            : ArrayList<WBDataGeoa>            = arrayListOf(),
    @SerializedName("WB_Data_ExstingIPP"      ) var WBDataExstingIPP      : ArrayList<WBDataExstingIPP>      = arrayListOf(),
    @SerializedName("WB_Data_plant_dtl"       ) var WBDataPlantDtl        : ArrayList<WBDataPlantDtl>        = arrayListOf(),
    @SerializedName("WB_Data_GRIDVIEW"        ) var WBDataGRIDVIEW        : ArrayList<WBDataGRIDVIEW>        = arrayListOf(),
    @SerializedName("WB_Data_GRIDVIEWCFORM"   ) var WBDataGRIDVIEWCFORM   : ArrayList<String>                = arrayListOf(),
    @SerializedName("WB_Data_TotalgraphCFORM" ) var WBDataTotalgraphCFORM : ArrayList<WBDataTotalgraphCFORM> = arrayListOf(),
    @SerializedName("WB_Data_APPROVEDBFORM"   ) var WBDataAPPROVEDBFORM   : ArrayList<WBDataAPPROVEDBFORM>   = arrayListOf(),
    @SerializedName("WB_Data_APPpendCFORM"    ) var WBDataAPPpendCFORM    : ArrayList<WBDataAPPpendCFORM>    = arrayListOf(),
    @SerializedName("WB_Data_TOTALCFORM"      ) var WBDataTOTALCFORM      : ArrayList<WBDataTOTALCFORM>      = arrayListOf(),
    @SerializedName("WB_Data_typeCount"       ) var WBDataTypeCount       : ArrayList<WBDataTypeCount>       = arrayListOf()

)
data class WBDataOld (

    @SerializedName("TOTAL_OLD" ) var TOTALOLD : String? = null,
    @SerializedName("BESCOM"    ) var BESCOM   : String? = null,
    @SerializedName("GESCOM"    ) var GESCOM   : String? = null,
    @SerializedName("HESCOM"    ) var HESCOM   : String? = null,
    @SerializedName("MESCOM"    ) var MESCOM   : String? = null,
    @SerializedName("CESC"      ) var CESC     : String? = null

)

data class WBDataGeoa (

    @SerializedName("TOTAL_GEOA" ) var TOTALGEOA : String? = null,
    @SerializedName("BESCOM_G"   ) var BESCOMG   : String? = null,
    @SerializedName("GESCOM_G"   ) var GESCOMG   : String? = null,
    @SerializedName("HESCOM_G"   ) var HESCOMG   : String? = null,
    @SerializedName("MESCOM_G"   ) var MESCOMG   : String? = null,
    @SerializedName("CESC_G"     ) var CESCG     : String? = null

)

data class WBDataExstingIPP (

    @SerializedName("TOTAL" ) var TOTAL : String? = null

)

data class WBDataPlantDtl (

    @SerializedName("totalsolarbescom"   ) var totalsolarbescom   : String? = null,
    @SerializedName("totalsolargescom"   ) var totalsolargescom   : String? = null,
    @SerializedName("totalsolarhescom"   ) var totalsolarhescom   : String? = null,
    @SerializedName("totalsolarmescom"   ) var totalsolarmescom   : String? = null,
    @SerializedName("totalsolarcesc"     ) var totalsolarcesc     : String? = null,
    @SerializedName("totalwindbescom"    ) var totalwindbescom    : String? = null,
    @SerializedName("totalwindgescom"    ) var totalwindgescom    : String? = null,
    @SerializedName("totalwindhescom"    ) var totalwindhescom    : String? = null,
    @SerializedName("totalwindmescom"    ) var totalwindmescom    : String? = null,
    @SerializedName("totalwindcesc"      ) var totalwindcesc      : String? = null,
    @SerializedName("totalthermalbescom" ) var totalthermalbescom : String? = null,
    @SerializedName("totalthermalgescom" ) var totalthermalgescom : String? = null,
    @SerializedName("totalthermalhescom" ) var totalthermalhescom : String? = null,
    @SerializedName("totalthermalmescom" ) var totalthermalmescom : String? = null,
    @SerializedName("totalthermalcesc"   ) var totalthermalcesc   : String? = null,
    @SerializedName("totalhydalbescom"   ) var totalhydalbescom   : String? = null,
    @SerializedName("totalhydalgescom"   ) var totalhydalgescom   : String? = null,
    @SerializedName("totalhydalhescom"   ) var totalhydalhescom   : String? = null,
    @SerializedName("totalhydalmescom"   ) var totalhydalmescom   : String? = null,
    @SerializedName("totalhydalcesc"     ) var totalhydalcesc     : String? = null,
    @SerializedName("totalbiobescom"     ) var totalbiobescom     : String? = null,
    @SerializedName("totalbiogescom"     ) var totalbiogescom     : String? = null,
    @SerializedName("totalbiohescom"     ) var totalbiohescom     : String? = null,
    @SerializedName("totalbiomescom"     ) var totalbiomescom     : String? = null,
    @SerializedName("totalbiocesc"       ) var totalbiocesc       : String? = null

)


data class WBDataGRIDVIEW (

    @SerializedName("SLNO"        ) var SLNO       : String? = null,
    @SerializedName("PL_FIRMNAME" ) var PLFIRMNAME : String? = null,
    @SerializedName("WB_TBC_ID"   ) var WBTBCID    : String? = null,
    @SerializedName("PL_PLANT"    ) var PLPLANT    : String? = null,
    @SerializedName("PL_COMCAP"   ) var PLCOMCAP   : String? = null,
    @SerializedName("PL_JURESCOM" ) var PLJURESCOM : String? = null,
    @SerializedName("HDR_STATUS"  ) var HDRSTATUS  : String? = null

)


data class WBDataTotalgraphCFORM (

    @SerializedName("TotalCForms" ) var TotalCForms : String? = null,
    @SerializedName("TotalBForms" ) var TotalBForms : String? = null

)

data class WBDataAPPROVEDBFORM (

    @SerializedName("approved" ) var approved : String? = null

)

data class WBDataAPPpendCFORM (

    @SerializedName("Approved" ) var Approved : String? = null,
    @SerializedName("Pending"  ) var Pending  : String? = null

)

data class WBDataTOTALCFORM (

    @SerializedName("TOTAL_C_form" ) var TOTALCForm : String? = null

)
data class WBDataTypeCount (

    @SerializedName("PL_PLANT"           ) var PLPLANT           : String? = null,
    @SerializedName("Toatal_Application" ) var ToatalApplication : String? = null

)