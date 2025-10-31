package com.example.myschool

import android.app.Application
import com.example.myschool.data.UserDataRepository

// This class is the first thing that runs when your app starts.
class MySchoolApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // We use the application's context to initialize our repository.
        // This gives it the ability to save and load data from the device.
        UserDataRepository.initialize(this)
    }
}
