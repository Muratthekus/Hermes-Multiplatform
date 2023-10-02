package me.thekusch.hermes.util

import platform.darwin.NSObject
import platform.Foundation.NSUserDefaults

actual typealias CommonPreferences = NSObject

actual fun CommonPreferences.getInt(
    key: String,
    defValue: Int
): Int {
    return NSUserDefaults
        .standardUserDefaults.integerForKey(key).toInt()
}

actual fun CommonPreferences.setInt(key: String, value: Int) {
    NSUserDefaults.standardUserDefaults.setInteger(value.toLong(), key)
}

actual fun CommonPreferences.setString(key: String, value: String) {
    NSUserDefaults.standardUserDefaults.setObject(value,key)
}

actual fun CommonPreferences.getString(
    key: String,
    defValue: String
): String {
    return NSUserDefaults
        .standardUserDefaults.stringForKey(key).toString()
}

actual fun CommonPreferences.getBoolean(
    key: String,
    defValue: Boolean
): Boolean {
    return NSUserDefaults
        .standardUserDefaults.boolForKey(key)
}

actual fun CommonPreferences.setBoolean(key: String, value: Boolean) {
    NSUserDefaults
        .standardUserDefaults.setBool(value, key).toString()
}