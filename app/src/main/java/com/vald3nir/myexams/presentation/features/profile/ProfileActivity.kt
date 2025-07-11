package com.vald3nir.myexams.presentation.features.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.vald3nir.myexams.presentation.CustomActivity
import com.vald3nir.myexams.presentation.features.profile.screen.CreateProfileScreen
import com.vald3nir.myexams.presentation.features.profile.screen.EditProfileScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
internal class ProfileActivity : CustomActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scope = ProfileScope(viewModel = hiltViewModel<ProfileViewModel>(this))
            if (intent.getBooleanExtra(PROFILE_EDIT, false)) {
                scope.EditProfileScreen()
            } else {
                scope.CreateProfileScreen()
            }
        }
    }
}

fun Context.redirectCreateProfileActivity() {
    val intent = Intent(this, ProfileActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    startActivity(intent)
}

fun Context.redirectEditProfileActivity() {
    val intent = Intent(this, ProfileActivity::class.java).apply {
        putExtra(PROFILE_EDIT, true)
    }
    startActivity(intent)
}

private const val PROFILE_EDIT = "PROFILE_EDIT"