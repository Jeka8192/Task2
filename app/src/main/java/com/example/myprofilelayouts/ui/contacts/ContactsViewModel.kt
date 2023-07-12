package com.example.myprofilelayouts.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myprofilelayouts.data.UserRepository
import com.example.myprofilelayouts.model.User
import kotlinx.coroutines.launch

class ContactsViewModel : ViewModel() {

    private val userRepository = UserRepository.get()

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    init {
        viewModelScope.launch {
            _users.value = userRepository.getUsers()
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userRepository.deleteUsers(user)
        }
    }

}