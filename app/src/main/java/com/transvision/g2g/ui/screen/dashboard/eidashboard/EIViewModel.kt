package com.transvision.g2g.ui.screen.dashboard.eidashboard

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transvision.g2g.ui.screen.dashboard.rcdashboard.RCModelState
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.RNDUIState
import com.transvision.g2g.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
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
            val eiResponse = eiRepository.getEIData(rnduiState)
            Log.d(TAG, "EIViewModel : $eiResponse")
            if (eiResponse.isSuccessful){
                eiResponse.body()?.let {
                    _state.value =  state.value.copy(
                        eiModel = it
                    )
                }
            }
        }
    }
}