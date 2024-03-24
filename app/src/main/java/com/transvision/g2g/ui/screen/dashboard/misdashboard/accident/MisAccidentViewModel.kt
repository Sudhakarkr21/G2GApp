package com.transvision.g2g.ui.screen.dashboard.misdashboard.accident

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transvision.g2g.ui.screen.dashboard.misdashboard.SessionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MisAccidentViewModel @Inject constructor(val accidentRepository: AccidentRepository) :
    ViewModel() {

    private var _state = mutableStateOf(AccidentState())
    val state: State<AccidentState> = _state
    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
    val year = Calendar.getInstance().get(Calendar.YEAR)

    val TAG = "MisAccidentViewModel"
    init {
        getAccidentalDashboard("$year-${String.format("%02d", currentMonth + 1)}")
    }

    fun getAccidentalDashboard(monthYear : String){
        Log.d(TAG, "getAccidentalDashboard: monthYear $monthYear")
        viewModelScope.launch {
           val accidentResponse = accidentRepository.getAccidentInfo(monthYear)
            Log.d(TAG, "getAccidentalDashboard: $accidentResponse")
            if (accidentResponse.isSuccessful){
                accidentResponse.body()?.let {
                  _state.value =  state.value.copy(
                      accidentModel = it
                    )
                }
            }
        }
    }
}