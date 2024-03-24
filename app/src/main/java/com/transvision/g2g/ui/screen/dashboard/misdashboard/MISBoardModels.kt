package com.transvision.g2g.ui.screen.dashboard.misdashboard

import com.google.gson.annotations.SerializedName

data class TableDataParliment (

    @SerializedName("Question_Type" ) var QuestionType : String? = null,
    @SerializedName("Total_count"   ) var TotalCount   : String? = null,
    @SerializedName("Ans_count"     ) var AnsCount     : String? = null,
    @SerializedName("Pending_count" ) var PendingCount : String? = null

)

data class TableDataAssmble (

    @SerializedName("Question_Type" ) var QuestionType : String? = null,
    @SerializedName("Total_count"   ) var TotalCount   : String? = null,
    @SerializedName("Ans_count"     ) var AnsCount     : String? = null,
    @SerializedName("Pending_count" ) var PendingCount : String? = null

)

data class MISDashBoardModel (

    @SerializedName("Table_data_Assmble"   ) var TableDataAssmble   : ArrayList<TableDataAssmble>   = arrayListOf(),
    @SerializedName("Table_data_Parliment" ) var TableDataParliment : ArrayList<TableDataParliment> = arrayListOf()

)