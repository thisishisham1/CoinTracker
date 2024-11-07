package h.callmeitsh.cointracker.crypto.data.network

import h.callmeitsh.cointracker.core.data.network.constructUrl
import h.callmeitsh.cointracker.core.data.network.safeCall
import h.callmeitsh.cointracker.core.util.NetworkError
import h.callmeitsh.cointracker.core.util.Result
import h.callmeitsh.cointracker.core.util.map
import h.callmeitsh.cointracker.crypto.data.dto.CoinHistoryDto
import h.callmeitsh.cointracker.crypto.data.dto.CoinsResponseDto
import h.callmeitsh.cointracker.crypto.data.mapper.toCoin
import h.callmeitsh.cointracker.crypto.data.mapper.toCoinPrice
import h.callmeitsh.cointracker.crypto.domain.Coin
import h.callmeitsh.cointracker.crypto.domain.CoinDataSource
import h.callmeitsh.cointracker.crypto.domain.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

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

    override suspend fun getHistory(
        coinId: String, start: ZonedDateTime, end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start.withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant().toEpochMilli()

        val endMillis = end.withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant().toEpochMilli()
        return safeCall<CoinHistoryDto> {
            httpClient.get(
                urlString = constructUrl("assets/$coinId/history"),
            ) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map {
                it.toCoinPrice()
            }
        }
    }

}