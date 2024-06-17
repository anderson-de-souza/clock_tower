package com.anderson.clocktower.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.anderson.clocktower.schema.Alarm
import com.anderson.clocktower.schema.ExactAlarm
import com.anderson.clocktower.schema.RepeatAlarm
import com.anderson.clocktower.schema.AlarmAndWeek
import com.anderson.clocktower.schema.Week


@Dao
interface MainDAO {

    @Insert
    fun saveExactAlarm(alarm: ExactAlarm)

    @Insert
    fun saveRepeatAlarm(alarm: RepeatAlarm)

    @Insert
    fun saveWeek(week: Week)

    @Update
    fun changeExactAlarm(alarm: ExactAlarm)

    @Update
    fun changeRepeatAlarm(alarm: RepeatAlarm)

    @Update
    fun changeWeek(week: Week)

    @Delete
    fun removeExactAlarm(alarm: ExactAlarm)

    @Delete
    fun removeRepeatAlarm(alarm: RepeatAlarm)

    @Delete
    fun removeWeek(week: Week)

    @Query("select * from exactalarm")
    fun getAllExactAlarms(): LiveData<List<ExactAlarm>>

    @Transaction
    @Query("select * from repeatalarm")
    fun getAllRepeatAlarm(): LiveData<List<AlarmAndWeek>>

    @Query("select name, description, imageUri, songUri from " +
            " (select id, name, description, imageUri, songUri from exactalarm " +
            " union all select id, name, description, imageUri, songUri from repeatalarm) " +
            " group by id order by id")
    fun getAllAlarms(): LiveData<List<Alarm>>

}