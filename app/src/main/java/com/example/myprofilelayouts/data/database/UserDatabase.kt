package com.example.myprofilelayouts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myprofilelayouts.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}