package com.example.cryptocoinloodos.modules.auth.login

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cryptocoinloodos.application.AppConfig
import com.example.cryptocoinloodos.modules.auth.FirebaseAuthRepo
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class LoginRegisterActivityViewModel :
    ViewModel() {

    private val firebaseAuthRepo by lazy { FirebaseAuthRepo(AppConfig.appComponent.getContext()) }

    val firebaseUser: LiveData<FirebaseUser> by lazy { firebaseAuthRepo.userLiveData }

    fun register(email: CharSequence?, password: CharSequence?) {
        val isValidPair = isValidInput(email, password)
        if (!isValidPair.first) {
            Toast.makeText(AppConfig.appComponent.getContext(), isValidPair.second, Toast.LENGTH_SHORT).show()
        } else {
            firebaseAuthRepo.register(email.toString(), password.toString())
        }
    }

    fun login(email: CharSequence?, password: CharSequence?) {
        val isValidPair = isValidInput(email, password)
        if (!isValidPair.first) {
            Toast.makeText(AppConfig.appComponent.getContext(), isValidPair.second, Toast.LENGTH_SHORT).show()
        } else {
            firebaseAuthRepo.login(email.toString(), password.toString())
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidInput(email: CharSequence?, password: CharSequence?): Pair<Boolean, String> {
        return when {
            email.isNullOrEmpty() -> {
                false to "Email should not be empty."
            }
            password.isNullOrEmpty() -> {
                false to "Password should not be empty."
            }
            !isEmailValid(email.toString()) -> {
                false to "Email is not valid."
            }
            else -> {
                true to ""
            }
        }

    }

}