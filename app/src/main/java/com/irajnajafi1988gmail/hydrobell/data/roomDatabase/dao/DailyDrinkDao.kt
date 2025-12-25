package com.irajnajafi1988gmail.hydrobell.data.roomDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.db.AppDatabase
import com.irajnajafi1988gmail.hydrobell.data.roomDatabase.entities.DailyDrinkEntity
import kotlinx.coroutines.flow.Flow

/**
 * 🥤 DAO مربوط به جدول DailyDrink
 * این اینترفیس تمام عملیات دیتابیس مربوط به آب نوشیدن روزانه را مدیریت می‌کند.
 */
@Dao
interface DailyDrinkDao {

    /**
     * 💾 درج یا به‌روزرسانی یک رکورد DailyDrink
     * اگر رکورد با همان تاریخ موجود باشد، جایگزین می‌شود (REPLACE)
     *
     * @param daily داده روزانه مصرف آب
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(daily: DailyDrinkEntity)

    /**
     * 🔍 دریافت مصرف آب یک روز مشخص
     * این تابع به صورت **سوسپند** است و فقط یک بار داده را برمی‌گرداند.
     *
     * @param date تاریخ موردنظر به فرمت yyyy-MM-dd
     * @return DailyDrinkEntity یا null اگر موجود نباشد
     */
    @Query("SELECT * FROM ${AppDatabase.DAILY_DRINK_TABLE} WHERE date = :date LIMIT 1")
    fun getByDate(date: String): Flow<DailyDrinkEntity?>


    /**
     * 📜 دریافت همه رکوردهای مصرف آب به ترتیب نزولی تاریخ
     * **Reactive**: با Flow برمی‌گردد، یعنی هر تغییری در دیتابیس باعث آپدیت اتوماتیک در collect می‌شود.
     *
     * @return Flow<List<DailyDrinkEntity>>
     */
    @Query("SELECT * FROM ${AppDatabase.DAILY_DRINK_TABLE} ORDER BY date DESC")
    fun getAll(): Flow<List<DailyDrinkEntity>>

    /**
     * 🏁 دریافت آخرین رکورد مصرف آب (آخرین روزی که داده ثبت شده)
     * **Reactive**: با Flow برمی‌گردد، مناسب UI که باید همیشه آپدیت شود.
     *
     * @return Flow<DailyDrinkEntity?> یا null اگر رکوردی وجود نداشته باشد
     */
    @Query("SELECT * FROM ${AppDatabase.DAILY_DRINK_TABLE} ORDER BY date DESC LIMIT 1")
    fun getLatest(): Flow<DailyDrinkEntity?>

    /**
     * ❌ حذف همه رکوردهای مصرف آب
     * معمولا برای reset اپ یا پاک کردن دیتابیس استفاده می‌شود.
     */
    @Query("DELETE FROM ${AppDatabase.DAILY_DRINK_TABLE}")
    suspend fun clearAll()
}
