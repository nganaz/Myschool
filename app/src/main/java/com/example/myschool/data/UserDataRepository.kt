package com.example.myschool.data

import android.content.Context
import android.content.SharedPreferences

object UserDataRepository {

    private const val PREFS_NAME = "MySchoolPrefs"
    private const val KEY_SELECTED_FORM = "selected_form"

    private lateinit var sharedPreferences: SharedPreferences

    // This must be called once from the Application class
    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveSelectedForm(form: String) {
        sharedPreferences.edit().putString(KEY_SELECTED_FORM, form).apply()
    }

    fun getSelectedForm(): String? {
        // Return the saved form, or null if it doesn't exist
        return sharedPreferences.getString(KEY_SELECTED_FORM, null)
    }

    fun hasEnrolled(): Boolean {
        // The user has enrolled if a form is saved in our preferences
        return getSelectedForm() != null
    }

    // Optional: A function to log out and clear the data
    fun clearData() {
        sharedPreferences.edit().clear().apply()
    }
}
