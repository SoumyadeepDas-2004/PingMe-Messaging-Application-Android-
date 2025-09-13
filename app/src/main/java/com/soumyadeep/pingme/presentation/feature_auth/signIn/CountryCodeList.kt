package com.soumyadeep.pingme.presentation.feature_auth.signIn

import android.content.Context
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import com.soumyadeep.pingme.R
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import com.soumyadeep.pingme.model.Country
fun loadCountries(context: Context): List<Country> {
    val inputStream = context.resources.openRawResource(R.raw.countries)
    val jsonString = inputStream.bufferedReader().use { it.readText() }
    return Json.decodeFromString(jsonString)
}
@Composable
fun rememberCountries(context: Context): List<Country> = remember { loadCountries(context) }