package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irajnajafi1988gmail.hydrobell.domain.datastore.model.IsCompleteUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.UserProfile
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.ClearUserProfileUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.GetUserProfileUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.InsertUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "MainScreenViewModel"

data class UserProfileUiState(
    val isLoading: Boolean = false,
    val profile: UserProfile? = null,
    val error: String? = null,
    val isSaved: Boolean = false,
    val isResetDone: Boolean = false
)

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val insertUserProfileUseCase: InsertUserProfileUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val clearUserProfileUseCase: ClearUserProfileUseCase,
    private val isCompleteUseCase: IsCompleteUseCase
) : ViewModel() {

    private val _selectedTab = MutableStateFlow(0)
    val selectedTab: StateFlow<Int> = _selectedTab.asStateFlow()

    private val _state = MutableStateFlow(UserProfileUiState())
    val state: StateFlow<UserProfileUiState> = _state.asStateFlow()

    // انتخاب تب
    fun selectTab(index: Int) {
        viewModelScope.launch {
            _selectedTab.value = index
            Log.d(TAG, "📌 تب انتخاب شد: $index")
        }
    }

    // بارگذاری پروفایل
    fun loadUserProfile() {
        viewModelScope.launch {
            Log.d(TAG, "📥 loadUserProfile() فراخوانی شد")
            _state.value = _state.value.copy(isLoading = true)

            try {
                val result = getUserProfileUseCase()
                Log.d(TAG, "✅ پروفایل با موفقیت بارگذاری شد")
                Log.d(TAG, "   جنسیت: ${result?.gender}")
                Log.d(TAG, "   وزن: ${result?.weight} کیلوگرم")
                Log.d(TAG, "   سن: ${result?.age} سال")
                Log.d(TAG, "   سطح فعالیت: ${result?.activityLaval}")
                Log.d(TAG, "   محیط: ${result?.environment}")
                Log.d(TAG, "-------------------------------------")

                _state.value = _state.value.copy(
                    isLoading = false,
                    profile = result
                )
            } catch (e: Exception) {
                Log.e(TAG, "❌ خطا در بارگذاری پروفایل: ${e.localizedMessage}", e)
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

   // ذخیره پروفایل
    fun saveUserProfile(profile: UserProfile) {
        viewModelScope.launch {
            Log.d(TAG, "💾 saveUserProfile() فراخوانی شد")
            try {
                insertUserProfileUseCase(profile)
                _state.value = _state.value.copy(
                    profile = profile,
                    isSaved = true
                )
                Log.d(TAG, "✅ پروفایل با موفقیت ذخیره شد:")
                Log.d(TAG, "   جنسیت: ${profile.gender}")
                Log.d(TAG, "   وزن: ${profile.weight} کیلوگرم")
                Log.d(TAG, "   سن: ${profile.age} سال")
                Log.d(TAG, "   سطح فعالیت: ${profile.activityLaval}")
                Log.d(TAG, "   محیط: ${profile.environment}")
                Log.d(TAG, "-------------------------------------")
            } catch (e: Exception) {
                Log.e(TAG, "❌ خطا در ذخیره پروفایل: ${e.localizedMessage}", e)
                _state.value = _state.value.copy(error = e.message)
            }
        }
    }

    // ریست پروفایل
    fun restartUserProfile() {
        viewModelScope.launch {
            Log.d(TAG, "🔄 [RESET] شروع ریست پروفایل")

            try {
                // 1️⃣ پاک کردن Room
                Log.d(TAG, "🗑️ [RESET] در حال پاک کردن دیتابیس Room")
                clearUserProfileUseCase()
                Log.d(TAG, "✅ [RESET] Room پاک شد")

                // 2️⃣ ریست DataStore
                Log.d(TAG, "🔁 [RESET] ست کردن isProfileComplete = false")
                isCompleteUseCase.setProfileCompleteUseCase(false)
                Log.d(TAG, "✅ [RESET] DataStore با موفقیت ریست شد")

                // 3️⃣ ریست UI State
                _state.value = UserProfileUiState()
                Log.d(TAG, "🎯 [RESET] State ریست شد")

                Log.d(TAG, "🏁 [RESET] ریست کامل با موفقیت انجام شد")
                Log.d(TAG, "-------------------------------------")

            } catch (e: Exception) {
                Log.e(TAG, "❌ [RESET] خطا در ریست پروفایل: ${e.localizedMessage}", e)
            }
        }
    }


}
