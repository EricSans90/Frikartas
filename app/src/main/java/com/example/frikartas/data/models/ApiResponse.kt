package com.example.frikartas.data.models

data class ApiResponse<T>(
    val status: String,
    val message: String,
    val data: T
)
