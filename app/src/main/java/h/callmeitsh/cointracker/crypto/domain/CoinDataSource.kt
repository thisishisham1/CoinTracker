package h.callmeitsh.cointracker.crypto.domain

import h.callmeitsh.cointracker.core.util.NetworkError
import h.callmeitsh.cointracker.core.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}