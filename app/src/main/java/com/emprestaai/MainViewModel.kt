package com.emprestaai

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emprestaai.db.fb.FBDatabase
import com.emprestaai.db.fb.FBItem
import com.emprestaai.db.fb.FBUser
import com.emprestaai.model.City
import com.emprestaai.model.Item
import com.emprestaai.model.User

class MainViewModel(private val db: FBDatabase) : ViewModel(),
    FBDatabase.Listener {

    private val _user = MutableLiveData<User?>(null)
    val user: LiveData<User?> get() = _user

    private val _items = mutableStateListOf<Item>()

    val items: List<Item>
        get() = _items.toList()

    private val _myItems = mutableStateListOf<Item>()

    val myItems: List<Item> get() = _myItems

    init {
        db.setListener(this)
    }

    fun add(item: Item) {
        val fbItem = item.toFBItem()
        db.addItem(fbItem)
    }

    fun remove(item: Item) {
        item.id?.let { db.removeItem(item.toFBItem()) }
    }

    override fun onUserLoaded(user: FBUser) {
        try {
            println("DEBUG: FBUser received - $user")
            val convertedUser = user.toUser()
            println("DEBUG: Converted user - $convertedUser")
            _user.value = convertedUser

            getUserItems()
        } catch (e: Exception) {
            println("DEBUG: Error in onUserLoaded - ${e.message}")
            e.printStackTrace()
        }
    }

    fun getUserItems() {
        db.getUserItems { fbItems ->
            _myItems.clear()
            _myItems.addAll(fbItems.map { it.toItem() })
        }
    }

    override fun onUserSignOut() {
        _user.value = null
        _items.clear()
    }


    override fun onItemAdded(item: FBItem) {
        _items.add(item.toItem())
    }

    override fun onItemUpdated(item: FBItem) {
        val updated = item.toItem()
        val index = _items.indexOfFirst { it.id == updated.id }
        if (index != -1) {
            _items[index] = updated
        }
    }

    override fun onItemRemoved(item: FBItem) {
        _items.removeIf { it.id == item.id }
    }
}
