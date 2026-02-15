package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.irajnajafi1988gmail.hydrobell.R

enum class SettingItem(
    @DrawableRes val iconRes: Int,
    @StringRes val titleRes: Int
) {
    DARKMODE(R.drawable.ic_darckmode, R.string.dark_mode),
    LANGUAGE(R.drawable.language, R.string.language),
    RESET(R.drawable.reset, R.string.reset),

    INFO(R.drawable.info, R.string.info);


    companion object {
        val items = entries
    }
}