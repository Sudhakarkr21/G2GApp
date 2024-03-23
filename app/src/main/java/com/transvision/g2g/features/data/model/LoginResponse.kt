package com.transvision.g2g.features.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (

    @SerializedName("ID"             ) var ID           : Int?    = null,
    @SerializedName("NAME"           ) var NAME         : String? = null,
    @SerializedName("FIRM_NAME"      ) var FIRMNAME     : String? = null,
    @SerializedName("PASSWORD"       ) var PASSWORD     : String? = null,
    @SerializedName("MOBILE_NO"      ) var MOBILENO     : String? = null,
    @SerializedName("EMAIL_ID"       ) var EMAILID      : String? = null,
    @SerializedName("ROLEID"         ) var ROLEID       : Int?    = null,
    @SerializedName("ADDRESS"        ) var ADDRESS      : String? = null,
    @SerializedName("REMARK"         ) var REMARK       : String? = null,
    @SerializedName("ADDBY"          ) var ADDBY        : String? = null,
    @SerializedName("ADDEDON"        ) var ADDEDON      : String? = null,
    @SerializedName("MISMACH_COUNT"  ) var MISMACHCOUNT : Int?    = null,
    @SerializedName("MISMACH_DATE"   ) var MISMACHDATE  : String? = null,
    @SerializedName("STATUS"         ) var STATUS       : String? = null,
    @SerializedName("LAST_LOGGED_ON" ) var LASTLOGGEDON : String? = null

)