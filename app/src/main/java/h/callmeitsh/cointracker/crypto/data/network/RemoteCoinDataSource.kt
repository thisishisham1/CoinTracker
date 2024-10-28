package h.callmeitsh.cointracker.crypto.data.network

import h.callmeitsh.cointracker.core.data.network.constructUrl
import h.callmeitsh.cointracker.core.data.network.safeCall
import h.callmeitsh.cointracker.core.util.NetworkError
import h.callmeitsh.cointracker.core.util.Result
import h.callmeitsh.cointracker.core.util.map
import h.callmeitsh.cointracker.crypto.data.dto.CoinsResponseDto
import h.callmeitsh.cointracker.crypto.data.mapper.toCoin
import h.callmeitsh.cointracker.crypto.domain.Coin
import h.callmeitsh.cointracker.crypto.domain.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource(private val httpClient: HttpClient) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("assets")
            )
        }.map { response ->
            response.data.map {
                it.toCoin()
            }
        }
    }

}