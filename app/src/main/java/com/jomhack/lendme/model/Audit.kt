package com.jomhack.lendme.model
import android.os.Parcel
import android.os.Parcelable
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
    val fromCustomerId: User? = null,
    @SerializedName("to_customer_id")
    val toCustomerId: User? = null,
    @SerializedName("bankin_type")
    val bankinType: String?,
    val amount: Int?,
    @SerializedName("point_of_interest")
    val pointOfInterest: Int?,
    val status: String?,
    @SerializedName("number_of_month")
    val numberOfMonth: Int?,
    val remark: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(User::class.java.classLoader),
            parcel.readParcelable(User::class.java.classLoader),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(auditId)
        parcel.writeString(insertBy)
        parcel.writeString(insertDate)
        parcel.writeString(updateBy)
        parcel.writeString(updateDate)
        parcel.writeParcelable(fromCustomerId, flags)
        parcel.writeParcelable(toCustomerId, flags)
        parcel.writeString(bankinType)
        parcel.writeValue(amount)
        parcel.writeValue(pointOfInterest)
        parcel.writeString(status)
        parcel.writeValue(numberOfMonth)
        parcel.writeString(remark)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Audit> {
        override fun createFromParcel(parcel: Parcel): Audit {
            return Audit(parcel)
        }

        override fun newArray(size: Int): Array<Audit?> {
            return arrayOfNulls(size)
        }
    }
}