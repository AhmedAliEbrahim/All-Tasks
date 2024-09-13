package com.example.addandedit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class UserViewModel : ViewModel() {
    private val _userList = MutableLiveData<MutableList<User>>(mutableListOf())
    val userList: LiveData<MutableList<User>> get() = _userList

    init {

        _userList.value = mutableListOf(
            User("Ahmed", "Ahmed123@gmail.com", "011122233455"),
            User("Ali", "Ali123@gmail.com", "0115347386647"),
            User("Mostafa", "Mostafa123@gmail.com", "011235182837"),
            User("Jana", "Jana123@gmail.com", "0117468236543"),
            User("Ebrahim", "Ebrahim123@gmail.com", "011122233455"),
            User("Omar", "Omar123@gmail.com", "011122233455")
        )
    }

    fun addUser(user: User) {
        _userList.value?.let {
            it.add(user)
            _userList.value = it
        }
    }

    fun updateUser(position: Int, updatedUser: User) {
        _userList.value?.let {
            if (position in it.indices) {
                it[position] = updatedUser
                _userList.value = it
            } else {
                Log.d("UserViewModel", "Invalid position: $position")
            }
        }
    }

    fun removeUser(user: User) {
        _userList.value?.let {
            if (it.remove(user)) {
                Log.d("UserViewModel", "Removed user: $user")
                _userList.value = it
            } else {
                Log.d("UserViewModel", "Failed to remove user: $user")
            }
        }
    }
}
