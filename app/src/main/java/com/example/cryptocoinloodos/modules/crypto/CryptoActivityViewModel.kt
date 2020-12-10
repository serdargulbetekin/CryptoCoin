package com.example.cryptocoinloodos.modules.crypto

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptocoinloodos.repo.CryptoCoin
import com.example.cryptocoinloodos.repo.CryptoCoinRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CryptoActivityViewModel @Inject constructor(private val cryptoCoinRepo: CryptoCoinRepo) :
    ViewModel() {

    private val _conList = MutableLiveData<List<CryptoCoin>>()
    val coinList: LiveData<List<CryptoCoin>>
        get() = _conList

    init {
        request()
    }


    @SuppressLint("CheckResult")
    fun request() {
        cryptoCoinRepo.requestCoinList()
            .subscribeOn(Schedulers.io())
            .observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe({
                _conList.postValue(it)
            }, {
                _conList.postValue(null)
            })
    }

    fun searchWithNameOrSymbol(searchText: String) {
        if (searchText.isNotEmpty()) {
            coinList.value?.let {
                (coinList as? MutableLiveData)?.postValue(
                    it.filter {
                        it.name.contains(searchText) || it.symbol.contains(searchText)
                    })
            }
        } else {
            (coinList as? MutableLiveData)?.postValue(_conList.value)
        }
    }


}