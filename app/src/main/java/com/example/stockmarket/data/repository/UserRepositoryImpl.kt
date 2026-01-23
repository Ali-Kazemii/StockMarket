package com.example.stockmarket.data.repository
import com.example.stockmarket.domain.model.User
import com.example.stockmarket.domain.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor() : UserRepository {

    private var currentUser = User(
        id = "user_001",
        name = "Ali M.",
        email = "ali@example.de",
        avatarUrl = "",
        joinDate = "January 2024",
        totalInvested = 45000.0,
        accountBalance = 5230.50
    )

    override fun getUser(): Flow<User> = flow {
        delay(200)
        emit(currentUser)
    }

    override suspend fun updateUser(user: User) {
        currentUser = user
    }
}