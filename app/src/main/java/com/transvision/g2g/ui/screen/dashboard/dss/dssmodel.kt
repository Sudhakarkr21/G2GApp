package com.transvision.g2g.ui.screen.dashboard.dss
import com.google.gson.annotations.SerializedName


data class DSSState(
    var dssModel: DSSModel = DSSModel(),
    var isLoader : Boolean = false
)

data class DSSModel (

    @SerializedName("GraphData"            ) var GraphData            : ArrayList<GraphData>            = arrayListOf(),
    @SerializedName("CashBalanceTableData" ) var CashBalanceTableData : ArrayList<CashBalanceTableData> = arrayListOf(),
    @SerializedName("BankBalanceTable"     ) var BankBalanceTable     : ArrayList<BankBalanceTable>     = arrayListOf()

)

data class GraphData (

    @SerializedName("Assets"      ) var Assets      : String? = null,
    @SerializedName("expanditure" ) var expanditure : String? = null,
    @SerializedName("income"      ) var income      : String? = null,
    @SerializedName("liability"   ) var liability   : String? = null

)

data class CashBalanceTableData (

    @SerializedName("AccountCode" ) var AccountCode : String? = null,
    @SerializedName("Description" ) var Description : String? = null,
    @SerializedName("Balance"     ) var Balance     : String? = null

)

data class BankBalanceTable (

    @SerializedName("AccountCode" ) var AccountCode : String? = null,
    @SerializedName("Description" ) var Description : String? = null,
    @SerializedName("Balance"     ) var Balance     : String? = null

)