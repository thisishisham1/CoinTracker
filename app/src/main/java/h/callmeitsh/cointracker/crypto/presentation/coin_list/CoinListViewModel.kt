package h.callmeitsh.cointracker.crypto.presentation.coin_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import h.callmeitsh.cointracker.core.util.onError
import h.callmeitsh.cointracker.core.util.onSuccess
import h.callmeitsh.cointracker.crypto.domain.CoinDataSource
import h.callmeitsh.cointracker.crypto.presentation.coin_details.DataPoint
import h.callmeitsh.cointracker.crypto.presentation.models.CoinUi
import h.callmeitsh.cointracker.crypto.presentation.models.toCoinUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class CoinListViewModel(
    private val coinDataSource: CoinDataSource,
) : ViewModel() {
    private val _state = MutableStateFlow(CoinListState())
    val state = _state.onStart {
        loadCoins()
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000L), CoinListState()
    )

    private val _event = Channel<CoinListEvent>()
    val event = _event.receiveAsFlow()

    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.onCoinClicked -> {
                selectCoin(action.coinUi)
            }
        }
    }

    private fun selectCoin(coinUi: CoinUi) {
        _state.update {
            it.copy(selectedCoin = coinUi)
        }

        viewModelScope.launch {
            coinDataSource.getHistory(
                coinId = coinUi.id,
                start = ZonedDateTime.now().minusDays(5),
                end = ZonedDateTime.now()
            ).onSuccess { history ->
                val dataPoints = history.sortedBy {
                    it.dateTime
                }.map {
                    DataPoint(
                        x = it.dateTime.hour.toFloat(),
                        y = it.priceUsd.toFloat(),
                        xLabel = DateTimeFormatter.ofPattern("ha\nM/d").format(it.dateTime),
                    )
                }
                _state.update {
                    it.copy(
                        selectedCoin = it.selectedCoin?.copy(coinPricesHistory = dataPoints)
                    )
                }
            }.onError { error ->
                _event.send(CoinListEvent.Error(error))
            }
        }
    }

    private fun loadCoins() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            coinDataSource.getCoins().onSuccess { coins ->
                _state.update { coin ->
                    coin.copy(isLoading = false, coins = coins.map {
                        it.toCoinUi()
                    })
                }

            }.onError { error ->
                _state.update { it.copy(isLoading = false) }
                _event.send(CoinListEvent.Error(error))
            }
        }
    }
}