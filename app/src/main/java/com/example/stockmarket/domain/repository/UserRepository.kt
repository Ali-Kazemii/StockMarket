package com.example.stockmarket.domain.repository

import com.example.stockmarket.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(): Flow<User>
    suspend fun updateUser(user: User)
}