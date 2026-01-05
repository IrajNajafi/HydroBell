package com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.common

import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.DailyDrink
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.DailyDrinkUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.UserProfileUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.repository.DailyDrinkRepository
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.homeScreenItem.utils.WaterCalculatorDynamic
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import javax.inject.Inject


class LoadTodayDrinkUseCase @Inject constructor(
    private val dailyDrinkUseCase: DailyDrinkUseCase,
    private val userProfileUseCase: UserProfileUseCase
) {
    suspend operator fun invoke(): Pair<DailyDrink, Int> {
        val todayDate = LocalDate.now().toString()

        // 1️⃣ محاسبه تارگت روزانه
        val profile = userProfileUseCase.getUserProfileUseCase()
        val dailyTarget =
            profile?.let { WaterCalculatorDynamic.calculateDailyNeedMl(it) } ?: 0

        // 2️⃣ دریافت رکورد امروز (یا ساخت امن)
        val todayDrink =
            dailyDrinkUseCase.getByDate(todayDate).first()
                ?: DailyDrink(
                    date = todayDate,
                    totalDrink = 0,
                    isCompleted = false
                ).also { dailyDrinkUseCase.upsert(it) }

        // 3️⃣ فقط برگردوندن دیتا (بدون منطق جانبی)
        return todayDrink to dailyTarget
    }
}
