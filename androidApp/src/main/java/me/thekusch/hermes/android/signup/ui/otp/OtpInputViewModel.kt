package me.thekusch.hermes.android.signup.ui.otp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.thekusch.hermes.otp.OtpUseCase
import javax.inject.Inject

@HiltViewModel
class OtpInputViewModel @Inject constructor(
    private val otpUseCase: OtpUseCase
) : ViewModel() {


    private val _otpUiState: MutableStateFlow<OtpInputUiState> =
        MutableStateFlow(OtpInputUiState.Init)

    val otpUiState: StateFlow<OtpInputUiState> = _otpUiState

    fun verifyOtp(
        email: String,
        otp: String
    ) {
        viewModelScope.launch {
            _otpUiState.value = OtpInputUiState.Loading
            _otpUiState.value = if (otpUseCase.verifyEmailOtp(email, otp))
                OtpInputUiState.Success
            else OtpInputUiState.Error

        }
    }
}