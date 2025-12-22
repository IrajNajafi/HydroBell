package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.model

import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.UserProfile
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.ActivityLevel
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Environment
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Gender

/**
 * آب مورد نیاز روزانه کاربر را بر اساس وزن، سن، جنسیت، سطح فعالیت،
 * محیط و درصد تعریق محاسبه می‌کند.
 */
object WaterCalculatorDynamic {

    /**
     * محاسبه میزان آب مورد نیاز روزانه به میلی‌لیتر
     */
    fun calculateDailyNeedMl(user: UserProfile): Int {

        // ---------- 1. پایه وزن ----------
        // هر کیلوگرم وزن پایه ۳۰ میلی‌لیتر آب
        var baseMl = user.weight * 30f

        // ---------- 2. اصلاح بر اساس جنسیت ----------
        baseMl *= when (user.gender) {
            Gender.MALE -> 1.10f   // مردها ۱۰٪ بیشتر
            Gender.FEMALE -> 1.00f // زن‌ها همان پایه
            Gender.NONE -> 1.00f   // مشخص نشده، همان پایه
        }

        // ---------- 3. اصلاح بر اساس سن ----------
        val ageFactor = when {
            user.age < 10 -> 0.80f          // کودکان کمتر از ۱۰ سال کمتر نیاز دارند
            user.age in 10..17 -> 0.90f     // نوجوانان
            user.age in 18..55 -> 1.00f     // بزرگسالان
            user.age > 55 -> 0.95f          // افراد مسن کمی کمتر
            else -> 1.00f
        }
        baseMl *= ageFactor

        // ---------- 4. اصلاح بر اساس سطح فعالیت ----------
        val activityAddedMl = when (user.activityLaval) {
            ActivityLevel.LOW, ActivityLevel.NONE -> 0f
            ActivityLevel.MEDIUM -> 400f
            ActivityLevel.HIGH -> 800f
        }

        // ---------- 5. اصلاح بر اساس محیط ----------
        val envAddedMl = when (user.environment) {
            Environment.FREEZING -> 100f
            Environment.COLD, Environment.NONE -> 0f
            Environment.NORMAL -> 200f
            Environment.WARM -> 300f
            Environment.HOT -> 400f
        }

        // مجموع پایه + فعالیت + محیط
        val subtotalMl = baseMl + activityAddedMl + envAddedMl

        // ---------- 6. درصد تعریق ----------
        val sweatPercentActivity = when (user.activityLaval) {
            ActivityLevel.LOW, ActivityLevel.NONE -> 0f
            ActivityLevel.MEDIUM -> 5f
            ActivityLevel.HIGH -> 10f
        }

        val sweatPercentEnv = when (user.environment) {
            Environment.FREEZING, Environment.COLD, Environment.NONE -> 0f
            Environment.NORMAL -> 5f
            Environment.WARM -> 10f
            Environment.HOT -> 15f
        }

        // جمع درصد تعریق کل
        val totalSweatPercent = (sweatPercentActivity + sweatPercentEnv) / 100f

        // ---------- 7. محاسبه نهایی ----------
        val totalMl = subtotalMl * (1 + totalSweatPercent)

        // ---------- 8. گرد کردن و محدود کردن ----------
        val roundedMl = (totalMl / 250).toInt() * 250

        // محدود کردن عدد بین 1200 تا 5000 میلی‌لیتر
        return roundedMl.coerceIn(1200, 5000)
    }
}
