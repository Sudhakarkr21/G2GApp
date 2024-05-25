package com.transvision.g2g.ui.screen.dashboard.eidashboard

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transvision.g2g.ui.screen.dashboard.rcdashboard.RCModelState
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.RNDUIState
import com.transvision.g2g.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class EIViewModel @Inject constructor(val eiRepository: EIRepository) : ViewModel(){

    private val _state = mutableStateOf(EIModelState())
    val state: State<EIModelState> = _state
    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
    val year = Calendar.getInstance().get(Calendar.YEAR)
    val TAG = "EIViewModel"

    init {
        getEIDashboard(
            RNDUIState("Month Wise", Constants.monthYear,"","ALL")
        )
    }

    fun getEIDashboard(rnduiState: RNDUIState){
        Log.d(TAG, "EIViewModel : monthYear $rnduiState")
        viewModelScope.launch {
            _state.value =  state.value.copy(
                isloading = true
            )
            eiRepository.getEIData(rnduiState).collect{
                it?.let {
                    _state.value =  state.value.copy(
                        eiModel = it, isloading = false
                    )
                }
            }
        }
    }
}