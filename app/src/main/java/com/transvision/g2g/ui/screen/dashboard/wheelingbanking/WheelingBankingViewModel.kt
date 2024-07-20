package com.transvision.g2g.ui.screen.dashboard.wheelingbanking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.RNDUIState
import com.transvision.g2g.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WheelingBankingViewModel  @Inject constructor(
    val wheelingAndBillingRepository: WheelingAndBillingRepository) : ViewModel(){

    private val _uiState = MutableStateFlow(WheelingBankingState())
    val uiState: StateFlow<WheelingBankingState> = _uiState

    init {
        getWheelingAndBanking(
            RNDUIState("Month Wise", Constants.monthYear,"","ALL")
        )
    }
    fun getWheelingAndBanking(rnduiState: RNDUIState){
        viewModelScope.launch {
            _uiState.value = WheelingBankingState(isLoading = true)
            wheelingAndBillingRepository.getWheelingData(rnduiState).collect{
                if (it != null){
                    _uiState.value = WheelingBankingState(
                        wheelingBankingModel = it
                    )
                }
                _uiState.value = WheelingBankingState(isLoading = false)
            }


        }

    }
}