package com.example.toghrultodo.Entity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewmodel(app : Application) : AndroidViewModel(app){

    val readEverything: LiveData<List<Todo>>
    private val repo : TodoRepo

    init {
        val todoDao = TodoDatabase.getDB(app).todoDao()
        repo = TodoRepo(todoDao)
        readEverything = repo.readEverything
    }


    fun addTodo(todo:Todo){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addTodo(todo)

        }
    }

}