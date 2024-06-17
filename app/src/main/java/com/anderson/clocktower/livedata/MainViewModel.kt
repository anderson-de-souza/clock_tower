package com.anderson.clocktower.livedata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.anderson.clocktower.schema.AlarmAndWeek
import com.anderson.clocktower.schema.ExactAlarm
import com.anderson.clocktower.schema.RepeatAlarm
import com.anderson.clocktower.schema.Week

class MainViewModel(app: Application): AndroidViewModel(app) {

    private val dataRepository: DataRepository by lazy {
        DataRepository(app)
    }

    val allExactAlarms: LiveData<List<ExactAlarm>> by lazy {
        dataRepository.getAllExactAlarms()
    }

    val allRepeatAlarm: LiveData<List<AlarmAndWeek>> by lazy {
        dataRepository.getAllRepeatAlarms()
    }

    fun saveExactAlarm(alarm: ExactAlarm) {
        dataRepository.saveExactAlarm(alarm)
    }

    fun saveRepeatAlarm(alarm: RepeatAlarm) {
        dataRepository.saveRepeatAlarm(alarm)
    }

    fun saveWeek(week: Week) {
        dataRepository.saveWeek(week)
    }

    fun changeExactAlarm(alarm: ExactAlarm) {
        dataRepository.changeExactAlarm(alarm)
    }

    fun changeRepeatAlarm(alarm: RepeatAlarm) {
        dataRepository.changeRepeatAlarm(alarm)
    }

    fun changeWeek(week: Week) {
        dataRepository.changeWeek(week)
    }

    fun removeExactAlarm(alarm: ExactAlarm) {
        dataRepository.removeExactAlarm(alarm)
    }

    fun removeRepeatAlarm(alarm: RepeatAlarm) {
        dataRepository.removeRepeatAlarm(alarm)
    }

    fun removeWeek(week: Week) {
        dataRepository.removeWeek(week)
    }

}