package h.callmeitsh.cointracker.crypto.presentation.coin_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import h.callmeitsh.cointracker.crypto.presentation.coin_details.components.InfoCard
import h.callmeitsh.cointracker.crypto.presentation.coin_list.CoinListState
import h.callmeitsh.cointracker.crypto.presentation.coin_list.components.coinPreview
import h.callmeitsh.cointracker.crypto.presentation.theme.CoinTrackerTheme
import h.callmeitsh.coinvault.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailsView(
    modifier: Modifier = Modifier,
    state: CoinListState
) {
    if (state.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (state.selectedCoin != null) {
        val coin = state.selectedCoin
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = coin.iconRes),
                contentDescription = coin.name,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = coin.name, style = MaterialTheme.typography.displaySmall.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center,
                    letterSpacing = (3).sp
                ), modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = coin.symbol, style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center
                ), modifier = Modifier.padding(top = 4.dp)
            )
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                InfoCard(
                    title = stringResource(R.string.coin_details_market_cap),
                    value = "$ ${coin.marketCap.suffix}",
                    icon = ImageVector.vectorResource(id = R.drawable.stock),
                )
                InfoCard(
                    title = stringResource(R.string.coin_details_price),
                    value = "$ ${coin.price.suffix}",
                    icon = ImageVector.vectorResource(id = R.drawable.dollar),
                )
                val absChangeFormated = (coin.price.value * (coin.percentChange24h.value / 100))
                val isPositive = absChangeFormated > 0.0
                val containerColor = if (!isPositive) {
                    MaterialTheme.colorScheme.error
                } else {
                    Color(0xFF00C853)
                }
                InfoCard(
                    title = stringResource(R.string.change_24h),
                    value = "$ ${coin.percentChange24h.suffix}",
                    icon = if (isPositive) {
                        ImageVector.vectorResource(id = R.drawable.trending)
                    } else {
                        ImageVector.vectorResource(id = R.drawable.trending_down)
                    },
                    contentColor = containerColor
                )
            }
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
            ), modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}