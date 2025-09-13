package com.soumyadeep.pingme.presentation.feature_profile.setprofile
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.soumyadeep.pingme.R
import com.soumyadeep.pingme.common.AppTopBar
import com.soumyadeep.pingme.presentation.viewmodel.SetProfileState
import com.soumyadeep.pingme.presentation.viewmodel.SetProfileViewModel
import androidx.compose.ui.layout.ContentScale
import android.widget.Toast
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun setprofile(
    navController: NavHostController,
    viewModel: SetProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    var fname by remember { mutableStateOf(viewModel.userName) }
    var about by remember { mutableStateOf(viewModel.about) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { selectedImageUri = it }
    }

    // 🔹 React to ViewModel state
    val currentState = uiState
    LaunchedEffect(currentState) {
        when (currentState) {
            is SetProfileState.Success -> {
                navController.navigate("main") {
                    popUpTo("set_profile") { inclusive = true }
                    launchSingleTop = true
                }
                viewModel.resetState() // now works
            }
            is SetProfileState.Error -> {
                val message = currentState.message
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }



    // Keep ViewModel in sync with text fields
    LaunchedEffect(fname) { viewModel.userName = fname }
    LaunchedEffect(about) { viewModel.about = about }

    BackHandler {
        navController.navigate("signin") {
            popUpTo("splash") { inclusive = false }
            launchSingleTop = true
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        topBar = {
            AppTopBar(
                title = "Set Profile",
                showBack = true,
                showTextButton = true,
                textButtonText = "Done",
                onTextButtonClick = {
                    if (selectedImageUri != null) {
                        viewModel.handleImageSelection(
                            context = context,
                            imageUri = selectedImageUri!!
                        )
                    }
                    val currentUser = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser

                    if (currentUser != null) {
                        viewModel.saveProfile(
                            userId = currentUser.uid,                     // actual Firebase UID
//                            phoneNumber = currentUser.phoneNumber ?: ""  // actual phone number
                        )
                    } else {
                        Toast.makeText(context, "User not logged in", Toast.LENGTH_SHORT).show()
                    }

                },
                onBackClick = {
                    navController.navigate("signin") {
                        launchSingleTop = true
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Profile picture
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clickable { imagePickerLauncher.launch("image/*") }
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onBackground),
                contentAlignment = Alignment.Center
            ) {
                if (selectedImageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(selectedImageUri),
                        contentDescription = "Profile Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop // 👈 fills the circle, crops edges if needed
                    )
                } else {
                    Icon(
                        painter = painterResource(R.drawable.camera),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = fname,
                onValueChange = { fname = it },
                placeholder = { Text("Full Name") },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.Transparent),
                singleLine = true
            )

            OutlinedTextField(
                value = about,
                onValueChange = { about = it },
                placeholder = { Text("About yourself") },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(Color.Transparent),
                singleLine = false,
                maxLines = 4
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (uiState is SetProfileState.Loading) {
                CircularProgressIndicator()
            }
        }
    }
}
