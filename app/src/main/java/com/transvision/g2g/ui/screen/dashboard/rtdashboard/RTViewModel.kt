package com.transvision.g2g.ui.screen.dashboard.rtdashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.RNDUIState
import com.transvision.g2g.ui.screen.dashboard.wheelingbanking.WheelingBankingState
import com.transvision.g2g.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RTViewModel @Inject constructor(val rtRepo: RTRepo) : ViewModel() {


    private val _uiState = MutableStateFlow(RTModelState())
    val uiState: StateFlow<RTModelState> = _uiState

    init {
        getRTData(
            RNDUIState("Month Wise", Constants.monthYear,"","ALL")
        )
    }

    fun updateRTView(rtViewData: RTView){
        _uiState.update {
            it.copy(
                rtView = rtViewData
            )
        }
    }

    fun getRTData(rnduiState: RNDUIState){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true
            )
            rtRepo.getRDData(rnduiState).collect{
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    rtModel = it?: RTModel()
                )
            }
        }
    }
}