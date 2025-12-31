package com.example.coinwatcher.repo

import android.util.Log
import com.example.coinwatcher.data.dto.toEntity
import com.example.coinwatcher.data.local.CoinDao
import com.example.coinwatcher.data.local.CoinEntity
import com.example.coinwatcher.network.CoinApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val apiService: CoinApiService,
    private val coinDao: CoinDao
) {

    val coins: Flow<List<CoinEntity>> = coinDao.getSavedCoins()

    suspend fun refreshCoins() {
        try {
            Log.e("COIN_TEST", "1. Requesting data from CoinPaprika...")


            val coinList = apiService.getCoins()

            Log.e("COIN_TEST", "2. API Success! Found ${coinList.size} coins.")


            val cleanCoins = coinList.take(50).map { dto ->
                dto.toEntity()
            }

            coinDao.saveCoins(cleanCoins)
            Log.e("COIN_TEST", "3. Saved to Database.")

        } catch (e: Exception) {
            Log.e("COIN_TEST", "CRITICAL ERROR: ${e.message}")
            e.printStackTrace()
        }
    }


}