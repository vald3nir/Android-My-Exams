package com.vald3nir.myexams.presentation.features.profile

import android.content.Context
import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.presentation.features.ScreenEvents
import com.vald3nir.myexams.presentation.features.boot.startBootActivity
import com.vald3nir.myexams.presentation.features.home.startExamsHomeActivity
import com.vald3nir.toolkit.helpers.baseclasses.BaseScreenScope


internal data class ProfileScope(val viewModel: ProfileViewModel) : BaseScreenScope(viewModel, null) {

    val eventUpdateProfile: (ProfileDTO) -> Unit = {
        viewModel.updateProfile(profile = it)
    }

    val eventCreateProfile: (birthday: String?, gender: String?) -> Unit = { birthday, gender ->
        viewModel.createProfile(birthday = birthday, gender = gender)
    }

    val eventRedirectHome: (Context) -> Unit = { context ->
        context.startExamsHomeActivity()
    }

    fun Context.checkRedirectEvents(event: Any?) {
        when (event) {
            ScreenEvents.UserLogout -> startBootActivity()
            ScreenEvents.GoHome -> startExamsHomeActivity()
            else -> Unit
        }
    }

    fun eventChangeUser() {
        viewModel.userLogout()
    }
}