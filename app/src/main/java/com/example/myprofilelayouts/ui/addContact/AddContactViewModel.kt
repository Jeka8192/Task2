package com.example.myprofilelayouts.ui.addContact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myprofilelayouts.data.UserRepository
import com.example.myprofilelayouts.model.User
import kotlinx.coroutines.launch

class AddContactViewModel: ViewModel() {

    private val userRepository = UserRepository.get()

    fun addUser(user: User) {
        viewModelScope.launch {
            userRepository.addUser(user)
        }
    }
}