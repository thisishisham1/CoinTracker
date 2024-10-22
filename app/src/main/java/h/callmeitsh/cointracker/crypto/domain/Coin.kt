package h.callmeitsh.cointracker.crypto.domain

data class Coin(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val price: Double,
    val percentChange24h: Double,
    val marketCap: Double,
)
