package com.example.myprofilelayouts.data

import android.content.Context
import androidx.room.Room
import com.example.myprofilelayouts.data.database.CreateUsers
import com.example.myprofilelayouts.data.database.UserDatabase
import com.example.myprofilelayouts.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val DATABASE_NAME = "user-database"

class UserRepository private constructor(context: Context) {
    private val scope = CoroutineScope(Dispatchers.IO + Job())

    private val database: UserDatabase = Room.databaseBuilder(
        context.applicationContext,
        UserDatabase::class.java,
        DATABASE_NAME,
    ).build()

    private val userDao = database.userDao()

    suspend fun getUsers(): List<User> = withContext(Dispatchers.IO) {
        userDao.getUsers()
    }

    suspend fun deleteUsers(users: User) = withContext(Dispatchers.IO) {
        userDao.deleteUsers(users)
    }

    suspend fun addUser(user: User) = withContext(Dispatchers.IO) {
        userDao.addUser(user)
    }

    companion object {
        private var INSTANCE: UserRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = UserRepository(context)
                CreateUsers.generateUsers().forEach {
                    get().scope.launch {
                        get().addUser(it)
                    }
                }
            }
        }

        fun get(): UserRepository {
            return INSTANCE ?: throw IllegalStateException("UserRepository must be initialized")
        }
    }
}