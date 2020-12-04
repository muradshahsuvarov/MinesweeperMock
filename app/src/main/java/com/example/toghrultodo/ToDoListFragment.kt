package com.example.toghrultodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toghrultodo.Entity.TodoViewmodel
import com.example.toghrultodo.List.ToDoAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception

class ToDoListFragment : Fragment() {

    private lateinit var todoViewModel: TodoViewmodel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_to_do_list, container, false)

        val fab: FloatingActionButton = root.findViewById(R.id.floatingAddButton)
        fab.setOnClickListener { view ->
            /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show() */
            findNavController().navigate(R.id.action_nav_todo_to_addTodoFragment)

        }


        try{
            val adapter = ToDoAdapter()
            val recyclerView = root.findViewById<RecyclerView>(R.id.myRecyclerView)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext()) // ERROR HAPPENS HERE

            if(recyclerView.layoutManager == null){
                Toast.makeText(context, "BRAT NULL-di", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "NOT NULL", Toast.LENGTH_SHORT).show()

            }


            todoViewModel = ViewModelProvider(this).get(TodoViewmodel::class.java)
            todoViewModel.readEverything.observe(viewLifecycleOwner, { todoItem ->

                adapter.setData(todoItem)

            })
        } catch (exception: Exception) {
            Toast.makeText(context, "$exception", Toast.LENGTH_SHORT).show()
        }



        return root
    }

}