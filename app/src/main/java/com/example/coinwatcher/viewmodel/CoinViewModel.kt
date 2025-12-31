package com.example.coinwatcher.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinwatcher.data.local.CoinEntity
import com.example.coinwatcher.repo.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinViewModel@Inject constructor(
    private val repository: CoinRepository
) : ViewModel() {
    val coins: StateFlow<List<CoinEntity>> = repository.coins.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        refreshCoins()
    }
    fun refreshCoins() {
        viewModelScope.launch {
            repository.refreshCoins()
        }
    }
}