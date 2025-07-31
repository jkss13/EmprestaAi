package com.emprestaai.db.fb


import com.emprestaai.model.Item
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.Exclude

data class FBItem (
    var name: String? = null,
    var description: String? = null,
    var condition: String? = null,
    var location: LatLng? = null,
    var ownerId: String? = null,

    @get:Exclude var id: String? = null
)
{
    fun toItem(): Item {
        return Item(name!!, description, condition, location, id = id)

    }
}

