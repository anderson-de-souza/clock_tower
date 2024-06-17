package com.anderson.clocktower.schema

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

import java.time.LocalDateTime
import java.time.LocalTime

@Entity
data class RepeatAlarm(

    @PrimaryKey(autoGenerate = true)
    var id: Int?,

    var fromTimestamp: LocalDateTime,
    var timeOfDay: LocalTime,

    @Embedded var alarm: Alarm

)
