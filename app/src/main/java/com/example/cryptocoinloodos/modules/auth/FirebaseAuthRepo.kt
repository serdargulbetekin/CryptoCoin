package com.example.cryptocoinloodos.modules.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseAuthRepo {

    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private val userMutableLiveData by lazy {
        MutableLiveData<FirebaseUser>()
    }

    val userLiveData: LiveData<FirebaseUser>
        get() = userMutableLiveData


    fun register(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    userMutableLiveData.postValue(firebaseAuth.currentUser)
                } else {
                    userMutableLiveData.postValue(null)

                }
            }
    }

    fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    userMutableLiveData.postValue(firebaseAuth.currentUser)
                } else {
                    userMutableLiveData.postValue(null)
                }
            }
    }

}