package com.example.myprofilelayouts

import android.app.Application
import com.example.myprofilelayouts.data.UserRepository

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        UserRepository.initialize(this)
    }
}