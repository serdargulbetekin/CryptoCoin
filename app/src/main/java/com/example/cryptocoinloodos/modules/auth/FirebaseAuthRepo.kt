package com.example.cryptocoinloodos.modules.auth

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class FirebaseAuthRepo @Inject constructor(private val context: Context) {

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
                    Toast.makeText(
                        context,
                        "Error in registration with: " + it.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    userMutableLiveData.postValue(firebaseAuth.currentUser)
                } else {
                    Toast.makeText(
                        context,
                        "Error in login with: " + it.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}