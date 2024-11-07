package h.callmeitsh.cointracker.crypto.domain

import h.callmeitsh.cointracker.core.util.NetworkError
import h.callmeitsh.cointracker.core.util.Result
import java.time.ZonedDateTime

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
    suspend fun getHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError>
}