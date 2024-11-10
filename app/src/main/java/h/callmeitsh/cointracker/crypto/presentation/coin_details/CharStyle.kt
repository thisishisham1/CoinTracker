package h.callmeitsh.cointracker.crypto.presentation.coin_details

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

data class CharStyle(
    val chartLineColor: Color,
    val unSelectedColor: Color,
    val selectedColor: Color,
    val helperLinesThicknessPx: Float,
    val axisLinesThicknessPx: Float,
    val labelFontSize: TextUnit,
    val minYLabelSpacingDp: Dp,
    val verticalPaddingDp: Dp,
    val horizontalPaddingDp: Dp,
    val xAxisLabelSpacingDp: Dp
)
