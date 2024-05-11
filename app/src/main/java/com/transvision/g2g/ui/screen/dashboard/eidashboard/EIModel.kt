package com.transvision.g2g.ui.screen.dashboard.eidashboard

import com.google.gson.annotations.SerializedName

data class EIModelState(
    var isloading : Boolean = false,
    var eiModel: EIModel = EIModel()
)

data class EIModel (
    @SerializedName("DRAWINGAPPROVAL"            ) var DRAWINGAPPROVAL            : String?                               = null,
    @SerializedName("DEMANDNOTE"                 ) var DEMANDNOTE                 : String?                               = null,
    @SerializedName("INSPECTION"                 ) var INSPECTION                 : String?                               = null,
    @SerializedName("SAFETYCERTIFICATE"          ) var SAFETYCERTIFICATE          : String?                               = null,
    @SerializedName("PIEChart_Data"              ) var PIEChartData               : PIEChartData?                         = PIEChartData(),
    @SerializedName("DrawingApprovalTableData"   ) var DrawingApprovalTableData   : ArrayList<DrawingApprovalTableData>   = arrayListOf(),
    @SerializedName("DemandNoteTableData"        ) var DemandNoteTableData        : ArrayList<DemandNoteTableData>        = arrayListOf(),
    @SerializedName("SpotInsepctionTableData"    ) var SpotInsepctionTableData    : ArrayList<SpotInsepctionTableData>    = arrayListOf(),
    @SerializedName("SafetyCertificateTableData" ) var SafetyCertificateTableData : ArrayList<SafetyCertificateTableData> = arrayListOf(),
    @SerializedName("EIW_overall"                ) var EIWOverall                 : ArrayList<EIWOverall>                 = arrayListOf()
)

data class PIEChartData (

    @SerializedName("DRAWINGAPPROVAL"   ) var DRAWINGAPPROVAL   : String? = null,
    @SerializedName("DEMANDNOTE"        ) var DEMANDNOTE        : String? = null,
    @SerializedName("INSPECTION"        ) var INSPECTION        : String? = null,
    @SerializedName("SAFETYCERTIFICATE" ) var SAFETYCERTIFICATE : String? = null

)

data class DrawingApprovalTableData (

    @SerializedName("slno"            ) var slno            : String? = null,
    @SerializedName("project"         ) var project         : String? = null,
    @SerializedName("subissiondate"   ) var subissiondate   : String? = null,
    @SerializedName("Fromstation"     ) var Fromstation     : String? = null,
    @SerializedName("tostation"       ) var tostation       : String? = null,
    @SerializedName("DEMANDISSUEDATE" ) var DEMANDISSUEDATE : String? = null,
    @SerializedName("days_count" ) var days_count : String? = null

)

data class DemandNoteTableData (

    @SerializedName("slno"            ) var slno            : String? = null,
    @SerializedName("project"         ) var project         : String? = null,
    @SerializedName("Fromstation"     ) var Fromstation     : String? = null,
    @SerializedName("tostation"       ) var tostation       : String? = null,
    @SerializedName("DEMANDISSUEDATE" ) var DEMANDISSUEDATE : String? = null

)

data class SpotInsepctionTableData (

    @SerializedName("slno"               ) var slno             : String? = null,
    @SerializedName("project"            ) var project          : String? = null,
    @SerializedName("INS_DATE"           ) var INSDATE          : String? = null,
    @SerializedName("RE_INSPECTION_DATE" ) var REINSPECTIONDATE : String? = null,
    @SerializedName("days_count" ) var days_count : String? = null

)

data class SafetyCertificateTableData (

    @SerializedName("slno"          ) var slno        : String? = null,
    @SerializedName("project"       ) var project     : String? = null,
    @SerializedName("SC_ISSUE_DATE" ) var SCISSUEDATE : String? = null,
    @SerializedName("SC_NO"         ) var SCNO        : String? = null

)

data class EIWOverall (

    @SerializedName("slno"      ) var slno     : String? = null,
    @SerializedName("Work_Type" ) var WorkType : String? = null,
    @SerializedName("count"     ) var count    : String? = null

)