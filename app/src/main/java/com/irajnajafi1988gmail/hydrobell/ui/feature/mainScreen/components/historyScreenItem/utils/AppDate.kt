package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.utils

import android.annotation.SuppressLint
import android.content.Context
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.model.CalendarType
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDate @Inject constructor(
    @ApplicationContext private val context: Context
) {

    /** فرمت تاریخ بر اساس timestamp و نوع تقویم */
    fun getFormattedDate(timestamp: Long, calendarType: CalendarType): String {
        val calendar = Calendar.getInstance().apply { timeInMillis = timestamp }

        val resolvedCalendarType = if (calendarType == CalendarType.GREGORIAN && isPersianLocale()) {
            CalendarType.JALALI
        } else {
            calendarType
        }

        return when (resolvedCalendarType) {
            CalendarType.GREGORIAN -> formatGregorian(calendar)
            CalendarType.JALALI -> formatJalali(calendar)
            CalendarType.HIJRI -> formatHijri(calendar)
        }
    }

    /** تاریخ مناسب برای ذخیره در دیتابیس */
    fun getDateForSave(timestamp: Long, calendarType: CalendarType): String {
        val calendar = Calendar.getInstance().apply { timeInMillis = timestamp }

        return when (calendarType) {
            CalendarType.JALALI -> {
                val persianDate = toPersian(calendar)
                "${persianDate[4]}/${persianDate[2].padStart(2,'0')}/${persianDate[1].padStart(2,'0')}"
            }
            else -> {
                val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.US)
                dateFormat.format(calendar.time)
            }
        }
    }

    /** زمان فعلی */
    @SuppressLint("DefaultLocale")
    fun getCurrentTime(timestamp: Long = System.currentTimeMillis()): String {
        val calendar = Calendar.getInstance().apply { timeInMillis = timestamp }
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return String.format("%02d:%02d", hour, minute)
    }

    // --- متدهای داخلی ---

    private fun formatGregorian(calendar: Calendar): String {
        val dateFormat = SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    private fun formatJalali(calendar: Calendar): String {
        val persianDate = toPersian(calendar)
        return "${persianDate[0]} ${persianDate[1]} ${persianDate[3]} ${persianDate[4]}"
    }

    @SuppressLint("NewApi")
    private fun formatHijri(calendar: Calendar): String {
        val hijriLocale = Locale.forLanguageTag("ar-u-ca-islamic")
        val pattern = "EEEE، d MMMM yyyy G"
        return try {
            val dateFormat = SimpleDateFormat(pattern, hijriLocale)
            dateFormat.timeZone = TimeZone.getDefault()
            dateFormat.format(calendar.time)
        } catch (e: Exception) {
            val dateFormatFallback = SimpleDateFormat("d MMMM yyyy", Locale.forLanguageTag("ar"))
            "هجری (نیاز به تنظیمات): ${dateFormatFallback.format(calendar.time)}"
        }
    }

  fun isPersianLocale(): Boolean {
        val locale = context.resources.configuration.locales.get(0) ?: Locale.getDefault()
        return locale.language == "fa"
    }

    private fun toPersian(calendar: Calendar): Array<String> {
        val gYear = calendar.get(Calendar.YEAR)
        val gMonth = calendar.get(Calendar.MONTH) + 1
        val gDay = calendar.get(Calendar.DAY_OF_MONTH)
        val gWeekDay = calendar.get(Calendar.DAY_OF_WEEK)

        val jDate = gregorianToJalali(gYear, gMonth, gDay)
        val weekDayName = getWeekDayName(gWeekDay)
        val monthName = getMonthName(jDate[1])

        return arrayOf(
            weekDayName,            // روز هفته
            jDate[2].toString(),    // روز ماه
            jDate[1].toString(),    // شماره ماه
            monthName,              // نام ماه
            jDate[0].toString()     // سال
        )
    }

    private fun getWeekDayName(weekDay: Int) = when(weekDay) {
        Calendar.SATURDAY -> "شنبه"
        Calendar.SUNDAY -> "یکشنبه"
        Calendar.MONDAY -> "دوشنبه"
        Calendar.TUESDAY -> "سه‌شنبه"
        Calendar.WEDNESDAY -> "چهارشنبه"
        Calendar.THURSDAY -> "پنجشنبه"
        Calendar.FRIDAY -> "جمعه"
        else -> ""
    }

    private fun getMonthName(month: Int) = when(month) {
        1 -> "فروردین"
        2 -> "اردیبهشت"
        3 -> "خرداد"
        4 -> "تیر"
        5 -> "مرداد"
        6 -> "شهریور"
        7 -> "مهر"
        8 -> "آبان"
        9 -> "آذر"
        10 -> "دی"
        11 -> "بهمن"
        12 -> "اسفند"
        else -> ""
    }
    fun getWeekDayLabel(
        timestamp: Long,
        calendarType: CalendarType,
        short: Boolean = true
    ): String {

        val calendar = Calendar.getInstance().apply {
            timeInMillis = timestamp
        }

        val resolvedCalendarType =
            if (calendarType == CalendarType.GREGORIAN && isPersianLocale()) {
                CalendarType.JALALI
            } else {
                calendarType
            }

        return when (resolvedCalendarType) {

            CalendarType.GREGORIAN -> {
                val pattern = if (short) "EEE" else "EEEE"
                val formatter = SimpleDateFormat(pattern, Locale.getDefault())
                formatter.format(calendar.time)
            }

            CalendarType.JALALI -> {
                val weekDay = calendar.get(Calendar.DAY_OF_WEEK)
                val fullName = getWeekDayName(weekDay)

                // فقط وقتی فارسیه کوتاه نکن
                fullName
            }

            CalendarType.HIJRI -> {
                val pattern = if (short) "EEE" else "EEEE"
                val locale = Locale.forLanguageTag("ar-u-ca-islamic")
                val formatter = SimpleDateFormat(pattern, locale)
                formatter.format(calendar.time)
            }
        }
    }

    private fun gregorianToJalali(gy: Int, gm: Int, gd: Int): IntArray {
        val gDaysInMonth = intArrayOf(31,28,31,30,31,30,31,31,30,31,30,31)
        val jDaysInMonth = intArrayOf(31,31,31,31,31,31,30,30,30,30,30,29)

        var gy2 = gy-1600
        var gm2 = gm-1
        var gd2 = gd-1

        var gDayNo = 365*gy2 + (gy2+3)/4 - (gy2+99)/100 + (gy2+399)/400
        for(i in 0 until gm2) gDayNo += gDaysInMonth[i]
        if(gm2>1 && (gy%4==0 && gy%100!=0 || gy%400==0)) gDayNo++
        gDayNo += gd2

        var jDayNo = gDayNo-79
        val jNp = jDayNo / 12053
        jDayNo %= 12053

        var jy = 979 + 33*jNp + 4*(jDayNo/1461)
        jDayNo %= 1461

        if(jDayNo>=366) {
            jy += (jDayNo-1)/365
            jDayNo = (jDayNo-1)%365
        }

        var i=0
        while(i<11 && jDayNo>=jDaysInMonth[i]){
            jDayNo -= jDaysInMonth[i]
            i++
        }

        val jm = i+1
        val jd = jDayNo+1
        return intArrayOf(jy,jm,jd)
    }
}
