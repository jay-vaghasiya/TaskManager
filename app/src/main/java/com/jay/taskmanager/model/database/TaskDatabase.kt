package com.jay.taskmanager.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jay.taskmanager.model.datamodel.Task
import com.jay.taskmanager.model.dao.TaskDao
import com.jay.taskmanager.model.typeConverter.TaskConverters
import com.jay.taskmanager.utils.Constant.DATABASE

@Database(entities = [Task::class], version = 1, exportSchema = false)
@TypeConverters(TaskConverters::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    DATABASE
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}