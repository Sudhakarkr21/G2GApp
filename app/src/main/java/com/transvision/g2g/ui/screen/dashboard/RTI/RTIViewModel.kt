package com.transvision.g2g.ui.screen.dashboard.RTI

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transvision.g2g.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RTIViewModel @Inject constructor( val rtiRepository: RTIRepository) : ViewModel() {

    private var _state = mutableStateOf<RTIState>(RTIState())

    val state : State<RTIState> = _state

    private val TAG = "RTIViewModel"
    init {
        getRTIData(
            RTIUIState(
            "Year Wise",
                Constants.getCurrentFinancialYear()
        )
        )
    }

    fun getRTIData(rtiuiState: RTIUIState){
        viewModelScope.launch {
           val response = rtiRepository.getRTIData(rtiuiState)
            Log.d(TAG, "getRTIData: $response")
            if (response.isSuccessful){
                response.body()?.let {
                    _state.value = state.value.copy(
                        rtiModel = it
                    )
                }
            }
        }
    }
}