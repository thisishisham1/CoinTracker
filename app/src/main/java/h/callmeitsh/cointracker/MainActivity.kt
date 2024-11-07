package h.callmeitsh.cointracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import h.callmeitsh.cointracker.core.presentation.util.ObserveAsEvents
import h.callmeitsh.cointracker.core.presentation.util.toString
import h.callmeitsh.cointracker.crypto.presentation.coin_list.CoinListEvent
import h.callmeitsh.cointracker.crypto.presentation.coin_list.CoinListView
import h.callmeitsh.cointracker.crypto.presentation.coin_list.CoinListViewModel
import h.callmeitsh.cointracker.crypto.presentation.theme.CoinTrackerTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoinTrackerTheme {
                Scaffold(Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = koinViewModel<CoinListViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
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
                    CoinListView(
                        state = state,
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(horizontal = 16.dp),
                    )
                }
            }
        }
    }
}
