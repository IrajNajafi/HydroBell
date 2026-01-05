package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.utils

import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.DailyDrink
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.model.ChartBarData
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.Locale

// ----------------------------- Helper -----------------------------
private fun sumDrinks(drinksByDate: Map<LocalDate, DailyDrink>, start: LocalDate, end: LocalDate): Int {
    var sum = 0
    var day = start
    while (!day.isAfter(end)) {
        sum += drinksByDate[day]?.totalDrink ?: 0
        day = day.plusDays(1)
    }
    return sum
}

// ----------------------------- Week -----------------------------
fun generateWeekData(
    drinksByDate: Map<LocalDate, DailyDrink>,
    anchorDate: LocalDate,
    dailyTarget: Int,
    startOfWeek: DayOfWeek = DayOfWeek.SATURDAY,
    locale: Locale = Locale.ENGLISH
): List<ChartBarData> {

    val weekStart = anchorDate.with(TemporalAdjusters.previousOrSame(startOfWeek))
    val formatter = DateTimeFormatter.ofPattern("EEE", locale)

    return (0..6).map { i ->
        val day = weekStart.plusDays(i.toLong())
        val drink = drinksByDate[day]   // ⭐ کلید ماجرا

        ChartBarData(
            date = day,
            label = day.format(formatter),
            value = drink?.totalDrink ?: 0,
            target = dailyTarget,
            isCompleted = drink?.isCompleted == true
        )
    }
}

// ----------------------------- Month -----------------------------
fun generateMonthData(
    drinksByDate: Map<LocalDate, DailyDrink>,
    anchorDate: LocalDate,
    dailyTarget: Int,
    startOfWeek: DayOfWeek = DayOfWeek.SATURDAY,
    locale: Locale = Locale.ENGLISH
): List<ChartBarData> {

    val firstDay = anchorDate.withDayOfMonth(1)
    val lastDay = anchorDate.withDayOfMonth(anchorDate.lengthOfMonth())
    val result = mutableListOf<ChartBarData>()

    var weekStart = firstDay
    var weekIndex = 1

    while (weekStart <= lastDay) {
        val weekEnd = minOf(weekStart.plusDays(6), lastDay)

        val sum = sumDrinks(drinksByDate, weekStart, weekEnd)
        val daysCount =
            (weekEnd.toEpochDay() - weekStart.toEpochDay() + 1).toInt()

        val target = dailyTarget * daysCount

        val label =
            if (locale.language == "fa") "هفته $weekIndex"
            else "Week $weekIndex"

        result.add(
            ChartBarData(
                date = weekStart,
                label = label,
                value = sum,
                target = target,
                isCompleted = sum >= target   // ⭐ منطق درست
            )
        )

        weekIndex++
        weekStart = weekEnd.plusDays(1)
    }

    return result
}

// ----------------------------- Year -----------------------------
fun generateYearData(
    drinksByDate: Map<LocalDate, DailyDrink>,
    anchorDate: LocalDate,
    dailyTarget: Int,
    locale: Locale = Locale.ENGLISH
): List<ChartBarData> {

    val year = anchorDate.year
    val monthFormatter = DateTimeFormatter.ofPattern("MMM", locale)

    return (1..12).map { month ->
        val firstDay = LocalDate.of(year, month, 1)
        val lastDay = firstDay.withDayOfMonth(firstDay.lengthOfMonth())

        val sum = sumDrinks(drinksByDate, firstDay, lastDay)
        val target = dailyTarget * firstDay.lengthOfMonth()

        ChartBarData(
            date = firstDay,
            label = firstDay.format(monthFormatter),
            value = sum,
            target = target,
            isCompleted = sum >= target   // ⭐ منطق درست
        )
    }
}
