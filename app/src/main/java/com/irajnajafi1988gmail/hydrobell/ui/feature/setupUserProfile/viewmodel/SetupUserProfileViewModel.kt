package com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.irajnajafi1988gmail.hydrobell.R
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.common.topBar.StepItem
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.ActivityLevel
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Environment
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Gender
import com.irajnajafi1988gmail.hydrobell.ui.theme.turquoise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
const val TAG = "SetupViewModel"
@HiltViewModel
class SetupUserProfileViewModel @Inject constructor() : ViewModel() {

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

    val stepFlow: StateFlow<List<StepItem>> = combine(
        selectedGender,
        selectedWeight,
        selectedAge,
        selectedActivity,
        selectedEnvironment
    ){ gender, weight, age, activity, environment ->
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
                label = "$age Yr",
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

        // لاگ مقدار جدید
        Log.d(TAG, "مرحله به روز شد: ${steps.map { it.label }}")

        steps
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

}