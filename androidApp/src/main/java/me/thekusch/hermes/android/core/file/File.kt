package me.thekusch.hermes.android.core.file

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream


fun Uri.saveFileToInternal(context: Context) {
    val path: File = context.filesDir
    val file = File(path, "user_profile_picture.jpg")
    val uriStr = this.toString()
    val stream = FileOutputStream(file)
    stream.use { fileStream ->
        fileStream.write(uriStr.toByteArray())
    }
}