package com.afdhal_studio.googlemapsdemo.misc

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

/**
 *Created by Muh Fuad Afdhal on 16/05/2021
 */

class CameraViewport {

    val jakarta: CameraPosition = CameraPosition.Builder()
        .target(LatLng(-6.174722685952155, 106.82711406885132))
        .zoom(17f)
        .bearing(100f)
        .tilt(45f)
        .build()

    val bandungBound = LatLngBounds(
        LatLng(-6.975528988994318, 107.55028819911398),
        LatLng(-6.844309841141881, 107.67748926910495)
    )
}