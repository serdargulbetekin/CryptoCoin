package com.example.cryptocoinloodos.modules.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FavoriteCryptoCoinViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteCryptoCoinViewModel::class.java)) {
            return FavoriteCryptoCoinViewModel() as T
        }

        throw IllegalArgumentException("Unknown view model class!!!")
    }

}