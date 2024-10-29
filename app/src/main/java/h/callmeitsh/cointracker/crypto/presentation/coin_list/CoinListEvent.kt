package h.callmeitsh.cointracker.crypto.presentation.coin_list

import h.callmeitsh.cointracker.core.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError) : CoinListEvent
}