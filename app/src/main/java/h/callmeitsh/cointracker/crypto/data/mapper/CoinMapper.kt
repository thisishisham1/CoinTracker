package h.callmeitsh.cointracker.crypto.data.mapper

import h.callmeitsh.cointracker.crypto.data.dto.CoinDto
import h.callmeitsh.cointracker.crypto.domain.Coin

fun CoinDto.toCoin() = Coin(
    id = id,
    name = name,
    symbol = symbol,
    priceUsd = priceUsd,
    marketCapUsd = marketCapUsd,
    changePercent24Hr = changePercent24Hr,
    rank = rank
)