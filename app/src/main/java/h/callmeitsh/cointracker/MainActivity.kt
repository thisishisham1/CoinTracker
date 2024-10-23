package h.callmeitsh.cointracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import h.callmeitsh.cointracker.crypto.presentation.coin_list.CoinListState
import h.callmeitsh.cointracker.crypto.presentation.coin_list.CoinListView
import h.callmeitsh.cointracker.crypto.presentation.coin_list.components.coinPreview
import h.callmeitsh.cointracker.ui.theme.CoinTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoinTrackerTheme {
                Surface(modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()) {
                    CoinListView(state = CoinListState(
                        coins = (0..100).map {
                            coinPreview.copy(id = it.toString())
                        }
                    ),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}
