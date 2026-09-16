package com.vald3nir.myexams.presentation.features.profile.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.vald3nir.myexams.domain.dto.ProfileDTO

internal class HomeProfileProvider : PreviewParameterProvider<HomeProfileUiModel> {
    override val values: Sequence<HomeProfileUiModel> = sequenceOf(generateFakeData())
}

private fun generateFakeData() = HomeProfileUiModel(
    profile = ProfileDTO(
        name = "Vald3nir",
        email = "vald3nir@gmail.com",
        birthday = "15/08/1991",
        gender = "Feminino",
        photoUrl = "https://avatars.githubusercontent.com/u/12345678?v=4"
    )
)