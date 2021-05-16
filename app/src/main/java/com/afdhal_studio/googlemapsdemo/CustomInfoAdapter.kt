package com.afdhal_studio.googlemapsdemo

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

/**
 *Created by Muh Fuad Afdhal on 16/05/2021
 */

class CustomInfoAdapter(context: Context) : GoogleMap.InfoWindowAdapter {
    @SuppressLint("InflateParams")
    private val contextView = (context as Activity).layoutInflater.inflate(R.layout.custom_info_window, null)

    override fun getInfoWindow(marker: Marker): View? {
        renderViews(marker, contextView)
        return contextView
    }

    override fun getInfoContents(marker: Marker): View? {
        renderViews(marker, contextView)
        return contextView
    }

    private fun renderViews(marker: Marker, contentView: View) {
        val title = marker.title
        val description = marker.snippet



        val textViewTitle = contentView.findViewById<TextView>(R.id.textTitle)
        val textViewSubTitle = contentView.findViewById<TextView>(R.id.textSubTitle)

        textViewTitle.text = title
        textViewSubTitle.text = description
    }
}