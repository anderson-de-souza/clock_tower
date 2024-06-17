package com.anderson.clocktower.livedata

import android.content.Context
import androidx.lifecycle.LiveData
import com.anderson.clocktower.database.MainDAO
import com.anderson.clocktower.database.RealDatabase
import com.anderson.clocktower.schema.AlarmAndWeek
import com.anderson.clocktower.schema.ExactAlarm
import com.anderson.clocktower.schema.RepeatAlarm
import com.anderson.clocktower.schema.Week
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class DataRepository(val context: Context) {

    private val database: RealDatabase by lazy {
        RealDatabase.getInstance(context)
    }

    private val mainDAO: MainDAO by lazy {
        database.getMainDAO()
    }

    private val executor: ExecutorService by lazy {
        Executors.newSingleThreadExecutor()
    }

    fun saveExactAlarm(alarm: ExactAlarm) {
        executor.execute {
            mainDAO.saveExactAlarm(alarm)
        }
    }

    fun saveRepeatAlarm(alarm: RepeatAlarm) {
        executor.execute {
            mainDAO.saveRepeatAlarm(alarm)
        }
    }

    fun saveWeek(week: Week) {
        executor.execute {
            mainDAO.saveWeek(week)
        }
    }

    fun changeExactAlarm(alarm: ExactAlarm) {
        executor.execute {
            mainDAO.changeExactAlarm(alarm)
        }
    }

    fun changeRepeatAlarm(alarm: RepeatAlarm) {
        executor.execute {
            mainDAO.changeRepeatAlarm(alarm)
        }
    }

    fun changeWeek(week: Week) {
        executor.execute {
            mainDAO.changeWeek(week)
        }
    }

    fun removeExactAlarm(alarm: ExactAlarm) {
        executor.execute {
            mainDAO.removeExactAlarm(alarm)
        }
    }

    fun removeRepeatAlarm(alarm: RepeatAlarm) {
        executor.execute {
            mainDAO.removeRepeatAlarm(alarm)
        }
    }

    fun removeWeek(week: Week) {
        executor.execute {
            mainDAO.removeWeek(week)
        }
    }

    fun getAllExactAlarms(): LiveData<List<ExactAlarm>> {
        return mainDAO.getAllExactAlarms()
    }

    fun getAllRepeatAlarms(): LiveData<List<AlarmAndWeek>> {
        return mainDAO.getAllRepeatAlarm()
    }

}