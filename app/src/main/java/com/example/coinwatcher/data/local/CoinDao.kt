package com.example.coinwatcher.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface CoinDao {

    @Query("SELECT * FROM coins")
    fun getSavedCoins(): Flow<List<CoinEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCoins(coins: List<CoinEntity>)


}