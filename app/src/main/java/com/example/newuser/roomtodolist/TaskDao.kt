package com.example.newuser.roomtodolist

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface TaskDao{
    @Query("select * from tasks")
    fun getAll() : List<RoomTask>

    @Delete
    fun delete(task: RoomTask)

    @Insert
    fun insert(task: RoomTask)

    @Insert
    fun insertAll(tasks: MutableList<RoomTask>)

    @Query("select * from tasks where id = :id")
    fun getById(id: Int) : RoomTask
}