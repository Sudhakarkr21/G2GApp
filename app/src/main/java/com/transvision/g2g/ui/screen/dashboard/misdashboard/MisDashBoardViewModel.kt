package com.transvision.g2g.ui.screen.dashboard.misdashboard

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
class MisDashBoardViewModel @Inject constructor(val misRepository: MisRepository): ViewModel() {
    private val _state = mutableStateOf(SessionState())
    val state: State<SessionState> = _state
    private val _dashBoardState = mutableStateOf(MisDashBoardState())
    val dashBoardState = _dashBoardState

    val TAG = "MisDashBoardViewModel"
    init {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            val sessionResponse = misRepository.getSessionData()
            Log.d(TAG, "misRepository: ${sessionResponse}")
            if (sessionResponse.isSuccessful){
                sessionResponse.body()?.let {
                    _state.value = state.value.copy(
                        listSession = it
                    )
                }
            }
            _state.value = state.value.copy(
                isLoading = false
            )

            getMISDashBoardData()
        }
    }

    fun getMISDashBoardData(sessionID : String = "1",financialYear : String = Constants.getCurrentFinancialYear()){
        viewModelScope.launch {
            val dashBoardResponse = misRepository.getMISDashBoardData(
                financialYear,
                sessionID
            )

            Log.d(TAG, "misRepository: ${dashBoardResponse}")
            if (dashBoardResponse.isSuccessful) {
                dashBoardResponse.body()?.let {
                    _dashBoardState.value = dashBoardState.value.copy(
                        misDashBoardModel = it
                    )                }
            }
        }


    }
}