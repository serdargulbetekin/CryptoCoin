package com.example.cryptocoinloodos.modules.cryptodetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocoinloodos.application.AppConfig
import com.example.cryptocoinloodos.repo.CryptoCoinRepo

class CryptoDetailViewModelFactory(private val id: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CryptoDetailViewModel::class.java)) {
            return CryptoDetailViewModel(
                id = id,
                repo = CryptoCoinRepo(restApi = AppConfig.appComponent.getApiInterface())
            ) as T
        }
        throw IllegalArgumentException("Unknown view model class!!! ")
    }

}