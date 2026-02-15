package com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase

import com.irajnajafi1988gmail.hydrobell.domain.datastore.repository.DateRepository
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.historyScreenItem.model.CalendarType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCalendarTypeUseCase @Inject constructor(
    private val repository: DateRepository
) {
    operator fun invoke(): Flow<CalendarType> = repository.getCalendarType()


}

class SaveDateUseCase @Inject constructor(
    private val repository: DateRepository
) {
    suspend operator fun invoke(calendarType: CalendarType) =
        repository.saveCalendarType(calendarType)
}