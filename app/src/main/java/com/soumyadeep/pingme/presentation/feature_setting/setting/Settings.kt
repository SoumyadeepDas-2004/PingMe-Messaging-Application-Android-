package com.soumyadeep.pingme.presentation.feature_setting.setting

//import androidx.compose.material3.Divider
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.soumyadeep.pingme.presentation.ui.components.settingscreen.AppTopBar
import com.soumyadeep.pingme.presentation.feature_setting.settingcontent.privacy
import com.soumyadeep.pingme.presentation.feature_setting.settingcontent.profilesection
import com.soumyadeep.pingme.presentation.feature_setting.settingcontent.personalization
import com.soumyadeep.pingme.presentation.feature_setting.settingcontent.notification
import com.soumyadeep.pingme.presentation.feature_setting.settingcontent.general
import com.soumyadeep.pingme.presentation.feature_setting.settingcontent.permission
import  androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.asPaddingValues
import androidx.navigation.NavHostController
import com.soumyadeep.pingme.common.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(navController: NavHostController) {

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        topBar = {
            AppTopBar(
                title = "Settings",
                showBack = true,
                showSearch = true,
                onBackClick = { navController.popBackStack() },
                onSearchClick = { /* search action */ }
            )
        }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            contentPadding = WindowInsets.navigationBars.asPaddingValues()
        ) {
            item { profilesection(navController = navController) }
            item { Divider() }
            item { privacy() }
            item { Divider() }
            item { personalization() }
            item { Divider() }
            item { notification() }
            item { Divider() }
            item { general() }
            item { Divider() }
            item { permission() }


        }
    }
}