package com.irajnajafi1988gmail.hydrobell.domain.datastore.repository

import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.model.CalendarType
import kotlinx.coroutines.flow.Flow

interface DateRepository {
    fun getCalendarType(): Flow<CalendarType>
    suspend fun saveCalendarType(calendarType: CalendarType)

}