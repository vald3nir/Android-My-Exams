package com.vald3nir.myexams.domain.mock

import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.domain.enums.GenderEnum

internal val profileMock = ProfileDTO(
    name = "Fulano de Tal",
    birthday = "20/03/1991",
    email = "test@gmail.com",
    gender = GenderEnum.MALE.description,
    photoUrl = "https://lh3.googleusercontent.com/a/ACg8ocLaA4brZnA2vSesrgyBoumd7O2A41yqaJ4TYpJ5qrIEvM8mvJM=s96-c"
)