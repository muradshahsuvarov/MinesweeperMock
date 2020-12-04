package com.example.toghrultodo.Entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodo(todo : Todo)


    @Query("SELECT * FROM todo_table ORDER BY Id DESC")
    fun readEverything() : LiveData<List<Todo>>

}