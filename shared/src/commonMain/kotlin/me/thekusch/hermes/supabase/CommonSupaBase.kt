package me.thekusch.hermes.supabase

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Returning
import me.thekusch.hermes.supabase.model.User

class CommonSupaBase constructor(
    private val supabase: SupabaseClient = SupaBaseClientProvider.provideSupaBase()
) {

    private val goTrue: GoTrue
        get() {
            return supabase.gotrue
        }

    private val database: Postgrest
        get() {
            return supabase.postgrest
        }

    suspend fun signupUser(
        email: String,
        password: String
    ) {
        goTrue.signUpWith(Email) {
            this.email = email
            this.password = password
        }
    }

    suspend fun registerUser(
        email: String,
    ) {
        val user = User(
            id = goTrue.currentSessionOrNull()?.user?.id.orEmpty(),
            email
        )
        database["profiles"].insert(user, returning = Returning.MINIMAL)
    }

    suspend fun verifyEmailOtp(
        email: String,
        token: String
    ): Boolean {
        return try {
            goTrue.verifyEmailOtp(
                type = OtpType.Email.SIGNUP,
                email = email,
                token = token
            )
            true
        } catch (exception: Exception) {

            false
        }
    }

    suspend fun resendOtp(
        email: String
    ) {
        goTrue.resendEmail(OtpType.Email.SIGNUP, email)
    }
}