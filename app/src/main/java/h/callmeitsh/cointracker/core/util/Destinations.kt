package h.callmeitsh.cointracker.core.util

sealed class Destinations(val route:String) {
    data object CoinList : Destinations(route = "coin_list")
    data object CoinDetails : Destinations(route = "coin_details")
}