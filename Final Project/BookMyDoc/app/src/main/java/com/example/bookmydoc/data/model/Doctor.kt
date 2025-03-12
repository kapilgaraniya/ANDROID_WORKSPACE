package com.bookmydoc.data

import android.os.Parcel
import android.os.Parcelable

data class Doctor(
    val id: String = "",
    val name: String = "",
    val speciality: String = "",
    val experience: String = "",
    val email: String = "",
    val imageUrl: String = "",
    var averageRating: Float = 0.0f

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(speciality)
        parcel.writeString(experience)
        parcel.writeString(email)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Doctor> {
        override fun createFromParcel(parcel: Parcel): Doctor {
            return Doctor(parcel)
        }

        override fun newArray(size: Int): Array<Doctor?> {
            return arrayOfNulls(size)
        }
    }
}