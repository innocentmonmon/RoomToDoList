package com.example.newuser.roomtodolist

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlin.coroutines.experimental.CoroutineContext

@Database(entities = arrayOf(RoomTask::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun taskDao(): TaskDao
    companion object {
        private var INSTANCE: AppDatabase ?= null
        fun getDatabase(context: Context):AppDatabase{
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,
                    "monTask.db").build()
        }
         //   AppDatabase.getDatabase(context).taskDao().insert(RoomTask(1,"xinxxx"))
            return INSTANCE as AppDatabase
        }

    }
    fun destroyInstance(){
        INSTANCE = null
    }

}