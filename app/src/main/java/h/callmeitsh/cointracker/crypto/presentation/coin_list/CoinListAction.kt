package h.callmeitsh.cointracker.crypto.presentation.coin_list

import h.callmeitsh.cointracker.crypto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class onCoinClicked(val coinUi: CoinUi) : CoinListAction
}