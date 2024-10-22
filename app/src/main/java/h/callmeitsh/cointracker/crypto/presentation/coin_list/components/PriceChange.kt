package h.callmeitsh.cointracker.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import h.callmeitsh.cointracker.crypto.presentation.models.DisplayableNumber
import h.callmeitsh.cointracker.ui.theme.CoinTrackerTheme

@Composable
fun PriceChange(
    change: DisplayableNumber,
    modifier: Modifier = Modifier
) {
    val contentColor = if (change.value < 0.0) {
        MaterialTheme.colorScheme.onError
    } else {
        MaterialTheme.colorScheme.onSecondary
    }

    val containerColor = if (change.value < 0.0) {
        MaterialTheme.colorScheme.error
    } else {
        Color.Green
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100f))
            .background(containerColor)
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (change.value < 0.0) {
                Icons.Default.ArrowDropDown
            } else {
                Icons.Default.ArrowDropUp
            },
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = contentColor
        )
        Text(
            text = "${change.suffix} %",
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Medium
            ),
            color = contentColor
        )
    }
}

@PreviewLightDark
@Composable
private fun PriceChangePreview() {
    CoinTrackerTheme {
        PriceChange(
            DisplayableNumber(2.43, "2.34")
        )
    }
}