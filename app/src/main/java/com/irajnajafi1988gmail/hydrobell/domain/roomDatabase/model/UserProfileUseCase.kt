package com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model

import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.ClearUserProfileUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.GetUserProfileUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.InsertUserProfileUseCase

data class UserProfileUseCase(
    val insertUserProfileUseCase: InsertUserProfileUseCase,
    val getUserProfileUseCase: GetUserProfileUseCase,
    val clearUserProfileUseCase: ClearUserProfileUseCase
)
