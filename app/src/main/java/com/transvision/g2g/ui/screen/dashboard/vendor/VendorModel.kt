package com.transvision.g2g.ui.screen.dashboard.vendor

import com.google.gson.annotations.SerializedName



data class NumberofApplications (

    @SerializedName("TYPE"  ) var TYPE  : String? = null,
    @SerializedName("COUNT" ) var COUNT : String? = null

)

data class ApprovedApplications (

    @SerializedName("TYPE"  ) var TYPE  : String? = null,
    @SerializedName("COUNT" ) var COUNT : String? = null

)


data class PendingApplications (

    @SerializedName("TYPE"  ) var TYPE  : String? = null,
    @SerializedName("COUNT" ) var COUNT : String? = null

)

data class RejectedApplications (

    @SerializedName("TYPE"  ) var TYPE  : String? = null,
    @SerializedName("COUNT" ) var COUNT : String? = null

)

data class ApplicationDetails (

    @SerializedName("SLNO"             ) var SLNO            : String? = null,
    @SerializedName("APPLICATION_ID"   ) var APPLICATIONID   : String? = null,
    @SerializedName("VENDOR_ID"        ) var VENDORID        : String? = null,
    @SerializedName("MATERIAL_NAME"    ) var MATERIALNAME    : String? = null,
    @SerializedName("APPLICATION"      ) var APPLICATION     : String? = null,
    @SerializedName("COMPANY_NAME"     ) var COMPANYNAME     : String? = null,
    @SerializedName("APPLICATION_DATE" ) var APPLICATIONDATE : String? = null,
    @SerializedName("APPLICATION_TYPE" ) var APPLICATIONTYPE : String? = null

)

data class VendorModel (

    @SerializedName("NumberofApplications" ) var NumberofApplications : ArrayList<NumberofApplications> = arrayListOf(),
    @SerializedName("ApprovedApplications" ) var ApprovedApplications : ArrayList<ApprovedApplications> = arrayListOf(),
    @SerializedName("PendingApplications"  ) var PendingApplications  : ArrayList<PendingApplications>  = arrayListOf(),
    @SerializedName("RejectedApplications" ) var RejectedApplications : ArrayList<RejectedApplications> = arrayListOf(),
    @SerializedName("ApplicationDetails"   ) var ApplicationDetails   : ArrayList<ApplicationDetails>   = arrayListOf()

)

data class VendorState(
    val vendorModel: VendorModel = VendorModel()
)
