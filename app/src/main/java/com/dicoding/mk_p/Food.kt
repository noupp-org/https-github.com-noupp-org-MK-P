package com.dicoding.mk_p

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class Food(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable