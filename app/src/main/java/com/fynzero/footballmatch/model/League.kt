package com.fynzero.footballmatch.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    val idLeague: String,
    val logo: Int,
    val nameLeague: String,
    val webLeague: String,
    val facebookLeague: String
): Parcelable