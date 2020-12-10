package com.example.cryptocoinloodos.modules.cryptodetail

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptocoinloodos.repo.CryptoCoinDetailInfo
import com.example.cryptocoinloodos.repo.CryptoCoinRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CryptoDetailViewModel @Inject constructor(
    private val id: String,
    private val repo: CryptoCoinRepo
) : ViewModel() {

    private val _coinDetail = MutableLiveData<CryptoCoinDetailInfo>()
    val coinDetail: LiveData<CryptoCoinDetailInfo>
        get() = _coinDetail



    private var timeInterval: Long = 1L

    private val databaseReference by lazy { FirebaseDatabase.getInstance().reference }

    private val currentUser by lazy { FirebaseAuth.getInstance().currentUser }


    init {
        requestDetail()
    }


    fun saveCryptoCoin() {
        currentUser?.let {
            databaseReference.child("favorites").child(it.uid + coinDetail.value?.id).setValue(
                CryptoCoinWithUser(
                    it.uid,
                    coinDetail.value?.id ?: "",
                    coinDetail.value?.symbol ?: "",
                    coinDetail.value?.name ?: ""
                )
            )

        }
    }


    fun timeInterval(timeInterval: String) {
        if (timeInterval.isNotEmpty()) {
            this.timeInterval = timeInterval.toLong()
        } else {
            this.timeInterval = 1L
        }
    }

    @SuppressLint("CheckResult")
    fun requestDetail() {
        Observable.interval(timeInterval, TimeUnit.SECONDS).timeInterval().flatMap {
            repo.requestCoinDetail(id)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _coinDetail.postValue(it)

            }, {
                _coinDetail.postValue(null)
            })

    }


}

data class CryptoCoinWithUser(
    val userId: String,
    val id: String,
    val symbol: String,
    val name: String

)