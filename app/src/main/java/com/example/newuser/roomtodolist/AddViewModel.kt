package com.example.newuser.roomtodolist

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.persistence.room.Room
import android.content.Context
import org.jetbrains.anko.doAsync

class AddViewModel(var tasks : MutableList<RoomTask> = ArrayList()):ViewModel() {
    fun addTask(context: Context) {
        val db = AppDatabase.getDatabase(context)
        if (tasks.isEmpty()) {
            doAsync {
                tasks.addAll(db.taskDao().getAll())
            }
        }
    }
}