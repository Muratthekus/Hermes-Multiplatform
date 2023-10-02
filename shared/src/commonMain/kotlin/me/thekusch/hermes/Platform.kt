package me.thekusch.hermes

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform