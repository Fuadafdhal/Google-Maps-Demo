package com.afdhal_studio.googlemapsdemo

import android.graphics.Color
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import kotlinx.coroutines.delay

/**
 *Created by Muh Fuad Afdhal on 16/05/2021
 */

class Shapes {

    private val jakarta = LatLng(-6.174722685952155, 106.82711406885132)
    private val bandung = LatLng(-6.902279437865977, 107.61879009793748)
    private val kolaka = LatLng(-4.049902876681206, 121.60204186452432)
    private val makassar = LatLng(-5.141564480565337, 119.4528415996294)
    private val balikpapan = LatLng(-1.26698433506293, 116.86744596095504)


    private val p0 = LatLng(-6.209348525668122, 106.81900318465871)
    private val p1 = LatLng(-6.736057977011878, 108.5658292146562)
    private val p2 = LatLng(-7.608078686209653, 108.22525307044286)
    private val p3 = LatLng(-7.104144746853909, 106.59103689458044)

    private val p00 = LatLng(-6.553273158287494, 107.38754561895036)
    private val p01 = LatLng(-6.613299533021818, 107.87918376261315)
    private val p02 = LatLng(-7.150475846708906, 107.72262859954735)
    private val p03 = LatLng(-6.916047359834148, 107.19803147418646)


    suspend fun addPolyline(map: GoogleMap) {

        val pattern = listOf(Dot(), Gap(30f), Dash(50f))

        val polyline = map.addPolyline(
            PolylineOptions().apply {
                add(jakarta, bandung, makassar)
                width(40f)
                color(Color.BLACK)
                geodesic(false)
                clickable(true)
                pattern(pattern)
                jointType(JointType.BEVEL)
            }
        )
        delay(5000)
        polyline.points = listOf(jakarta, balikpapan, makassar, kolaka)
    }

    suspend fun addPolygon(map: GoogleMap) {
        val polygon = map.addPolygon(
            PolygonOptions().apply {
                add(p0, p1, p2, p3)
                fillColor(R.color.black)
                strokeColor(R.color.black)
//                addHole(listOf(p00,p01,p02,p03))
            }
        )

        delay(2000)
        val polygon2 = map.addPolygon(
            PolygonOptions().apply {
                add(p00, p01, p02, p03)
                fillColor(R.color.black)
                strokeColor(R.color.black)
                zIndex(1f)
            }
        )
    }

    suspend fun addCircle(map: GoogleMap) {
        val circle = map.addCircle(
            CircleOptions().apply {
                center(bandung)
                radius(50000.0)
                fillColor(R.color.purple_500)
                strokeColor(R.color.purple_500)
//                clickable(true)
            }
        )
//
//        delay(3000)
//        circle.fillColor = R.color.black
    }
}