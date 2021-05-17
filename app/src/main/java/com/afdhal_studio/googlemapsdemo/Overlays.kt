package com.afdhal_studio.googlemapsdemo

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*

/**
 *Created by Muh Fuad Afdhal on 18/05/2021
 */

class Overlays {
    private val jakarta = LatLng(-6.174722685952155, 106.82711406885132)

    private val jakartaBounds = LatLngBounds(
        LatLng(-6.221209423555922, 106.77458414152392),
        LatLng(-6.116078295316692, 106.86213143665941)
    )

    fun addGroundOverlay(map: GoogleMap): GroundOverlay? {
        return map.addGroundOverlay(
            GroundOverlayOptions().apply {
//                position(jakarta, 10000f, 10000f)
                positionFromBounds(jakartaBounds)
                image(BitmapDescriptorFactory.fromResource(R.drawable.image_circel))
            }
        )
    }

    fun addGroundOverlayWithTag(map: GoogleMap): GroundOverlay? {
        val groundOverlay = map.addGroundOverlay(
            GroundOverlayOptions().apply {
//                position(jakarta, 10000f, 10000f)
                positionFromBounds(jakartaBounds)
                image(BitmapDescriptorFactory.fromResource(R.drawable.image_circel))
            }
        )
        groundOverlay?.tag = "My Ground Overlay"


        return groundOverlay
    }
}