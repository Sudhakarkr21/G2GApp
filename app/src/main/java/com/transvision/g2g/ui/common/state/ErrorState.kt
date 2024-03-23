package com.transvision.g2g.ui.common.state

import androidx.annotation.StringRes
import com.transvision.g2g.R

/**
 * Error state holding values for error ui
 */
data class ErrorState(
    val hasError: Boolean = false,
    @StringRes val errorMessageStringResource: Int = R.string.empty_string
)