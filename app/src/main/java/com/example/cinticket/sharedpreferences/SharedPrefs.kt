package com.example.cinticket.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import com.example.cinticket.entities.Account
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPrefs(val context: Context){
    private val preferenceName = "GeoBro22"
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
    var sharedUser: Account? = null
    private val gson = Gson()

    /*Stored String Data*/
    fun saveString(key_name: String, text: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key_name, text)
        editor.apply()
    }
    fun saveLong(key_name: String, text: Long) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putLong(key_name, text)
        editor.apply()
    }

    fun getString(key_name: String): String? {
        return sharedPreferences.getString(key_name, null)
    }
    fun getLong(key_name: String): Long? {
        return sharedPreferences.getLong(key_name, 0)
    }
    fun saveList(key: String, list: List<Any>) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }

    fun getList(key: String): List<Any> {
        val json = sharedPreferences.getString(key, null)
        val type = object : TypeToken<List<Any>>() {}.type
        return gson.fromJson(json, type) ?: listOf()
    }
    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}