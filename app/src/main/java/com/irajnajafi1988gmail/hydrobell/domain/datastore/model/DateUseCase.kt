package com.irajnajafi1988gmail.hydrobell.domain.datastore.model

import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.GetCalendarTypeUseCase
import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.SaveDateUseCase

data class DateUseCase (
    val getCalendarTypeUseCase : GetCalendarTypeUseCase,
    val saveDateUseCase : SaveDateUseCase
)
