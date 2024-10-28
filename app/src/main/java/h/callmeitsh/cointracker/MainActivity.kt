package h.callmeitsh.cointracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import h.callmeitsh.cointracker.crypto.presentation.coin_list.CoinListView
import h.callmeitsh.cointracker.crypto.presentation.viewModel.CoinListViewModel
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
