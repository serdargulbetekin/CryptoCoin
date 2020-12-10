package com.example.cryptocoinloodos.repo

import com.example.cryptocoinloodos.api.RestApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject

class CryptoCoinRepo(
    private val restApi: RestApi
) {

    fun requestCoinList(): Observable<List<CryptoCoin>> {
        return restApi.getCryptoCoin()
            .map {
                parseCryptoCoinJSON(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun parseCryptoCoinJSON(responseBody: ResponseBody): List<CryptoCoin> {
        val coinList = mutableListOf<CryptoCoin>()
        val jsonArray = JSONArray(responseBody.string())

        for (x in 0 until jsonArray.length()) {
            val jsonObjectData = jsonArray.getJSONObject(x)
            val id = jsonObjectData.optString("id")
            val symbol = jsonObjectData.optString("symbol")
            val name = jsonObjectData.optString("name")
            coinList.add(
                CryptoCoin(
                    id = id,
                    symbol = symbol,
                    name = name
                )
            )
        }
        return coinList.toList()
    }

    fun requestCoinDetail(id: String): Observable<CryptoCoinDetailInfo> {
        return restApi.getCryptoCoinDetail(id).map {
            parseCryptoCoinDetailJSON(it)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun parseCryptoCoinDetailJSON(responseBody: ResponseBody): CryptoCoinDetailInfo {
        val jsonObject = JSONObject(responseBody.string())

        return CryptoCoinDetailInfo(
            id = jsonObject.optString("id") ?: "",
            symbol = jsonObject.optString("symbol") ?: "",
            name = jsonObject.optString("name") ?: "",
            hashingAlgorithm = jsonObject.optString("hashing_algorithm") ?: "",
            priceChange = jsonObject.optJSONObject("market_data")?.optString("price_change_24h")?:"",
            priceChangePercentage = jsonObject.optJSONObject("market_data")?.optString("price_change_percentage_24h")?:"",
            cryptoCoinImage = CryptoCoinImage(
                thumb = jsonObject.optJSONObject("image")?.optString("thumb") ?: "",
                small = jsonObject.optJSONObject("image")?.optString("small") ?: "",
                large = jsonObject.optJSONObject("image")?.optString("large") ?: ""
            )
        )
    }
}

data class CryptoCoin(
    val id: String,
    val symbol: String,
    val name: String
)

data class CryptoCoinDetailInfo(
    val id: String,
    val symbol: String,
    val name: String,
    val hashingAlgorithm: String,
    val priceChange: String,
    val priceChangePercentage: String,
    val cryptoCoinImage: CryptoCoinImage
)

data class CryptoCoinImage(
    val thumb: String,
    val small: String,
    val large: String
)

