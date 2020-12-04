package com.example.toghrultodo.Entity

import androidx.lifecycle.LiveData

class TodoRepo(private val todoDAO: TodoDao) {

    var readEverything: LiveData<List<Todo>> = todoDAO.readEverything()

    suspend fun addTodo(todo: Todo){
        todoDAO.addTodo(todo)
    }

}