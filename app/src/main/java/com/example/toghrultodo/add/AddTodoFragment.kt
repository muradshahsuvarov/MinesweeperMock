package com.example.toghrultodo.add

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.toghrultodo.Entity.Todo
import com.example.toghrultodo.Entity.TodoViewmodel
import com.example.toghrultodo.R
import kotlinx.android.synthetic.main.fragment_add_todo.*
import java.text.SimpleDateFormat
import java.util.*


class AddTodoFragment : Fragment()  {


    // Date
    var Year : Int? = null
    var Month : Int? = null
    var Day : Int? = null

    var Hour : Int? = null
    var Minute : Int? = null

    var myDate : String? = null
    var myTime : String? = null
    var overallDate : String? = null

    private lateinit var todoViewModel : TodoViewmodel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root =  inflater.inflate(R.layout.fragment_add_todo, container, false)

        todoViewModel = ViewModelProvider(this).get(TodoViewmodel::class.java)

        root.findViewById<Button>(R.id.Date).setOnClickListener {

// Get Current Time
            // Get Current Time
            val c = Calendar.getInstance()
            Hour = c[Calendar.HOUR_OF_DAY]
            Minute = c[Calendar.MINUTE]

            // Launch Time Picker Dialog

            // Launch Time Picker Dialog
            val timePickerDialog = TimePickerDialog(
                context,
                OnTimeSetListener { view, hourOfDay, minute -> Date.setText("$hourOfDay:$minute") },
                Hour!!,
                Minute!!,
                false
            )
            timePickerDialog.show()


        }

        root.findViewById<Button>(R.id.Date).setOnClickListener {

            // Call Date Picker
            DatePicker(root)

            // Call Time Picker
            TimePicker()

        }


        root.findViewById<Button>(R.id.submitButton).setOnClickListener {

            val tName = nameTextView.text.toString()
            val tDetails = detailsTextView.text.toString()
            val tDate = DateTextView.text.toString()

            if( ( tName == null && tDetails == null && tDate == null ) == false){

                val cal = Calendar.getInstance()
                cal.set(Year!!, Month!!, Day!!)
                val myTmpCalDate = cal.time

                var tmpDate: Date
                tmpDate = myTmpCalDate

                val calendar = Calendar.getInstance()
                Hour?.let { it1 -> calendar.set(Calendar.HOUR, it1) };
                Minute?.let { it1 -> calendar.set(Calendar.MINUTE, it1) };
                calendar.set(Calendar.YEAR, Year!!)
                calendar.set(Calendar.MONTH, Month!!)
                calendar.set(Calendar.DAY_OF_MONTH, Day!!)

                var todoItem = Todo(0, tName, tDetails, calendar)
                todoViewModel.addTodo(todoItem)
                Toast.makeText(context, "Todo is succesfully added!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addTodoFragment_to_nav_todo)

            }else{

                if(nameTextView.text.toString().isEmpty() == true){
                    nameTextView.setText("")
                    nameTextView.setHintTextColor(getResources().getColor(R.color.red))
                }

                if(detailsTextView.text.toString().isEmpty() == true){
                    detailsTextView.setText("")
                    detailsTextView.setHintTextColor(getResources().getColor(R.color.red))
                }

                if(DateTextView.text.toString() == "Start Date"){
                    DateTextView.setTextColor(getResources().getColor(R.color.red))
                }

            }

        }



        return root
    }

    private fun DatePicker(view: View){

        val dPicker = DatePickerDialog(
            view.context,
            DatePickerDialog.OnDateSetListener { _, mYear, mMonth, mDay ->
                myDate = "$mYear/$mMonth/$mDay"
                Year = mYear
                Month = mMonth
                Day = mDay
                overallDate = myDate + " " + myTime
                DateTextView.setText(overallDate)

            },
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )

        dPicker.show()
    }


    private fun TimePicker(){

        // Get Current Time
        // Get Current Time
        val c = Calendar.getInstance()
        Hour = c[Calendar.HOUR_OF_DAY]
        Minute = c[Calendar.MINUTE]

        // Launch Time Picker Dialog

        // Launch Time Picker Dialog
        val timePickerDialog = TimePickerDialog(
            context,
            OnTimeSetListener { view, hourOfDay, minute ->
                myTime = "$hourOfDay:$minute"
                Hour = hourOfDay
                Minute = minute
            },
            Hour!!,
            Minute!!,
            false
        )
        timePickerDialog.show()

    }


}