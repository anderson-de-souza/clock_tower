package com.anderson.clocktower.schema

import androidx.room.Embedded
import androidx.room.Relation

data class AlarmAndWeek (
    @Embedded var alarm: RepeatAlarm,
    @Relation (
        parentColumn = "id",
        entityColumn = "repeatAlarmId"
    )
    var week: Week
)