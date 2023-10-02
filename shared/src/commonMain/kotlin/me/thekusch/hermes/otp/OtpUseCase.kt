package me.thekusch.hermes.otp


interface OtpUseCase {

    suspend fun verifyEmailOtp(
        email: String,
        token: String
    ): Boolean

    suspend fun signupUser(
        email: String,
        password: String
    )

    suspend fun registerUser(
        email: String
    )

    suspend fun resendOtp(email: String)
}