package com.emprestaai.model

import com.emprestaai.db.fb.FBItem
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.Exclude

data class Item(
    val name: String,
    val description: String? = null,
    val condition: String? = null,
    val location: LatLng? = null,
    val ownerId: String? = null,
    @get:Exclude val id: String? = null
) {
    fun toFBItem(): FBItem {
        return FBItem(
            id = this.id,
            name = this.name,
            description = this.description,
            condition = this.condition,
            location = location,
            ownerId = this.ownerId
        )
    }
}
