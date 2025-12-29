package com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase

import com.irajnajafi1988gmail.hydrobell.domain.datastore.repository.IsCompleteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIsProfileCompleteUseCase @Inject constructor(
    private val repository: IsCompleteRepository
) { operator fun invoke(): Flow<Boolean> = repository.getIsProfileCompleteFlow() }



class SetProfileCompleteUseCase @Inject constructor(
    private val repository: IsCompleteRepository
) { suspend operator fun invoke(isComplete: Boolean) = repository.setProfileComplete(isComplete) }


class ResetProfileCompleteUseCase @Inject constructor(
    private val repository: IsCompleteRepository
) {
    suspend operator fun invoke() {
        repository.setProfileComplete(false)
    }
}
