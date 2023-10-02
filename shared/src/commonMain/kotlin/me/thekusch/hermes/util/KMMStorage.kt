package me.thekusch.hermes.util

class KMMStorage(val context: CommonPreferences) {

    fun getInt(key: String, defValue: Int = -1): Int {
        return context.getInt(key, defValue)
    }

    fun putInt(key: String, value: Int) {
        context.setInt(key, value)
    }

    fun getString(key: String, defValue: String = ""): String {
        return context.getString(key, defValue)
    }

    fun putString(key: String, value: String) {
        context.setString(key, value)
    }

    fun getBoolean(key: String, defValue: Boolean = false): Boolean {
        return context.getBoolean(key, defValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        context.setBoolean(key, value)
    }
}