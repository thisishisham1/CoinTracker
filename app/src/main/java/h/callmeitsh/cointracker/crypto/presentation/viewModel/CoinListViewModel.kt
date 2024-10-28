package h.callmeitsh.cointracker.crypto.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import h.callmeitsh.cointracker.core.util.onError
import h.callmeitsh.cointracker.core.util.onSuccess
import h.callmeitsh.cointracker.crypto.domain.CoinDataSource
import h.callmeitsh.cointracker.crypto.presentation.coin_list.CoinListAction
import h.callmeitsh.cointracker.crypto.presentation.coin_list.CoinListState
import h.callmeitsh.cointracker.crypto.presentation.models.toCoinUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val coinDataSource: CoinDataSource
) : ViewModel() {
    private val _state = MutableStateFlow(CoinListState())
    val state = _state.onStart {
        loadCoins()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        CoinListState()
    )

    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.onCoinClicked -> {
                // TODO: Handle coin click
            }
        }
    }

    private fun loadCoins() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            coinDataSource
                .getCoins()
                .onSuccess { coins ->
                    _state.update { coin ->
                        coin.copy(isLoading = false, coins = coins.map {
                            it.toCoinUi()
                        })
                    }

                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                }
        }
    }
}