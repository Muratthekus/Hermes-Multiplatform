package me.thekusch.hermes.util

import android.content.Context
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.format

actual class Strings() {
    actual fun get(id: StringResource, args: List<Any>): StringDesc {

        return if(args.isEmpty()) {
            StringDesc.Resource(id)
        } else {
            id.format(*args.toTypedArray())
        }
    }
}