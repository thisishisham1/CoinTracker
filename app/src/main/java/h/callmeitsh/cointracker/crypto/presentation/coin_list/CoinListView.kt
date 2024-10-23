package h.callmeitsh.cointracker.crypto.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import h.callmeitsh.cointracker.crypto.presentation.coin_list.components.CoinListItem
import h.callmeitsh.cointracker.crypto.presentation.coin_list.components.coinPreview
import h.callmeitsh.cointracker.ui.theme.CoinTrackerTheme

@Composable
fun CoinListView(modifier: Modifier = Modifier, state: CoinListState) {
    if (state.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(state.coins) { coinUi ->
                CoinListItem(
                    coinUi = coinUi,
                    onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinListViewPreview() {
    CoinTrackerTheme {
        CoinListView(state = CoinListState(
            coins = (0..100).map {
                coinPreview.copy(id = it.toString())
            }
        ),
            modifier = Modifier.background(MaterialTheme.colorScheme.background)

        )
    }
}