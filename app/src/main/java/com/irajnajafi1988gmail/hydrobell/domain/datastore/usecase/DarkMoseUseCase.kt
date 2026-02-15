package com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase

import com.irajnajafi1988gmail.hydrobell.domain.datastore.repository.DarkModeRepository
import com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.components.settingScreenItem.model.ItemDarkMode
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDarkModeStateUseCase @Inject constructor(
    private val repository: DarkModeRepository
) {
    operator fun invoke(): Flow<ItemDarkMode> = repository.getDarkModeState()

}

class SaveDarkModeStateUseCase @Inject constructor(
    private val repository: DarkModeRepository
) {
    suspend operator fun invoke(mode: ItemDarkMode) = repository.saveDarkModeState(mode)
}