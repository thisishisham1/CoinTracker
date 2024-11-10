package h.callmeitsh.cointracker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import h.callmeitsh.cointracker.core.navigation.AdaptiveCoinListDetailPane
import h.callmeitsh.cointracker.crypto.presentation.theme.CoinTrackerTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoinTrackerTheme {
                Scaffold(Modifier.fillMaxSize()) { innerPadding ->
                    AdaptiveCoinListDetailPane(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
