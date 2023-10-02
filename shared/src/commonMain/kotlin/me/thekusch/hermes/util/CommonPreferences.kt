package me.thekusch.hermes.util

expect class CommonPreferences

expect fun CommonPreferences.getInt(key: String,defValue: Int): Int

expect fun CommonPreferences.setInt(key: String,value: Int)

expect fun CommonPreferences.setString(key: String,value: String)

expect fun CommonPreferences.getString(key: String,defValue: String): String

expect fun CommonPreferences.getBoolean(key: String, defValue: Boolean): Boolean

expect fun CommonPreferences.setBoolean(key: String,value: Boolean)