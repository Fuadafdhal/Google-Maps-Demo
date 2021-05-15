package com.afdhal_studio.googlemapsdemo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
        val jakarta = LatLng(-6.174722685952155, 106.82711406885132)
        val bandung = LatLng(-6.902279437865977, 107.61879009793748)
        map.addMarker(MarkerOptions().position(jakarta).title("Marker in Home"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(jakarta, 15f))
        map.uiSettings.apply {
            isZoomControlsEnabled = true
        }
        typeAndStyle.setMapStyle(this, map)

//        lifecycleScope.launch {
//            delay(4000)
////            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.jakarta),2000,object : GoogleMap.CancelableCallback {
////                override fun onFinish() {
////                    Toast.makeText(this@MapsActivity, "Finished", Toast.LENGTH_SHORT).show()
////                }
////
////                override fun onCancel() {
////                    Toast.makeText(this@MapsActivity, "Cancelable", Toast.LENGTH_SHORT).show()
////                }
////            })
////            map.animateCamera(CameraUpdateFactory.zoomTo(10f),2000,null)
////            map.animateCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewport.bandungBound, 100),2000,null) // Make Animation move camera
////            map.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraAndViewport.bandungBound.center,10f))
////            map.setLatLngBoundsForCameraTarget(cameraAndViewport.bandungBound)
//
//        }

        onMapClicked()
        onMapLongClicked()
    }

    private fun onMapClicked() {
        map.setOnMapClickListener {
            Toast.makeText(this, "Single Click", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onMapLongClicked() {
        map.setOnMapLongClickListener {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
            map.addMarker(MarkerOptions().position(it))

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_types_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        typeAndStyle.setMapType(item, map)
        return super.onOptionsItemSelected(item)
    }


}