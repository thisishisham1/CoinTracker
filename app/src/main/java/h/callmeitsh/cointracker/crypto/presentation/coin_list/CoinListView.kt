package h.callmeitsh.cointracker.crypto.presentation.coin_list

import android.widget.Toast
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import h.callmeitsh.cointracker.core.presentation.util.toString
import h.callmeitsh.cointracker.crypto.presentation.coin_list.components.CoinListItem
import h.callmeitsh.cointracker.crypto.presentation.coin_list.components.coinPreview
import h.callmeitsh.cointracker.crypto.presentation.theme.CoinTrackerTheme
import h.callmeitsh.cointracker.crypto.presentation.viewModel.CoinListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.withContext

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
            items(state.coins,
                key = { it.id }
            ) { coinUi ->
                Modifier
                    .fillMaxWidth()
                CoinListItem(
                    coinUi = coinUi,
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .animateItem(
                            fadeInSpec = null, fadeOutSpec = null, placementSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessMedium
                            )
                        )
                        .scale(
                            animateFloatAsState(
                                targetValue = 1f,
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = FastOutSlowInEasing
                                ),
                                label = "scale"
                            ).value
                        )

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