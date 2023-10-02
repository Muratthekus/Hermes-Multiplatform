package me.thekusch.hermes.supabase.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val email: String
)
