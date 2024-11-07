package h.callmeitsh.cointracker.crypto.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinPriceDto(
    val time: Long,
    val priceUsd: Double
)
