package com.emprestaai.model

import com.google.android.gms.maps.model.LatLng

data class Item(
    val name: String,
    val description: String? = null,
    val condition: String? = null,
    val location: LatLng
)
