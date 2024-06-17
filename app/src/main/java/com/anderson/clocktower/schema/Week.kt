package com.anderson.clocktower.schema

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Week (

    @PrimaryKey(autoGenerate = true)
    var id: Int?,

    var repeatAlarmId: Int,

    val sunday: Boolean = false,
    val monday: Boolean = false,
    val tuesday: Boolean = false,
    val wednesday: Boolean = false,
    val thursday: Boolean = false,
    val friday: Boolean = false,
    val saturday: Boolean = false

)