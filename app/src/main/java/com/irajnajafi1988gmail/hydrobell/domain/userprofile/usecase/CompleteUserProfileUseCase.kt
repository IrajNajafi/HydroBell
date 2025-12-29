package com.irajnajafi1988gmail.hydrobell.domain.userprofile.usecase

import android.util.Log
import com.irajnajafi1988gmail.hydrobell.domain.datastore.usecase.SetProfileCompleteUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.UserProfile
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.InsertUserProfileUseCase
import javax.inject.Inject

class CompleteUserProfileUseCase @Inject constructor(
    private val setProfileCompleteUseCase: SetProfileCompleteUseCase,
    private val insertUserProfileUseCase: InsertUserProfileUseCase
) {

    suspend operator fun invoke(profile: UserProfile) {
        Log.d("CompleteUserProfile", "Saving user profile...")

        insertUserProfileUseCase(profile)
        setProfileCompleteUseCase(true)
    }
}
