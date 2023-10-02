package me.thekusch.hermes.android.signup.ui.otp

sealed class OtpInputUiState {

    object Init: OtpInputUiState()

    object Success: OtpInputUiState()

    object Error: OtpInputUiState()

    object Loading: OtpInputUiState()
}