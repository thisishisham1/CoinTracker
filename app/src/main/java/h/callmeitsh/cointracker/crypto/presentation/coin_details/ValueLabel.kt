package h.callmeitsh.cointracker.crypto.presentation.coin_details

import java.text.NumberFormat
import java.util.Locale

data class ValueLabel(
    val value: Float,
    val unit: String
) {
    fun formatted(): String {
        val formattedValue = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
            val fractionDigits = when {
                value < 1000 -> 0
                value in 2f..999f -> 2
                else -> 0
            }
            maximumFractionDigits = fractionDigits
            minimumFractionDigits = 0
        }
        return "${formattedValue.format(value)} $unit"
    }
}
