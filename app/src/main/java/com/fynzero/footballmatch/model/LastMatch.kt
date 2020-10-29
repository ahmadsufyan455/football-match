package com.fynzero.footballmatch.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LastMatch(
    val idEvent: String,
    val idHomeTeam: String,
    val idAwayTeam: String,
    val strHomeTeam: String,
    val strAwayTeam: String,
    val dateEventLocal: String,
    val intHomeScore: String,
    val intAwayScore: String,
    val strVenue: String,
    val strLeague: String
) : Parcelable