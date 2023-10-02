package me.thekusch.hermes.util

import androidx.core.app.ComponentActivity
import androidx.core.content.edit

actual typealias CommonPreferences = ComponentActivity

actual fun CommonPreferences.getInt(
    key: String,
    defValue: Int
): Int {
    return SharedPreferencesProvider
        .getCommonPref(this)
        .getInt(key, defValue)
}

actual fun CommonPreferences.setInt(key: String, value: Int) {
    SharedPreferencesProvider
        .getCommonPref(this)
        .edit {
            putInt(key, value)
        }
}

actual fun CommonPreferences.setString(key: String, value: String) {
    SharedPreferencesProvider
        .getCommonPref(this)
        .edit {
            putString(key, value)
        }
}

actual fun CommonPreferences.getString(
    key: String,
    defValue: String
): String {
    return SharedPreferencesProvider
        .getCommonPref(this)
        .getString(key, defValue).orEmpty()
}

actual fun CommonPreferences.getBoolean(
    key: String,
    defValue: Boolean
): Boolean {
    return SharedPreferencesProvider
        .getCommonPref(this)
        .getBoolean(key, defValue)
}

actual fun CommonPreferences.setBoolean(key: String, value: Boolean) {
    SharedPreferencesProvider
        .getCommonPref(this)
        .edit {
            putBoolean(key, value)
        }
}