package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.utils

import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.model.CalendarType
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.Locale




fun formatWeekTitle(
    date: LocalDate,
    startOfWeek: DayOfWeek = DayOfWeek.SATURDAY,
    locale: Locale = Locale.ENGLISH
): String {
    val start = date.with(TemporalAdjusters.previousOrSame(startOfWeek))
    val end = start.plusDays(6)
    val formatter = DateTimeFormatter.ofPattern("MMM d", locale)
    return "${start.format(formatter)} – ${end.format(formatter)}"
}

fun formatMonthTitle(
    date: LocalDate,
    locale: Locale = Locale.ENGLISH
): String {
    val firstDay = date.withDayOfMonth(1)
    val lastDay = date.withDayOfMonth(date.lengthOfMonth())
    val formatter = DateTimeFormatter.ofPattern("MMM d", locale)
    return "${firstDay.format(formatter)} – ${lastDay.format(formatter)}"
}

fun formatYearTitle(
    date: LocalDate,
    locale: Locale = Locale.ENGLISH
): String {
    val year = date.year
    val formatter = DateTimeFormatter.ofPattern("MMM yyyy", locale) // اضافه شدن سال
    val firstDayOfYear = LocalDate.of(year, 1, 1)
    val lastDayOfYear = LocalDate.of(year, 12, 31)

    return "${firstDayOfYear.format(formatter)} – ${lastDayOfYear.format(formatter)}"
}