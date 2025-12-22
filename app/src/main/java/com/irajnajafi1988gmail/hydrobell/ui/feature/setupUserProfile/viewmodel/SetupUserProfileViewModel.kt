package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.domain.datastore.model.IsCompleteUseCase
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.UserProfile
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.repository.UserProfileRepository
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.usecase.InsertUserProfileUseCase
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.common.topBar.StepItem
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.ActivityLevel
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Environment
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Gender
import com.irajnajafi1988gmail.hydrobell.ui.theme.turquoise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "SetupViewModel"

@HiltViewModel
class SetupUserProfileViewModel @Inject constructor(
    val isCompleteUseCase: IsCompleteUseCase,
    private val insertUserProfileUseCase: InsertUserProfileUseCase
) : ViewModel() {

    companion object {
        const val LAST_FORM_STEP = 4
        const val LOADING_STEP = 5
    }

    private val _currentSetup = MutableStateFlow(0)
    val currentSetup: StateFlow<Int> = _currentSetup.asStateFlow()

    private val _selectedGender = MutableStateFlow(Gender.NONE)
    val selectedGender: StateFlow<Gender> = _selectedGender.asStateFlow()

    private val _selectedWeight = MutableStateFlow(65)
    val selectedWeight: StateFlow<Int> = _selectedWeight.asStateFlow()

    private val _selectedAge = MutableStateFlow(25)
    val selectedAge: StateFlow<Int> = _selectedAge.asStateFlow()

    private val _selectedActivity = MutableStateFlow(ActivityLevel.NONE)
    val selectedActivity: StateFlow<ActivityLevel> = _selectedActivity.asStateFlow()

    private val _selectedEnvironment = MutableStateFlow(Environment.NONE)
    val selectedEnvironment: StateFlow<Environment> = _selectedEnvironment.asStateFlow()

    private val _setupCompleted = MutableStateFlow(false)
    val setupCompleted: StateFlow<Boolean> = _setupCompleted.asStateFlow()

    private val stepValidation: List<StateFlow<Boolean>> = listOf(
        selectedGender.map { it != Gender.NONE }.stateIn(viewModelScope, SharingStarted.Lazily, false),
        selectedWeight.map { it > 0 }.stateIn(viewModelScope, SharingStarted.Lazily, false),
        selectedAge.map { it > 0 }.stateIn(viewModelScope, SharingStarted.Lazily, false),
        selectedActivity.map { it != ActivityLevel.NONE }.stateIn(viewModelScope, SharingStarted.Lazily, false),
        selectedEnvironment.map { it != Environment.NONE }.stateIn(viewModelScope, SharingStarted.Lazily, false)
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val isNextEnabled: StateFlow<Boolean> = currentSetup
        .flatMapLatest { step ->
            val relevantValidations = stepValidation.take(step + 1)
            combine(relevantValidations) { results -> results.all { it } }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, false)
        .also { flow ->
            viewModelScope.launch {
                flow.collect { enabled ->
                    Log.d(TAG, "📌 مرحله فعلی: ${_currentSetup.value}")
                    stepValidation.forEachIndexed { i, step ->
                        Log.d(TAG, "   مرحله $i فعال است؟ ${if (step.value) "✅ بله" else "❌ خیر"}")
                    }
                    Log.d(TAG, "➡️ دکمه بعدی فعال است؟ ${if (enabled) "✅ بله" else "❌ خیر"}")
                    Log.d(TAG, "-----------------------------------------")
                }
            }
        }

    val stepFlow: StateFlow<List<StepItem>> = combine(
        selectedGender,
        selectedWeight,
        selectedAge,
        selectedActivity,
        selectedEnvironment
    ) { gender, weight, age, activity, environment ->

        val steps = listOf(
            StepItem(
                icon = when (gender) {
                    Gender.MALE -> R.drawable.male
                    Gender.FEMALE -> R.drawable.female
                    else -> R.drawable.venus_mars_icon
                },
                label = gender.name,
                color = turquoise
            ),
            StepItem(
                icon = R.drawable.weight,
                label = "$weight Kg",
                color = turquoise
            ),
            StepItem(
                icon = R.drawable.age,
                label = "$age years old",
                color = turquoise
            ),
            StepItem(
                icon = R.drawable.activity,
                label = activity.name,
                color = turquoise
            ),
            StepItem(
                icon = R.drawable.environment,
                label = environment.name,
                color = turquoise
            )
        )

        Log.d(TAG, "📝 مراحل به‌روزرسانی شدند: ${steps.map { it.label }}")
        steps
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    fun nextStep() {
        viewModelScope.launch {
            when (_currentSetup.value) {
                in 0 until LAST_FORM_STEP -> {
                    _currentSetup.value++
                    Log.d(TAG, "➡️ رفتن به مرحله بعد: ${_currentSetup.value}")
                }
                LAST_FORM_STEP -> {
                    _currentSetup.value = LOADING_STEP
                    Log.d(TAG, "⏳ شروع ذخیره‌سازی و تکمیل پروفایل...")
                    delay(1500)
                    completeProfile() // حالا suspend هست
                    _setupCompleted.value = true
                    Log.d(TAG, "🎉 مراحل تکمیل شد!")
                }

            }
        }
    }

    fun backStep() {
        viewModelScope.launch {
            if (_currentSetup.value in 1..LAST_FORM_STEP) {
                _currentSetup.value--
                Log.d(TAG, "⬅️ برگشت به مرحله قبلی: ${_currentSetup.value}")
            }
        }
    }

    fun setGender(gender: Gender) {
        viewModelScope.launch {
            _selectedGender.value = gender
            Log.d(TAG, "👤 جنسیت انتخاب شد: $gender")
        }
    }

    fun setWeight(weight: Int) {
        viewModelScope.launch {
            _selectedWeight.value = weight
            Log.d(TAG, "⚖️ وزن انتخاب شد: $weight کیلوگرم")
        }
    }

    fun setAge(age: Int) {
        viewModelScope.launch {
            _selectedAge.value = age
            Log.d(TAG, "🎂 سن انتخاب شد: $age سال")
        }
    }

    fun setActivityLevel(level: ActivityLevel) {
        viewModelScope.launch {
            _selectedActivity.value = level
            Log.d(TAG, "🏃‍♂️ سطح فعالیت انتخاب شد: $level")
        }
    }

    fun setEnvironment(environment: Environment) {
        viewModelScope.launch {
            _selectedEnvironment.value = environment
            Log.d(TAG, "🌎 محیط انتخاب شد: $environment")
        }
    }

    private  suspend fun completeProfile() {

            try {
                // 1️⃣ ست کردن Complete
                isCompleteUseCase.setProfileCompleteUseCase(true)
                Log.d(TAG, "✅ پروفایل در DataStore تکمیل شد")

                // 2️⃣ ساخت UserProfile
                val profile = UserProfile(
                    gender = _selectedGender.value,
                    weight = _selectedWeight.value,
                    age = _selectedAge.value,
                    activityLaval = _selectedActivity.value,
                    environment = _selectedEnvironment.value
                )
                Log.d(TAG, "📦 پروفایل ساخته شد: جنسیت=${profile.gender}, وزن=${profile.weight}, سن=${profile.age}, فعالیت=${profile.activityLaval}, محیط=${profile.environment}")

                // 3️⃣ ذخیره در Room
                insertUserProfileUseCase(profile)
                Log.d(TAG, "💾 پروفایل با موفقیت در Room ذخیره شد")

            } catch (e: Exception) {
                Log.e(TAG, "❌ خطا در completeProfile → ${e.localizedMessage}", e)
            }
        }

}
