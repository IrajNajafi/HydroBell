package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.components

import android.widget.NumberPicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.drawable.toDrawable
import com.irajnajafi1988gmail.hydrobell.ui.theme.BluePrimary

@Composable
fun NumberPickerView(
    minValue: Int,
    maxValue: Int,
    value: Int,
    onValueChange: (Int) -> Unit,
    label: String,
) {
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

                    // آپدیت هنگام اسکرول
                    this.setOnScrollListener { _, _ ->
                        this.updateNumberPickerStyle()
                    }

                    // آپدیت هنگام انتخاب
                    this.setOnValueChangedListener { _, _, newVal ->
                        onValueChange(newVal)
                        this.updateNumberPickerStyle()
                    }
                }
            },

            update = { picker ->
                picker.value = value
                picker.updateNumberPickerStyle()
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

fun NumberPicker.updateNumberPickerStyle() {
    try {
        val count = this.childCount
        for (i in 0 until count) {
            val child = this.getChildAt(i)
            if (child is android.widget.EditText) {

                // اگر این مقدار، مقدار انتخاب‌شده فعلی باشد
                if (child.text.toString() == this.value.toString()) {
                    child.setTextColor(BluePrimary.toArgb())
                    child.textSize = 26f   // بزرگ‌تر
                    child.setTypeface(null, android.graphics.Typeface.BOLD)
                } else {
                    child.setTextColor(android.graphics.Color.GRAY)
                    child.textSize = 20f   // سایز معمولی
                    child.setTypeface(null, android.graphics.Typeface.NORMAL)
                }
            }
        }

        // Divider همیشه مشکی
        val fields = NumberPicker::class.java.declaredFields
        for (field in fields) {
            if (field.name == "mSelectionDivider") {
                field.isAccessible = true
                field.set(this, android.graphics.Color.BLACK.toDrawable())
                break
            }
        }

    } catch (e: Exception) {
        e.printStackTrace()
    }
}
