package com.example.stockmarket.domain.usecase

import com.example.stockmarket.domain.model.User
import com.example.stockmarket.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<User> {
        return repository.getUser()
    }
}