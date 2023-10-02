package me.thekusch.hermes.otp

import me.thekusch.hermes.supabase.CommonSupaBase

class OtpUseCaseImpl(
    private val supaBase: CommonSupaBase = CommonSupaBase()
): OtpUseCase {

    override suspend fun verifyEmailOtp(
        email: String,
        token: String
    ): Boolean {
        return supaBase
            .verifyEmailOtp(email, token)
    }

    override suspend fun signupUser(
        email: String,
        password: String
    ) {
        supaBase.signupUser(email, password)
    }

    override suspend fun registerUser(email: String) {
        supaBase.registerUser(email)
    }

    override suspend fun resendOtp(email: String) {
        supaBase.resendOtp(email)
    }
}


class OtpUseCaseModule {
    val otpUseCase: OtpUseCase = OtpUseCaseImpl()
}