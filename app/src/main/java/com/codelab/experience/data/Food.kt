package com.codelab.experience.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Food(var foodName: String = "",
                var calories: Int = 0,
                var imageUrl: String = "") : Parcelable