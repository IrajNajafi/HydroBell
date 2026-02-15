package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.model

import com.irajnajafi1988gmail.hydrobell.R

enum class ItemLanguage (
    val code: String,
    val textRes: Int,
    val flag: String,
    val isRtl: Boolean = false
){
    ENGLISH(
        code = "en",
        textRes = R.string.english,
        flag = "\uD83C\uDDFA\uD83C\uDDF8" // 🇺🇸
    ),
    PERSIAN(
        code = "fa",
        textRes = R.string.persian,
        flag = "\uD83C\uDDEE\uD83C\uDDF7", // 🇮🇷
        isRtl = true
    );
    companion object {
        fun fromCode(code: String):ItemLanguage {
            return ItemLanguage.entries.find { it.code == code } ?:ItemLanguage.ENGLISH
        }
    }
}