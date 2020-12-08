package com.example.toghrultodo.Details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.toghrultodo.R
import kotlinx.android.synthetic.main.fragment_details.view.*
import java.text.SimpleDateFormat
import java.util.*

class DetailsFragment : Fragment() {

    private val args by navArgs<DetailsFragmentArgs>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_details, container, false)

        root.nameInDetails.text = args.currentToDo.Name.toString()
        root.detailsInDetails.text = args.currentToDo.Details.toString()
        root.dateInDetails.text = GetConvertMsToDate(args.currentToDo.Date.timeInMillis)


        return root
    }

    // Gets converted milliseconds to Date in the format specified
    private fun GetConvertMsToDate(ms: Long): String? {
        var dateFormat: String? = "dd/MM/yyyy hh:mm:ss"
        var result : String? = null

        var cal : Calendar = Calendar.getInstance()
        cal.timeInMillis = ms
        var formattedDate : SimpleDateFormat = SimpleDateFormat(dateFormat)
        result =  formattedDate.format(cal.time)

        if(result.isEmpty() == true){
            return "dd/MM/yyyy hh:mm:ss"
        }

        return result

    }

}