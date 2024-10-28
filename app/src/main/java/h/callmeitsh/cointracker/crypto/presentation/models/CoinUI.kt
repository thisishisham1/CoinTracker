package h.callmeitsh.cointracker.crypto.presentation.models

import androidx.annotation.DrawableRes
import h.callmeitsh.cointracker.crypto.domain.Coin
import h.callmeitsh.cointracker.core.presentation.util.getDrawableIdForCoin
import java.text.NumberFormat
import java.util.Locale

data class CoinUi(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val price: DisplayableNumber,
    val percentChange24h: DisplayableNumber,
    val marketCap: DisplayableNumber,
    @DrawableRes val iconRes: Int
)

data class DisplayableNumber(
    val value: Double,
    val suffix: String
)

fun Coin.toCoinUi() = CoinUi(
    id = id,
    rank = rank,
    name = name,
    symbol = symbol,
    price = priceUsd.toDisplayableNumber(),
    percentChange24h = changePercent24Hr.toDisplayableNumber(),
    marketCap = marketCapUsd.toDisplayableNumber(),
    iconRes = getDrawableIdForCoin(symbol)
)

private fun Double.toDisplayableNumber(): DisplayableNumber {
    val suffix = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return DisplayableNumber(this, suffix.format(this))
}