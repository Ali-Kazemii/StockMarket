package com.example.stockmarket.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val avatarUrl: String,
    val joinDate: String,
    val totalInvested: Double,
    val accountBalance: Double
)