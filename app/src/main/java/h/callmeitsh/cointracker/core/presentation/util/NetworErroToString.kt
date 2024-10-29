package h.callmeitsh.cointracker.core.presentation.util

import android.content.Context
import h.callmeitsh.cointracker.core.util.NetworkError
import h.callmeitsh.coinvault.R

fun NetworkError.toString(context: Context): String {
    val resId = when (this) {
        NetworkError.UNKNOWN -> R.string.error_unknown
        NetworkError.SERIALIZATION_ERROR -> R.string.error_serialization
        NetworkError.NO_INTERNET_CONNECTION -> R.string.error_no_internet
        NetworkError.TIMEOUT -> R.string.error_request_timeout
        NetworkError.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
        NetworkError.SERVER_ERROR -> R.string.error_server_error
        NetworkError.NOT_FOUND -> R.string.error_not_found
    }
    return context.getString(resId)
}