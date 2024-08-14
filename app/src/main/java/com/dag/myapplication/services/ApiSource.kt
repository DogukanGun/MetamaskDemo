package com.dag.myapplication.services

import com.dag.myapplication.data.register.RegisterRequest
import com.dag.myapplication.data.register.RegisterResponse
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

interface ApiSource {
    suspend fun register(
        registerBody: RegisterRequest,
    ): Flow<RegisterResponse>
}