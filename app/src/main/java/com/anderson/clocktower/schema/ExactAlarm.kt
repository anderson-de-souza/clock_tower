package com.anderson.clocktower.schema

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

import java.time.LocalDateTime

@Entity
data class ExactAlarm(

    @PrimaryKey(autoGenerate = true)
    var id: Int?,

    var fromTimestamp: LocalDateTime,
    var toTimestamp: LocalDateTime,

    @Embedded var alarm: Alarm,

    )