package com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase

import com.irajnajafi1988gmail.hydrobell.domain.datastore.repository.LanguageRepository
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.model.ItemLanguage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLanguageStateUseCase @Inject constructor(
    private val repository: LanguageRepository
) {
    operator fun invoke(): Flow<ItemLanguage> = repository.getLanguageState()
}

class SaveLanguageStateUseCase @Inject constructor(
    private val repository: LanguageRepository
) {
    suspend operator fun invoke(language: ItemLanguage) = repository.saveLanguageState(language)
}