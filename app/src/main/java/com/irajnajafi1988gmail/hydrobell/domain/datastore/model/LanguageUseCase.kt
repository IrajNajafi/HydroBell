package com.irajnajafi1988gmail.hydrobell.domain.datastore.model

import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.GetLanguageStateUseCase
import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.SaveLanguageStateUseCase

data class LanguageUseCase(
    val getLanguageStateUseCase: GetLanguageStateUseCase,
    val saveLanguageStateUseCase: SaveLanguageStateUseCase
)
