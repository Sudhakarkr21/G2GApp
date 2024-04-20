package com.transvision.g2g.ui.screen.dashboard.dss

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transvision.g2g.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DSSViewModel @Inject constructor(val dssRepository: DSSRepository) : ViewModel()  {

    private val _mutableState = mutableStateOf(DSSState())
    val state = _mutableState
    val TAG = "DSSViewModel"
    init {
        getDSSData("${Constants.getCurrentYear}-${Constants.currentMonth + 1}")
    }

    fun getDSSData(monthYear : String){
        Log.d(TAG, "getDSSData: monthYear $monthYear")
        viewModelScope.launch {
            _mutableState.value = state.value.copy(
                isLoader = true
            )
            val response = dssRepository.getDSSData(monthYear)
            Log.d(TAG, "getDSSData: response $response")

            if (response.isSuccessful){
                response.body()?.let {
                    _mutableState.value = state.value.copy(
                        dssModel = it
                    )
                }
            }
            _mutableState.value = state.value.copy(
                isLoader = false
            )
        }
    }
}