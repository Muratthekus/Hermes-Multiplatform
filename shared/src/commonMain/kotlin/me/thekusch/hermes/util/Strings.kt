package me.thekusch.hermes.util

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc

expect class Strings {
    fun get(id: StringResource, args: List<Any>): StringDesc
}