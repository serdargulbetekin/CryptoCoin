package com.example.cryptocoinloodos.modules.crypto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocoinloodos.application.AppConfig
import com.example.cryptocoinloodos.repo.CryptoCoinRepo

class CryptoActivityViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CryptoActivityViewModel::class.java)) {
            return CryptoActivityViewModel(CryptoCoinRepo(restApi = AppConfig.appComponent.getApiInterface())) as T

        }
        throw IllegalArgumentException("Unknown View Model Class found!!!")
    }

}