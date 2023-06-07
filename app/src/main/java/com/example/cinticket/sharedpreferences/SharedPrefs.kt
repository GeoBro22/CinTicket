package com.example.cinticket.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import com.example.cinticket.entities.Account

class SharedPrefs(val context: Context){
    private val preferenceName = "GeoBro22"
    val sharedPref: SharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
    var sharedUser: Account? = null

    /*Stored String Data*/
    fun saveString(key_name: String, text: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(key_name, text)
        editor.apply()
    }
    fun saveLong(key_name: String, text: Long) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putLong(key_name, text)
        editor.apply()
    }

    fun getString(key_name: String): String? {
        return sharedPref.getString(key_name, null)
    }
    fun getLong(key_name: String): Long? {
        return sharedPref.getLong(key_name, 0)
    }

    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }
}