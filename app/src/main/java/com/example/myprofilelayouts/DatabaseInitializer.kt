package com.example.myprofilelayouts

import android.app.Application

class DatabaseInitializer : Application() {

    override fun onCreate() {
        super.onCreate()
        UserRepository.initialize(this)
        CreateUsers()
    }
}