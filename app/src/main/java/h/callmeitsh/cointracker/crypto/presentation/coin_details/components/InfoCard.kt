package h.callmeitsh.cointracker.crypto.presentation.coin_details.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import h.callmeitsh.cointracker.crypto.presentation.theme.CoinTrackerTheme
import h.callmeitsh.coinvault.R

@Composable
fun InfoCard(
    title: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.onSurface
) {
    val defaultTextStyle = LocalTextStyle.current.copy(
        textAlign = TextAlign.Center, fontSize = 18.sp, color = contentColor, letterSpacing = 2.sp
    )
    Card(
        modifier = modifier
            .padding(8.dp)
            .shadow(
                elevation = 15.dp,
                shape = MaterialTheme.shapes.medium,
                ambientColor = MaterialTheme.colorScheme.primary,
                spotColor = MaterialTheme.colorScheme.primary
            ), shape = MaterialTheme.shapes.medium, border = BorderStroke(
            width = 1.dp, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
        ), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer, contentColor = contentColor
        )
    ) {
        AnimatedContent(
            targetState = icon,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            label = "IconAnimation"
        ) { icon ->
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = contentColor,
                modifier = Modifier
                    .size(70.dp)
                    .padding(top = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        AnimatedContent(
            targetState = value,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            label = "valueAnimation"
        ) { formatedText ->
            Text(
                text = formatedText,
                style = defaultTextStyle,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        AnimatedContent(
            targetState = title,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            label = "valueAnimation"
        ) { title ->
            Text(
                text = title,
                style = defaultTextStyle.copy(
                    fontSize = 14.sp,
                    color = contentColor.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Light
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 8.dp)
            )
        }
    }

}

@PreviewLightDark
@Composable
private fun InfoCardPreview() {
    CoinTrackerTheme {
        InfoCard(
            title = "price",
            value = "$1,000",
            icon = ImageVector.vectorResource(id = R.drawable.dollar)
        )
    }
}