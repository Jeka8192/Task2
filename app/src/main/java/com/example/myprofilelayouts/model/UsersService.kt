package com.example.myprofilelayouts.model

import androidx.lifecycle.LiveData
import com.example.myprofilelayouts.UserRepository

class UsersService {

    private val userRepository = UserRepository.get()

    fun getUsers(): LiveData<List<User>> {
        return userRepository.getUsers()
    }

    fun deleteUsers(users: User) {
        userRepository.deleteUsers(users)
    }
}

