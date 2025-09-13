package com.soumyadeep.pingme.model

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val name: String,
    val code: String,
    val flag: String
)
