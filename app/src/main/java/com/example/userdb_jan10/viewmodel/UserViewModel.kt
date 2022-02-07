package com.example.userdb_jan10.viewmodel

import androidx.lifecycle.*
import com.example.userdb_jan10.model.local.user.User
import com.example.userdb_jan10.model.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private var _users: MutableLiveData<List<User>> = MutableLiveData()
    val users: LiveData<List<User>> get() = _users

    fun insertUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user)
        }
    }

    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getAllUsers().collect { listOfUsers ->
                _users.postValue(listOfUsers)
            }
        }
    }

    class Factory(
        private val userRepository: UserRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                return UserViewModel(userRepository) as T
            } else {
                throw RuntimeException("Could not create instance of UsersViewModel")
            }
        }
    }
}