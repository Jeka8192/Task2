package com.example.myprofilelayouts

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.myprofilelayouts.database.UserDatabase
import com.example.myprofilelayouts.model.User
import java.util.concurrent.Executors

private const val DATABASE_NAME = "user-database"

class UserRepository private constructor(context: Context) {

    private val database: UserDatabase = Room.databaseBuilder(
        context.applicationContext,
        UserDatabase::class.java,
        DATABASE_NAME,
    ).build()

    private val userDao = database.userDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getUsers(): LiveData<List<User>> {
        return userDao.getUsers()
    }

    fun deleteUsers(users: User) {
        executor.execute {
            userDao.deleteUsers(users)
        }
    }

    fun addUser(user: User) {
        executor.execute {
            userDao.addUser(user)
        }
    }

    companion object {
        private var INSTANCE: UserRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = UserRepository(context)
            }
        }

        fun get(): UserRepository {
            return INSTANCE ?: throw IllegalStateException("UserRepository must be initialized")
        }
    }
}