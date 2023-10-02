package me.thekusch.hermes.supabase

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.PropertyConversionMethod
import io.github.jan.supabase.realtime.Realtime
import kotlin.time.Duration.Companion.seconds

object SupaBaseClientProvider {

    private val supabaseClient: SupabaseClient? = null

    fun provideSupaBase(): SupabaseClient {
        if (supabaseClient != null)
            return supabaseClient
        return createSupabaseClient(
            supabaseUrl = SupabaseSecrets.supabaseUrl,
            supabaseKey = SupabaseSecrets.supabaseKey
        ) {
            install(GoTrue) {}
            install(Postgrest) {
               // defaultSchema = "schema" // default: "public"
                propertyConversionMethod = PropertyConversionMethod.SERIAL_NAME // default: PropertyConversionMethod.CAMEL_CASE_TO_SNAKE_CASE
            }
            install(Realtime) {
                reconnectDelay = 5.seconds // Default: 7 seconds
            }
        }
    }
}