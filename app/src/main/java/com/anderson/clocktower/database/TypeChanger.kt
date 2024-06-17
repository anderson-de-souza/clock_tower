package com.anderson.clocktower.database

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TypeChanger {

    @TypeConverter
    fun fromLocalTime(time: LocalTime): String {
        return time.format(DateTimeFormatter.ofPattern("hh:mm a"))
    }

    @TypeConverter
    fun toLocalTime(time: String): LocalTime {
        return LocalTime.parse(time)
    }

    @TypeConverter
    fun fromLocalDateTime(datetime: LocalDateTime): String {
        return datetime.format(DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a"))
    }

    @TypeConverter
    fun toLocalDateTime(datetime: String): LocalDateTime {
        return LocalDateTime.parse(datetime)
    }

}