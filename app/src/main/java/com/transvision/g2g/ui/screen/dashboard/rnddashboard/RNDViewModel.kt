package com.transvision.g2g.ui.screen.dashboard.rnddashboard

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transvision.g2g.ui.screen.dashboard.misdashboard.SessionState
import com.transvision.g2g.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class RNDViewModel @Inject constructor(private val rndRepository: RNDRepository) : ViewModel(){

    private val _state = mutableStateOf(RNDState())
    val state: State<RNDState> = _state
    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
    val year = Calendar.getInstance().get(Calendar.YEAR)
    val TAG = "RNDViewModel"
    init {
        getRNDDashboard(
            RNDUIState("Month Wise",Constants.monthYear,"","ALL")
        )
    }

    fun getRNDDashboard(rnduiState: RNDUIState){
        Log.d(TAG, "getRNDDashboard: monthYear $rnduiState")
        viewModelScope.launch {
            val accidentResponse = rndRepository.getRDData(rnduiState)
            Log.d(TAG, "getRNDDashboard: $accidentResponse")
            if (accidentResponse.isSuccessful){
                accidentResponse.body()?.let {
                    _state.value =  state.value.copy(
                        rndModel = it
                    )
                }
            }
        }
    }
}