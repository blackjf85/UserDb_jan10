package com.example.userdb_jan10.model.repository

import com.example.userdb_jan10.model.local.user.User
import com.example.userdb_jan10.model.local.user.UserDao
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val userDao: UserDao
) {

    suspend fun addUser(user: User) {
        userDao.insertUser(user)
    }

    fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()

}