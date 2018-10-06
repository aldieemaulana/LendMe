package com.jomhack.lendme.model
import com.google.gson.annotations.SerializedName

/**
 * Created by Al on 06/10/2018 for JomHack
 */

data class User(
    @SerializedName("customer_id")
    val customerId: String?,
    @SerializedName("insert_by")
    val insertBy: String?,
    @SerializedName("insert_date")
    val insertDate: String?,
    @SerializedName("update_by")
    val updateBy: String?,
    @SerializedName("update_date")
    val updateDate: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("ic_no")
    val icNo: String?,
    @SerializedName("date_of_birth")
    val dateOfBirth: String?,
    val gender: String?,
    @SerializedName("profile_rate")
    val profileRate: Int?,
    val address1: String?,
    val address2: String?,
    val address3: String?,
    @SerializedName("postal_code")
    val postalCode: String?,
    val city: String?,
    val state: String?,
    val country: String?,
    @SerializedName("mobile_no")
    val mobileNo: String?,
    val email: String?,
    @SerializedName("emergency_contact_no")
    val emergencyContactNo: String?,
    val remark: Any?
)