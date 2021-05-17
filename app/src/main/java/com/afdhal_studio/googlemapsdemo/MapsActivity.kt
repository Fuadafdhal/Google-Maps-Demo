package com.afdhal_studio.googlemapsdemo

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
    private val shapes by lazy { Shapes() }
    private val overlays by lazy { Overlays() }

    private val jakarta = LatLng(-6.174722685952155, 106.82711406885132)


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

        val jakartaMarker =
            map.addMarker(
                MarkerOptions()
                    .position(jakarta)
                    .title("Marker in Monas")
                    .snippet("Lorem Ipsum")

            )
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(jakarta, 10f))
        map.uiSettings.apply {
            isZoomControlsEnabled = true
            isMyLocationButtonEnabled = true
        }

        typeAndStyle.setMapStyle(this, map)
        checkLocationPermission()
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map.isMyLocationEnabled = true
            Toast.makeText(this, "Already Enabled", Toast.LENGTH_SHORT).show()
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            1
        )
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode != 1) {
            return
        }
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Gandted!", Toast.LENGTH_SHORT).show()
            map.isMyLocationEnabled = true
        } else {
            Toast.makeText(this, "Wer need your permission", Toast.LENGTH_SHORT).show()
        }
    }
}