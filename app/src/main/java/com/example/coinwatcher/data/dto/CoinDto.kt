package com.example.coinwatcher.data.dto

import com.example.coinwatcher.data.local.CoinEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CoinDto(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "symbol") val symbol: String,
    @Json(name = "quotes") val quotes: QuotesDto?
)

@JsonClass(generateAdapter = true)
data class QuotesDto(
    @Json(name = "USD") val usd: UsdDto?
)

@JsonClass(generateAdapter = true)
data class UsdDto(
    @Json(name = "price") val price: Double?,
    @Json(name = "percent_change_24h") val percentChange24h: Double?
)


fun CoinDto.toEntity(): CoinEntity {
    return CoinEntity(
        id = id,
        symbol = symbol,
        name = name,
        price = quotes?.usd?.price ?: 0.0,
        changePercent = quotes?.usd?.percentChange24h ?: 0.0
    )
}