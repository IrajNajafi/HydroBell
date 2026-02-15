package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irajnajafi1988gmail.hydrobell.domain.roomDatabase.model.UserProfile
import com.irajnajafi1988gmail.hydrobell.domain.userprofile.usecase.CompleteUserProfileUseCase
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.common.topBar.StepItem
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.mapper.SetupStepUiMapper
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.ActivityLevel
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Environment
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Gender
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

@HiltViewModel
class SetupUserProfileViewModel @Inject constructor(
    private val completeUserProfileUseCase: CompleteUserProfileUseCase
) : ViewModel() {


    companion object {
        const val LAST_FORM_STEP = 4
        const val LOADING_STEP = 5
    }

    // ---------------- Form State ----------------
    private val _currentStep = MutableStateFlow(0)
    val currentStep: StateFlow<Int> = _currentStep.asStateFlow()

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

    // ---------------- Step Validation ----------------
    private val stepValidation: List<StateFlow<Boolean>> = listOf(
        selectedGender.map { it != Gender.NONE }
            .stateIn(viewModelScope, SharingStarted.Lazily, false),
        selectedWeight.map { it > 0 }.stateIn(viewModelScope, SharingStarted.Lazily, false),
        selectedAge.map { it > 0 }.stateIn(viewModelScope, SharingStarted.Lazily, false),
        selectedActivity.map { it != ActivityLevel.NONE }
            .stateIn(viewModelScope, SharingStarted.Lazily, false),
        selectedEnvironment.map { it != Environment.NONE }
            .stateIn(viewModelScope, SharingStarted.Lazily, false)
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val isNextEnabled: StateFlow<Boolean> = currentStep
        .flatMapLatest { step ->
            val relevantValidations = stepValidation.take(step + 1)
            combine(relevantValidations) { results -> results.all { it } }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    // ---------------- Step Display ----------------
    val stepFlow: StateFlow<List<StepItem>> = combine(
        selectedGender,
        selectedWeight,
        selectedAge,
        selectedActivity,
        selectedEnvironment
    ) { gender, weight, age, activity, environment ->

        val profile = UserProfile(
            gender = gender,
            weight = weight,
            age = age,
            activityLaval = activity,
            environment = environment
        )

        SetupStepUiMapper.map(profile)
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // ---------------- Update Form ----------------
    fun setGender(gender: Gender) = viewModelScope.launch { _selectedGender.value = gender }
    fun setWeight(weight: Int) = viewModelScope.launch { _selectedWeight.value = weight }
    fun setAge(age: Int) = viewModelScope.launch { _selectedAge.value = age }
    fun setActivityLevel(level: ActivityLevel) =
        viewModelScope.launch { _selectedActivity.value = level }

    fun setEnvironment(environment: Environment) =
        viewModelScope.launch { _selectedEnvironment.value = environment }

    // ---------------- Navigation ----------------
    fun nextStep() {
        viewModelScope.launch {
            when (_currentStep.value) {
                in 0 until LAST_FORM_STEP -> _currentStep.value++
                LAST_FORM_STEP -> {
                    _currentStep.value = LOADING_STEP
                    val profile = UserProfile(
                        gender = _selectedGender.value,
                        weight = _selectedWeight.value,
                        age = _selectedAge.value,
                        activityLaval = _selectedActivity.value,
                        environment = _selectedEnvironment.value
                    )
                    try {
                        completeUserProfileUseCase(profile)
                        delay(2000)
                        _setupCompleted.value = true
                    } catch (e: Exception) {
                        e.message
                        _currentStep.value = LAST_FORM_STEP
                    }

                }
            }
        }
    }


    fun backStep() {
        if (_currentStep.value > 0) _currentStep.value--
    }
}

