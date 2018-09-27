package com.example.newuser.roomtodolist

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "tasks")
class RoomTask(@PrimaryKey(autoGenerate = true) val id: Int,
               @ColumnInfo(name="newTask") var newTask: String) {
}