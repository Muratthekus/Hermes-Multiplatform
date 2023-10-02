package me.thekusch.hermes.android.signup.ui.register

import android.net.Uri
import androidx.core.app.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.thekusch.hermes.android.core.file.saveFileToInternal
import me.thekusch.hermes.otp.OtpUseCase
import me.thekusch.hermes.util.GlobalConstant
import me.thekusch.hermes.util.KMMStorage
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val otpUseCase: OtpUseCase
) : ViewModel() {

    fun completeRegisterStep(
        activity: ComponentActivity,
        email: String,
        username: String,
        mediaUri: Uri,
        onFinished: () -> Unit
    ) {
        KMMStorage(activity)
            .apply {
                putString(
                    GlobalConstant.PrefKeys.USER_NAME,
                    username
                )
                putBoolean(
                    GlobalConstant.PrefKeys.IS_USER_COMPLETE_REGISTRATION,
                    true
                )
            }
        if (mediaUri != Uri.EMPTY) {
            mediaUri.saveFileToInternal(activity)
        }
        saveUserToDB(email)
        onFinished()
    }

    fun saveUserToDB(email: String) {
        viewModelScope.launch {
            otpUseCase.registerUser(email)
        }
    }

}