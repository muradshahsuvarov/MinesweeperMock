package com.example.toghrultodo.List

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.toghrultodo.Entity.Todo
import com.example.toghrultodo.R
import kotlinx.android.synthetic.main.fragment_add_todo.view.*
import kotlinx.android.synthetic.main.todo_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class ToDoAdapter : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

     var todoItemLits = emptyList<Todo>()

    class ToDoViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoAdapter.ToDoViewHolder {
        return ToDoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false))
    }

    override fun onBindViewHolder(holder: ToDoAdapter.ToDoViewHolder, position: Int) {
        val todoItem  = todoItemLits[position]
        if (todoItem != null) {
            holder.itemView.CapitalLetter.text = todoItem.Name.take(1).capitalize().toString()
            holder.itemView.listNameTextView.text = todoItem.Name.toString()
            holder.itemView.listDetailsTextView.text = GetConvertMsToDate(todoItem.Date.timeInMillis)
        }

    }

    override fun getItemCount(): Int {
        return todoItemLits.size
    }

    // Gets converted milliseconds to Date in the format specified
    private fun GetConvertMsToDate(ms: Long): String? {
        var dateFormat: String? = "dd/MM/yyyy hh:mm:ss"
        var result : String? = null

            var cal : Calendar = Calendar.getInstance()
            cal.timeInMillis = ms
            var formattedDate : SimpleDateFormat = SimpleDateFormat(dateFormat)
            result =  formattedDate.format(cal.time)

                return result

    }

     fun setData(todoList : List<Todo>){
        this.todoItemLits = todoList
        notifyDataSetChanged()
    }
}