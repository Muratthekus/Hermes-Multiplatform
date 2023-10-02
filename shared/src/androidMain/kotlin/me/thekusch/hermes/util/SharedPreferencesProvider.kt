package me.thekusch.hermes.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys


object SharedPreferencesProvider {

    private lateinit var sharedPreferences: SharedPreferences

    fun getCommonPref(context: Context): SharedPreferences {
        if (::sharedPreferences.isInitialized)
            return sharedPreferences

        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        // Initialize/open an instance of EncryptedSharedPreferences on below line.
        sharedPreferences =  EncryptedSharedPreferences.create(
            // passing a file name to share a preferences
            context.packageName,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        return sharedPreferences
    }
}