package com.afdhal_studio.googlemapsdemo.misc

import android.content.Context
import android.util.Log
import android.view.MenuItem
import com.afdhal_studio.googlemapsdemo.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MapStyleOptions

/**
 *Created by Muh Fuad Afdhal on 16/05/2021
 */

class TypeAndStyle {
    fun setMapStyle(context: Context, googleMap: GoogleMap) {
        try {
            val success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(context, R.raw.mapstyle)
            )
            if (!success) {
                Log.d("Maps", "Failed to add Style")
            }
        } catch (e: Exception) {
            Log.d("Maps", e.message.toString())
        }
    }

    fun setMapType(item: MenuItem, map: GoogleMap) {
        when (item.itemId) {
            R.id.normal_map -> {
                map.mapType = GoogleMap.MAP_TYPE_NORMAL
            }

            R.id.hybrid_map -> {
                map.mapType = GoogleMap.MAP_TYPE_HYBRID
            }

            R.id.satellite_map -> {
                map.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }

            R.id.terrain_map -> {
                map.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }
            R.id.none_map -> {
                map.mapType = GoogleMap.MAP_TYPE_NONE
            }
        }
    }
}