package com.example.cryptocoinloodos.modules.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptocoinloodos.repo.CryptoCoin
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FavoriteCryptoCoinViewModel : ViewModel() {

    private val databaseReference by lazy { FirebaseDatabase.getInstance().reference.child("favorites") }

    private val firebaseAuth by lazy { FirebaseAuth.getInstance().currentUser }

    private val _favoriteList = MutableLiveData<List<CryptoCoin>>()
    val favoriteList: LiveData<List<CryptoCoin>>
        get() = _favoriteList

    init {
        getFavoriteList()
    }


    private fun getFavoriteList() {
        val dataObject = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val cryptoCoinList = mutableListOf<CryptoCoin>()
                snapshot.children.forEach {
                    if (it.child("userId").toString().contains(firebaseAuth?.uid ?: "")){
                        cryptoCoinList.add(
                            CryptoCoin(
                                it.child("id").value.toString(),
                                it.child("symbol").value.toString(),
                                it.child("name").value.toString()
                            )
                        )
                    }


                }
                _favoriteList.postValue(cryptoCoinList)
            }

        }
        databaseReference.addValueEventListener(dataObject)
        databaseReference.addListenerForSingleValueEvent(dataObject)

    }


}