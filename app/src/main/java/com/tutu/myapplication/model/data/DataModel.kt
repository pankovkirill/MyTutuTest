package com.tutu.myapplication.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel(
    val id: Int = 0,
    val login: String = "",
    val avatar_url: String = "",
    val type: String = ""
) : Parcelable
