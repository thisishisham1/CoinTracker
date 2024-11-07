package h.callmeitsh.cointracker

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import h.callmeitsh.cointracker.core.presentation.util.ObserveAsEvents
import h.callmeitsh.cointracker.core.presentation.util.toString
import h.callmeitsh.cointracker.crypto.presentation.coin_details.CoinDetailsView
import h.callmeitsh.cointracker.crypto.presentation.coin_list.CoinListAction
import h.callmeitsh.cointracker.crypto.presentation.coin_list.CoinListEvent
import h.callmeitsh.cointracker.crypto.presentation.coin_list.CoinListView
import h.callmeitsh.cointracker.crypto.presentation.coin_list.CoinListViewModel
import h.callmeitsh.cointracker.crypto.presentation.theme.CoinTrackerTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoinTrackerTheme {
                Scaffold(Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Navigation()
                    }
                }
            }
        }
    }
}


@Composable
fun Navigation() {
    val navController = rememberNavController()
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
    NavHost(navController = navController, startDestination = "coin_list") {
        composable("coin_list") {
            CoinListView(
                state = state,
                onAction = { action ->
                    viewModel.onAction(action)
                    if (action is CoinListAction.onCoinClicked) {
                        navController.navigate("coin_details")
                    }
                }
            )
        }
        composable("coin_details") {
            CoinDetailsView(
                state = state
            )
        }
    }
}