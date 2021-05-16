package com.afdhal_studio.googlemapsdemo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.afdhal_studio.googlemapsdemo.databinding.ActivityMapsBinding
import com.afdhal_studio.googlemapsdemo.misc.CameraViewport
import com.afdhal_studio.googlemapsdemo.misc.TypeAndStyle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap

    private lateinit var binding: ActivityMapsBinding

    private val typeAndStyle by lazy { TypeAndStyle() }
    private val cameraAndViewport by lazy { CameraViewport() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_types_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        typeAndStyle.setMapType(item, map)
        return super.onOptionsItemSelected(item)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in jakarta and move the camera
        val jakarta = LatLng(-6.174722685952155, 106.82711406885132)
        val bandung = LatLng(-6.902279437865977, 107.61879009793748)
        val ancol = LatLng(-6.128074434660651, 106.83392975804425)

        val jakartaMarker =
            map.addMarker(
                MarkerOptions()
                    .position(jakarta)
                    .title("Marker in Monas")
                    .snippet("Lorem Ipsum")

            )
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(jakarta, 15f))
        map.uiSettings.apply {
            isZoomControlsEnabled = true
        }
        map.setInfoWindowAdapter(CustomInfoAdapter(this))

        typeAndStyle.setMapStyle(this, map)
    }
}