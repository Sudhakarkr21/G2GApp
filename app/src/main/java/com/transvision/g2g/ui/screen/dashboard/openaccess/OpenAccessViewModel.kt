package com.transvision.g2g.ui.screen.dashboard.openaccess

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transvision.g2g.utils.Constants.monthYearInt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OpenAccessViewModel @Inject constructor(val openAccessRepository: OpenAccessRepository) : ViewModel() {

        private var _state = mutableStateOf(OpenAccessState())
        val state : State<OpenAccessState> = _state
        val TAG = "OpenAccessViewModel"

    init {
        //getOpenAccessData(monthYearInt)
    }
    fun getOpenAccessData(monthYear : String){
        viewModelScope.launch {
            _state.value = state.value.copy(
                loader = true
            )
            val response = openAccessRepository.getOpenAccessData(monthYear)
            _state.value = state.value.copy(
                loader = false
            )

            Log.d(TAG, "getOpenAccessData: $response")
            if (response.isSuccessful){
                response.body()?.let {
                    Log.d(TAG, "getOpenAccessData: $it")

                    _state.value =  state.value.copy(
                        openAccessModel = it
                    )
                }
            }
        }
    }
}