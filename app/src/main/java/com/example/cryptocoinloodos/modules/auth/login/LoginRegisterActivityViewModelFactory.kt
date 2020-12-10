package com.example.cryptocoinloodos.modules.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocoinloodos.application.AppConfig

class LoginRegisterActivityViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginRegisterActivityViewModel::class.java)) {
            return LoginRegisterActivityViewModel() as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }

}