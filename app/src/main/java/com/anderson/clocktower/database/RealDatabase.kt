package com.anderson.clocktower.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.anderson.clocktower.schema.ExactAlarm
import com.anderson.clocktower.schema.RepeatAlarm
import com.anderson.clocktower.schema.Week

@Database(
    entities = [
        ExactAlarm::class,
        RepeatAlarm::class,
        Week::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeChanger::class)
abstract class RealDatabase: RoomDatabase() {

    abstract fun getMainDAO(): MainDAO

    companion object {

        private var instance: RealDatabase? = null

        fun getInstance(context: Context): RealDatabase {

            synchronized(RealDatabase::class) {

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, RealDatabase::class.java, "digitalclock.db"
                    ).fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .addCallback(object: RoomDatabase.Callback() {

                            override fun onCreate(db: SupportSQLiteDatabase) {

                            }

                        })
                        .build()
                }

            }

            return instance!!

        }

    }

}