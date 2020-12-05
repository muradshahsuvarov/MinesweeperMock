package com.example.toghrultodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_gps.*
import java.lang.Exception


class GpsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var googleMap : GoogleMap

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        map.onCreate(savedInstanceState)
        map.onResume()

        map.getMapAsync(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root =  inflater.inflate(R.layout.fragment_gps, container, false)



        return root
    }

    override fun onMapReady(map: GoogleMap) {

        map?.let{
            googleMap = it
        }
    }



}