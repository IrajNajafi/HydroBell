package com.irajnajafi1988gmail.hydrobell.domain.datastore.model

import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.GetIsProfileCompleteUseCase
import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.SetProfileCompleteUseCase

data class IsCompleteUseCase(
    val getIsProfileCompleteUseCase : GetIsProfileCompleteUseCase,
    val setProfileCompleteUseCase: SetProfileCompleteUseCase
)
