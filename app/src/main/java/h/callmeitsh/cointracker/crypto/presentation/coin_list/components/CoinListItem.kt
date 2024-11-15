package h.callmeitsh.cointracker.crypto.presentation.coin_list.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import h.callmeitsh.cointracker.crypto.domain.Coin
import h.callmeitsh.cointracker.crypto.presentation.models.CoinUi
import h.callmeitsh.cointracker.crypto.presentation.models.toCoinUi
import h.callmeitsh.cointracker.crypto.presentation.theme.CoinTrackerTheme

@Composable
fun CoinListItem(
    modifier: Modifier = Modifier,
    coinUi: CoinUi,
    onClick: () -> Unit,
) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isVisible = true
    }

    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "alpha"
    )
    val translationX by animateFloatAsState(
        targetValue = if (isVisible) 0f else -100f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "translationX"
    )

    Row(
        modifier = modifier
            .shadow(
                4.dp,
                RoundedCornerShape(16.dp),
                clip = false,
                spotColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            )
            .background(
                MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
            .padding(16.dp)
            .graphicsLayer {
                this.alpha = alpha
                this.translationX = translationX
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = coinUi.iconRes),
            contentDescription = coinUi.name,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(80.dp)
                .background(
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(8.dp)
        )
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = coinUi.symbol,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = coinUi.name,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Light
                )
            )
        }
        Column(
            horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "$ ${coinUi.price.suffix}",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleSmall
            )
            PriceChange(
                change = coinUi.percentChange24h, modifier = Modifier.align(Alignment.End)
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinListItemPreview() {
    CoinTrackerTheme {
        CoinListItem(
            onClick = {}, coinUi = coinPreview, modifier = Modifier.background(
                MaterialTheme.colorScheme.surface
            )
        )
    }
}

internal val coinPreview = Coin(
    id = "BTC",
    name = "Bitcoin",
    rank = 1,
    symbol = "BTC",
    priceUsd = 50000.0,
    changePercent24Hr = -0.6,
    marketCapUsd = 12485965258.75
).toCoinUi()