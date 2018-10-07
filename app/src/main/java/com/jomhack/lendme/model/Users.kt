package com.jomhack.lendme.model
import android.os.Parcel
import android.os.Parcelable
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
    val remark: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(customerId)
        parcel.writeString(insertBy)
        parcel.writeString(insertDate)
        parcel.writeString(updateBy)
        parcel.writeString(updateDate)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(icNo)
        parcel.writeString(dateOfBirth)
        parcel.writeString(gender)
        parcel.writeValue(profileRate)
        parcel.writeString(address1)
        parcel.writeString(address2)
        parcel.writeString(address3)
        parcel.writeString(postalCode)
        parcel.writeString(city)
        parcel.writeString(state)
        parcel.writeString(country)
        parcel.writeString(mobileNo)
        parcel.writeString(email)
        parcel.writeString(emergencyContactNo)
        parcel.writeString(remark)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}