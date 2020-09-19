package com.fynzero.footballmatch.model

data class NextMatch(
    val strHomeTeam: String,
    val strAwayTeam: String,
    val dateEventLocal: String,
    val strVenue: String,
    val strTimeLocal: String
)