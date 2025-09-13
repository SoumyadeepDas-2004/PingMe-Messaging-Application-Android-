package com.soumyadeep.pingme.presentation.feature_auth.signIn

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import com.soumyadeep.pingme.model.Country

//@OptIn(ExperimentalMaterial3Api::class)
////@Composable
////fun PhoneNumberInput(
////    phoneNumber: String,
////    onPhoneChange: (String) -> Unit,
////    selectedCountry: Country,
////    onCountryChange: (Country) -> Unit,
////    countries: List<Country>
////) {
////    var expanded by remember { mutableStateOf(false) }
////
////    Column {
////        // Country selector
////        ExposedDropdownMenuBox(
////            expanded = expanded,
////            onExpandedChange = { expanded = !expanded }
////        ) {
////            OutlinedTextField(
////                value = "${selectedCountry.flag} ${selectedCountry.name} (${selectedCountry.code})",
////                onValueChange = {},
////                readOnly = true,
////                label = { Text("Country") },
////                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
////                modifier = Modifier.menuAnchor(),
////                shape = RoundedCornerShape(12.dp),
////                colors = OutlinedTextFieldDefaults.colors(
////                    focusedBorderColor = MaterialTheme.colorScheme.primary,
////                    unfocusedBorderColor = Color.Gray
////                )
////            )
////
////            ExposedDropdownMenu(
////                expanded = expanded,
////                onDismissRequest = { expanded = false }
////            ) {
////                countries.forEach { country ->
////                    DropdownMenuItem(
////                        text = { Text("${country.flag} ${country.name} (${country.code})") },
////                        onClick = {
////                            onCountryChange(country)
////                            expanded = false
////                        }
////                    )
////                }
////            }
////        }
////
////        Spacer(modifier = Modifier.height(16.dp))
////
////        // Phone input
////        OutlinedTextField(
////            value = phoneNumber,
////            onValueChange = { input ->
////                if (input.length <= 15 && input.all { it.isDigit() }) {
////                    onPhoneChange(input)
////                }
////            },
////            placeholder = { Text("Phone number") },
////            singleLine = true,
////            modifier = Modifier.fillMaxWidth(),
////            shape = RoundedCornerShape(12.dp),
////            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
////                keyboardType = androidx.compose.ui.text.input.KeyboardType.Phone
////            ),
////            leadingIcon = {
////                Text(
////                    text = selectedCountry.code,
////                    modifier = Modifier.padding(start = 8.dp),
////                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
////                )
////            }
////        )
////    }
////}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNumberInput(
    phoneNumber: String,
    onPhoneChange: (String) -> Unit,
    selectedCountry: Country,
    onCountryChange: (Country) -> Unit,
    countries: List<Country>
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        // Country selector
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = "${selectedCountry.flag} ${selectedCountry.name} (${selectedCountry.code})",
                onValueChange = {},
                readOnly = true,
                label = { Text("Country") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color.Gray
                )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                countries.forEach { country ->
                    DropdownMenuItem(
                        text = { Text("${country.flag} ${country.name} (${country.code})") },
                        onClick = {
                            onCountryChange(country)
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Phone input
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { input ->
                // Only digits, max 15 chars
                val digitsOnly = input.filter { it.isDigit() }
                if (digitsOnly.length <= 15) {
                    onPhoneChange(digitsOnly)
                }
            },
            placeholder = { Text("Phone number") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                keyboardType = androidx.compose.ui.text.input.KeyboardType.Phone
            ),
            leadingIcon = {
                Text(
                    text = selectedCountry.code, // always includes '+'
                    modifier = Modifier.padding(start = 8.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
            }
        )
    }
}

/**
 * Returns the full number in E.164 format
 */
fun getFullE164Number(countryCode: String, phoneNumber: String): String {
    val digitsOnly = phoneNumber.filter { it.isDigit() }
    return countryCode + digitsOnly
}
