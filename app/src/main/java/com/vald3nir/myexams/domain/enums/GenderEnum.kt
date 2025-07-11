package com.vald3nir.myexams.domain.enums

internal enum class GenderEnum(val description: String) {
    MALE("Masculino"),
    FEMALE("Feminino")
}

internal fun genderEnumList(): List<String> = GenderEnum.entries.map { it.description }