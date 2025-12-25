package com.irajnajafi1988gmail.hydrobell.ui.feature.mainScreen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "MainScreenViewModel"



@HiltViewModel
class MainScreenViewModel @Inject constructor(

) : ViewModel() {

    private val _selectedTab = MutableStateFlow(0)
    val selectedTab: StateFlow<Int> = _selectedTab.asStateFlow()

    fun selectTab(index: Int) {
        viewModelScope.launch {
            _selectedTab.value = index
            Log.d(TAG, "📌 تب انتخاب شد: $index")
        }
    }

}
