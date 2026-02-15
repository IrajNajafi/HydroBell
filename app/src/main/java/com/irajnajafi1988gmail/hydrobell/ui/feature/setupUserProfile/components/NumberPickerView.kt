package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.components

import android.annotation.SuppressLint
import android.widget.NumberPicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.drawable.toDrawable
import com.irajnajafi1988gmail.hydrobell.ui.theme.BluePrimary
import com.irajnajafi1988gmail.hydrobell.ui.theme.LocalIsDarkTheme

@Composable
fun NumberPickerView(
    minValue: Int,
    maxValue: Int,
    value: Int,
    onValueChange: (Int) -> Unit,
    label: String,
) {

    val isDarkTheme = LocalIsDarkTheme.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        AndroidView(
            modifier = Modifier
                .height(170.dp)
                .width(100.dp),
            factory = { context ->
                NumberPicker(context).apply {
                    this.minValue = minValue
                    this.maxValue = maxValue
                    this.value = value
                    descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS


                    this.setOnScrollListener { _, _ ->
                        this.updateNumberPickerStyle(isDarkTheme)
                    }


                    this.setOnValueChangedListener { _, _, newVal ->
                        onValueChange(newVal)
                        this.updateNumberPickerStyle(isDarkTheme)
                    }
                }
            },
            update = { picker ->
                picker.value = value
                picker.updateNumberPickerStyle(isDarkTheme)
            }
        )

        Text(
            text = label,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = BluePrimary
        )
    }
}

@SuppressLint("SoonBlockedPrivateApi")
fun NumberPicker.updateNumberPickerStyle(isDarkTheme: Boolean) {
    try {
        val selectedColor = BluePrimary.toArgb()
        val unSelectedColor = if (isDarkTheme) android.graphics.Color.WHITE else android.graphics.Color.BLACK

        val paintField = NumberPicker::class.java.getDeclaredField("mSelectorWheelPaint")
        paintField.isAccessible = true
        val paint = paintField.get(this) as android.graphics.Paint
        paint.color = unSelectedColor
        paint.textSize = if (isDarkTheme) 42f else 40f


        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child is android.widget.EditText) {
                if (child.text.toString() == value.toString()) {
                    child.setTextColor(selectedColor)
                    child.textSize = 26f
                    child.setTypeface(null, android.graphics.Typeface.BOLD)
                } else {
                    child.setTextColor(unSelectedColor)
                    child.textSize = 20f
                    child.setTypeface(null, android.graphics.Typeface.NORMAL)
                }
            }
        }

        // 🔹 Divider
        val dividerField = NumberPicker::class.java.getDeclaredField("mSelectionDivider")
        dividerField.isAccessible = true
        dividerField.set(
            this,
            if (isDarkTheme) android.graphics.Color.DKGRAY.toDrawable() else android.graphics.Color.LTGRAY.toDrawable()
        )

        invalidate()

    } catch (e: Exception) {
        e.printStackTrace()
    }
}
