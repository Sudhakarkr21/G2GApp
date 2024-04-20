package com.transvision.g2g.ui.screen.dashboard.rcdashboard

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.RNDState
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.RNDUIState
import com.transvision.g2g.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class RCViewModel @Inject constructor(val rcRepository: RCRepository) : ViewModel() {

    private val _state = mutableStateOf(RCModelState())
    val state: State<RCModelState> = _state
    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
    val year = Calendar.getInstance().get(Calendar.YEAR)
    val TAG = "RCViewModel"
    init {
        getRCDashboard(
            RNDUIState("Month Wise", Constants.monthYear,"","ALL")
        )
    }

    fun getRCDashboard(rnduiState: RNDUIState){
        Log.d(TAG, "getRNDDashboard: monthYear $rnduiState")
        viewModelScope.launch {
            val accidentResponse = rcRepository.getRCData(rnduiState)
            Log.d(TAG, "getRNDDashboard: $accidentResponse")
            if (accidentResponse.isSuccessful){
                accidentResponse.body()?.let {
                    _state.value =  state.value.copy(
                        rcModel = it
                    )
                }
            }
        }
    }
}