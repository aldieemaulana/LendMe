package com.jomhack.lendme.model
import com.google.gson.annotations.SerializedName

/**
 * Created by Al on 06/10/2018 for JomHack
 */

data class Audit(
    @SerializedName("audit_id")
    val auditId: String?,
    @SerializedName("insert_by")
    val insertBy: String?,
    @SerializedName("insert_date")
    val insertDate: String?,
    @SerializedName("update_by")
    val updateBy: String?,
    @SerializedName("update_date")
    val updateDate: String?,
    @SerializedName("from_customer_id")
    val fromCustomerId: String?,
    @SerializedName("to_customer_id")
    val toCustomerId: String?,
    @SerializedName("bankin_type")
    val bankinType: String?,
    val amount: Int?,
    @SerializedName("point_of_interest")
    val pointOfInterest: Int?,
    val status: String?,
    @SerializedName("number_of_month")
    val numberOfMonth: Int?,
    val remark: Any?
)