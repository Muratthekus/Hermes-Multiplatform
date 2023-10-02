package me.thekusch.hermes.android.signup.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.thekusch.hermes.otp.OtpUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val otpUseCase: OtpUseCase
) : ViewModel() {

    fun signUpUser(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            otpUseCase.signupUser(email, password)
        }
    }
}