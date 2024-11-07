package h.callmeitsh.cointracker.crypto.presentation.coin_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import h.callmeitsh.cointracker.crypto.presentation.coin_list.CoinListState
import h.callmeitsh.cointracker.crypto.presentation.coin_list.components.coinPreview
import h.callmeitsh.cointracker.crypto.presentation.theme.CoinTrackerTheme

@Composable
fun CoinDetailsView(modifier: Modifier = Modifier, state: CoinListState) {
    if (state.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (state.selectedCoin != null) {
        val coin = state.selectedCoin
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = coin.iconRes),
                contentDescription = coin.name,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(8.dp)
            )


        }
    }
}

@PreviewLightDark
@Composable
private fun CoinDetailsViewPreview() {
    CoinTrackerTheme {
        CoinDetailsView(
            state = CoinListState(
                selectedCoin = coinPreview
            ),
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}