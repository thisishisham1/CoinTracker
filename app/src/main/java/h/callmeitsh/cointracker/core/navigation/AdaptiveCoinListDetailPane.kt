package h.callmeitsh.cointracker.core.navigation

import android.widget.Toast
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import h.callmeitsh.cointracker.core.presentation.util.ObserveAsEvents
import h.callmeitsh.cointracker.core.presentation.util.toString
import h.callmeitsh.cointracker.crypto.presentation.coin_details.CoinDetailsView
import h.callmeitsh.cointracker.crypto.presentation.coin_list.CoinListAction
import h.callmeitsh.cointracker.crypto.presentation.coin_list.CoinListEvent
import h.callmeitsh.cointracker.crypto.presentation.coin_list.CoinListView
import h.callmeitsh.cointracker.crypto.presentation.coin_list.CoinListViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AdaptiveCoinListDetailPane(
    modifier: Modifier = Modifier,
    viewModel: CoinListViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    ObserveAsEvents(events = viewModel.event) { event ->
        when (event) {
            is CoinListEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error.toString(context),
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
    }
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = {
            CoinListView(
                state = state.value,
                modifier = modifier,
                onAction = { action ->
                    viewModel.onAction(action)
                    when (action) {
                        is CoinListAction.onCoinClicked -> {
                            navigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Detail
                            )
                        }
                    }
                },
            )
        },
        detailPane = {
            CoinDetailsView(state = state.value, modifier = modifier)
        },
    )
}