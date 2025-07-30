package com.emprestaai.db.fb

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.firestore
class FBDatabase {
    interface Listener {
        fun onUserLoaded(user: FBUser)
        fun onUserSignOut()
        fun onItemAdded(item: FBItem)
        fun onItemUpdated(item: FBItem)
        fun onItemRemoved(item: FBItem)
    }

    private val auth = Firebase.auth
    private val db = Firebase.firestore
    private var  publicItemsReg: ListenerRegistration? = null
    private var listener: Listener? = null

    init {
        auth.addAuthStateListener { auth ->
            val user = auth.currentUser

            if (user == null) {
                publicItemsReg?.remove()
                listener?.onUserSignOut()
                return@addAuthStateListener
            }

            db.collection("users").document(user.uid)
                .get()
                .addOnSuccessListener { doc ->
                    doc.toObject(FBUser::class.java)?.let { userData ->
                        listener?.onUserLoaded(userData)
                    }
                }

            publicItemsReg?.remove()
            publicItemsReg = db.collection("items")
                .addSnapshotListener { snapshots, ex ->
                    if (ex != null) return@addSnapshotListener

                    snapshots?.documentChanges?.forEach { change ->
                        val item = change.document.toObject(FBItem::class.java)

                        when (change.type) {
                            DocumentChange.Type.ADDED -> listener?.onItemAdded(item)
                            DocumentChange.Type.MODIFIED -> listener?.onItemUpdated(item)
                            DocumentChange.Type.REMOVED -> listener?.onItemRemoved(item)
                        }
                    }
                }
        }
    }

    fun setListener(listener: Listener?) {
        this.listener = listener
    }

    fun register(user: FBUser) {
        val currentUser = auth.currentUser
            ?: throw RuntimeException("User not logged in!")
        db.collection("users").document(currentUser.uid).set(user)
    }

    fun addItem(item: FBItem) {
        val currentUser = auth.currentUser
            ?: throw RuntimeException("User not logged in!")

        if (item.name.isNullOrEmpty())
            throw RuntimeException("Item name is required")

        val itemWithOwner = item.copy(ownerId = currentUser.uid)

        db.collection("items")
            .add(itemWithOwner)
    }

    fun removeItem(itemId: String) {
        val currentUser = auth.currentUser
            ?: throw RuntimeException("User not logged in!")

        val itemRef = db.collection("items").document(itemId)

        itemRef.get().addOnSuccessListener { doc ->
            val item = doc.toObject(FBItem::class.java)
            if (item?.ownerId == currentUser.uid) {
                doc.reference.delete()
            } else {
                throw RuntimeException("You do not have permission to delete this item")
            }
        }
    }

    fun getUserItems(onResult: (List<FBItem>) -> Unit) {
        val currentUser = auth.currentUser
            ?: throw RuntimeException("User not logged in!")

        db.collection("items")
            .whereEqualTo("ownerId", currentUser.uid)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val items = querySnapshot.documents.mapNotNull {
                    it.toObject(FBItem::class.java)
                }
                onResult(items)
            }
    }

}