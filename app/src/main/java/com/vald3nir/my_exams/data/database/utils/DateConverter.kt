package com.vald3nir.my_exams.data.database.utils

import androidx.room.TypeConverter
import com.vald3nir.core.extensions.fromJson
import com.vald3nir.core.extensions.toJson

class DateConverter {

    @TypeConverter
    fun listAnyToJson(value: List<Any>?): String? = value?.toJson()

    @TypeConverter
    fun jsonToListAny(value: String) = fromJson(value, Array<Any>::class.java).toList()
}