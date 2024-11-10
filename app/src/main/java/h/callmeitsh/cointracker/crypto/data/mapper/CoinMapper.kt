package h.callmeitsh.cointracker.crypto.data.mapper

import h.callmeitsh.cointracker.crypto.data.dto.CoinDto
import h.callmeitsh.cointracker.crypto.data.dto.CoinPriceDto
import h.callmeitsh.cointracker.crypto.domain.Coin
import h.callmeitsh.cointracker.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun CoinDto.toCoin() = Coin(
    id = id,
    name = name,
    symbol = symbol,
    priceUsd = priceUsd,
    marketCapUsd = marketCapUsd,
    changePercent24Hr = changePercent24Hr,
    rank = rank
)

fun CoinPriceDto.toCoinPrice() = CoinPrice(
    priceUsd = priceUsd,
    dateTime = Instant.ofEpochMilli(time).atZone(ZoneId.of("UTC"))
)