package com.transvision.g2g.ui.screen.dashboard.vendor

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.RNDState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VendorViewModel @Inject constructor(vendorRepository: VendorRepository) : ViewModel() {

    private val _state = mutableStateOf(VendorState())
    val state: State<VendorState> = _state
    val TAG = "VendorViewModel"
    init {
        viewModelScope.launch {
            val vendorResponse = vendorRepository.getVendorDetails()

            if (vendorResponse.isSuccessful){
                vendorResponse.body()?.let {
                    _state.value =  state.value.copy(
                        vendorModel = it
                    )
                }
            }
        }
    }
}